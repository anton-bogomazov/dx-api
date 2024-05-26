#!/bin/bash
set -e
currentDir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
rootDir="$currentDir/../../"

started_at=$(date +%s)

(cd "$rootDir" && exec ./tools/scripts/build.sh)
(cd "$rootDir" && exec ./gradlew jibDockerBuild)
(cd "$rootDir" && exec docker-compose  \
  -f ./tools/docker/infrastructure.yml \
  -f ./tools/docker/monitoring.yml \
  -f ./tools/docker/services.yml \
--env-file ./tools/docker/env/local.env --project-name=user-api --profile local up -d --remove-orphans)

portainerPort=$(cd "$rootDir" && cat ./tools/docker/env/local.env | grep "PORTAINER_PORT" | cut -d'=' -f2)

printf 'Local stack ENVs\n'
(cd "$rootDir" && exec cat ./tools/docker/env/local.env)
printf "\nPortainer GUI is available at http://localhost:$portainerPort/#/dashboard\n"

echo "Finished in $(($(date +%s) - $started_at)) seconds"