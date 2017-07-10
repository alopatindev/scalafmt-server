#!/bin/sh

PORT=$1
HOST='localhost'
INPUT='/dev/stdin'

if [[ $PORT -eq '' ]]
then
    echo 'Port is not specified'
    exit 1
fi

curl -s --upload-file "${INPUT}" "http://${HOST}:${PORT}/" || cat "${INPUT}"
