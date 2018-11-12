#!/bin/bash

root=$(git rev-parse --show-toplevel)
command="./script/api-parser-all"
spec_folder="api.json.d"
cd $root
eval $command

tmp_repo="tmp"
# rm -rf $tmp_repo

# git clone ssh://git@git.sqcorp.co/xp/api-spec-generator.git $tmp_repo

cd $tmp_repo
eval $command


api_specs=$(ls api.json.d/*.json)

for api_spec in $api_specs
do
	echo "diff ${api_spec} ${root}/${api_spec}"
	diff $api_spec $root/$api_spec
done

cd  $root
