#!/bin/sh

set -e

PORT=$1
CURRENT_DIR=$(dirname `readlink -f $0`)

if [[ $PORT -eq '' ]]
then
    echo 'Port is not specified'
    exit 1
fi

"${CURRENT_DIR}/bin/scalafmt-server" -Dhttp.port=${PORT} &
sleep 3s && echo 'object WarmUp { }' | "${CURRENT_DIR}/scalafmt-client.sh" "${PORT}" >> /dev/null
