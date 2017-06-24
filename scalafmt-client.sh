#!/bin/sh

PORT=$1
TEXT=$(cat /dev/stdin)
wget -qO - "http://127.0.0.1:${PORT}/?t=${TEXT}" || echo "${TEXT}"
