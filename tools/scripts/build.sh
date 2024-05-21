#!/bin/bash
set -e
currentDir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
rootDir="$currentDir/../../"

# TODO add it to build phase
(cd "$rootDir" && exec ./gradlew build user-api:jibDockerBuild -PallWarningsAsErrors=true)