#!/bin/bash
# Invoked by prod-jenkins
set -exu
set -o pipefail

# Setup proxy access
proxy_host=webproxy.vip
proxy_port=3128
export http_proxy=http://$proxy_host:$proxy_port
export https_proxy=${http_proxy}
export no_proxy='vip,square,corp.squareup.com,sqcorp.co'
export MAVEN_OPTS="-Dhttp.proxyHost=$proxy_host -Dhttp.proxyPort=$proxy_port -Dhttps.proxyHost=$proxy_host -Dhttps.proxyPort=$proxy_port"

java_version='1.8.0_144'
mvn_version='3.3.9'

export JAVA_HOME="/usr/java/jdk${java_version}"
export MAVEN_HOME="/opt/apache-maven-${mvn_version}"
export PATH="${MAVEN_HOME}/bin:${JAVA_HOME}/bin:${PATH}"

# Install Java
curl --connect-timeout 60 --retry 3 -sfL \
    https://hoistrepo-api.vip/download/partial-artifact/jdk-partials/jdk-${java_version}.tar.gz \
    | sudo tar -C / -zxf -

# Install Maven
curl --connect-timeout 60 --retry 3 -sfL \
    https://hoistrepo-api.vip/download/user-submitted-artifacts/apache-maven/apache-maven-${mvn_version}-bin.tar.gz \
    | sudo tar -C /opt -xzf -

# setup git
git config --global user.name "Connect API Specification Generator"
git config --global user.email "devs-experience-fyi@squareup.com"

working_dir=$PWD
tmp_dir=$working_dir/tmp
rm -rf $tmp_dir
mkdir -p $tmp_dir
version=`git rev-parse --short HEAD`
branch_name="api-specification-update"

proto_dir=$tmp_dir/square-public-apis
git clone "https://git.sqcorp.co/scm/xp/square-public-apis.git" $proto_dir

specification_dir=$tmp_dir/connect-api-spec
git clone "https://git.sqcorp.co/scm/xp/connect-api-specification.git" $specification_dir

documentation_dir=$tmp_dir/connect-api-docs
git clone "https://git.sqcorp.co/scm/cad/connectv2-docs.git" $documentation_dir

script/api-parser-all -s $proto_dir

# update connect-api-specification
cd $specification_dir
if [ `git branch -r | grep -i "^\s*origin/${branch_name}$"` ];
then
    git checkout $branch_name
else
    git checkout -f master
    git branch -D $branch_name || true
    git checkout -b $branch_name
fi
rm -f $specification_dir/api.json.d/*.json
cp $working_dir/api.json.d/*.json $specification_dir/api.json.d/

if [[ -z $(git status -s) ]]
then
    echo "No changes on specification"
else
    git add --all .
    git commit -m "API Specification update: $version"
    git rebase master
    git push -uf origin $branch_name
fi
cd $working_dir

# update connectv2-docs
cd $documentation_dir/
if [ `git branch -r | grep -i "^\s*origin/${branch_name}$"` ];
then
    git checkout $branch_name
else
    git checkout -f master
    git branch -D $branch_name || true
    git checkout -b $branch_name
fi
rm -f $documentation_dir/api.json.d/*.json
cp $working_dir/api.json.d/*.json $documentation_dir/sources/api.json.d/

if [[ -z $(git status -s) ]]
then
    echo "No changes on documentation"
else
    git add --all .
    git commit -m "API Specification update: $version"
    git push -u origin $branch_name
fi
cd $working_dir

rm -rf $proto_dir $specification_dir $documentation_dir
