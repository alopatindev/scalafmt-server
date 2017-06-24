#!/bin/sh

PORT=$1
HOST='127.0.0.1'

if [[ $PORT -eq '' ]]
then
    echo 'Port is not specified'
    exit 1
fi

curl -X POST -H "Content-Type: multipart/form-data" -F "sourceFile=@/dev/stdin" "http://${HOST}:${PORT}/upload"
