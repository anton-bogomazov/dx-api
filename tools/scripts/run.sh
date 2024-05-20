#!/bin/bash
set -e
currentDir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
rootDir="$currentDir/../../"

(cd "$rootDir" && exec ./tools/scripts/build.sh)
(cd "$rootDir" && exec ./gradlew jibDockerBuild)
(cd "$rootDir" && exec docker-compose -f ./tools/docker/docker-compose.yml --env-file \
./tools/docker/env/local.env --project-name=user-api --profile local up -d --remove-orphans)

portainerPort=$(cd "$rootDir" && cat ./tools/docker/env/local.env | grep "PORTAINER_PORT" | cut -d'=' -f2)

printf "\nPortainer GUI is available at http://localhost:$portainerPort/#/dashboard\n"