#!/bin/bash
set -e
currentDir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
rootDir="$currentDir/../../"

(cd "$rootDir" && exec docker-compose  \
  -f ./tools/docker/infrastructure.yml \
  -f ./tools/docker/monitoring.yml \
  -f ./tools/docker/services.yml \
--env-file ./tools/docker/env/local.env --profile local --project-name=user-api stop)