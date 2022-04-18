#!/usr/bin/env bash

# init
DOCKER_REGISTRY=#{DOCKER_REGISTRY}#
BASE_IMAGE=$DOCKER_REGISTRY/openjdk11-alpine:11.0.6
IMAGE_NAME=countryservice
VERSION=1.1.0-SNAPSHOT
#TAG_NAME=$DOCKER_REGISTRY/$LOCAL_IMAGE

# build new image
docker build --build-arg SOURCE_IMAGE=$BASE_IMAGE --no-cache -t $DOCKER_REGISTRY/$IMAGE_NAME:$VERSION .

#docker tag $LOCAL_IMAGE:$LOCAL_VERSION $TAG_NAME:$LOCAL_VERSION

docker push $DOCKER_REGISTRY/$IMAGE_NAME:$VERSION

# find new image
docker images | grep $LOCAL_IMAGE
