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

# Configure Ruby Environment
RUBY_VERSION=2.4
RUBY_SHA=724e25cbd18fbb1036eaac84e37fc2cf908f44bb # ruby 2.4.4

# Pre requisites: Needs gcc-c++ package.
sudo yum -y install gcc-c++
sudo yum -y install mysql-devel

# Install Ruby Runtime
RUBY_PARTIAL=ruby-${RUBY_VERSION}_el7_${RUBY_SHA}.tar.gz
curl --retry 4 -sfL https://hoistrepo-api.vip.sjc2b.square/download/partial-artifact/ruby/${RUBY_PARTIAL} | sudo tar zx
export PATH=$PWD/ruby/bin:$PATH
sudo chown -R prod-jenkins:prod-jenkins ruby/

# setup git
git config --global user.name "Connect Technical Reference Preview Generator"
git config --global user.email "devs-experience-fyi@squareup.com"

working_dir=$PWD
tmp_dir=$working_dir/tmp
rm -rf $tmp_dir
mkdir -p $tmp_dir

proto_dir=$tmp_dir/square-public-apis
git clone "https://git.sqcorp.co/scm/xp/square-public-apis.git" $proto_dir

documentation_dir=$tmp_dir/connect-api-docs
git clone "https://git.sqcorp.co/scm/cad/connectv2-docs.git" $documentation_dir


cd $documentation_dir
gem install bundler --conservative --version '>= 1.12.5'

# retrieve all unmerged branches on square-public-apis to generate previewable tech ref
cd $proto_dir
branches=$(git branch -r --no-merged |cut -d "/" -f 2-)
branches+=("master")

for branch in $branches
do
    cd $proto_dir
    git checkout $branch

    latest_sha=$(git rev-parse HEAD)
    # generate API specs with current branch
    cd $working_dir
    script/api-parser-all -s $proto_dir

    # update connectv2-docs
    cd $documentation_dir
    git checkout master

    preview_branch="preview_${branch}"
    git checkout -b $preview_branch

    rm -f $documentation_dir/api.json.d/*.json
    cp $working_dir/api.json.d/*_docs.json $documentation_dir/sources/api.json.d/

    bundle install
    bin/rake documentation:compile_preview

    if [[ -z $(git status -s) ]]
    then
        echo "No changes on documentation for $preview_branch"
    else
        git add --all .
        git commit -m "Tech Ref Preview Update $latest_sha"
        git push -uf origin $preview_branch
    fi
done
