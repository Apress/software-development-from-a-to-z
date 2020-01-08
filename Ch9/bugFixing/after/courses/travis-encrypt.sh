#!/bin/bash

usage() { echo -e "Travis Encrypt Script\nUsage:\t$0 \n -r\t<username/repository> \n -e\t<string which should be encrypted>" 1>&2; exit 1; }

while getopts ":r:e:" param; do
  case "${param}" in
    r)
      r=${OPTARG}
      ;;
    e)
      e=${OPTARG}
      ;;
    *)
      usage
      ;;
  esac
done
shift $((OPTIND -1))

if [ -z "${r}" ] || [[ !(${r} =~ [[:alnum:]]/[[:alnum:]]) ]] || [ -z "${e}" ]; then
  usage
fi

key_match="\"key\":\"([^\"]+)\""
key_url="https://api.travis-ci.org/repos/${r}/key"
request_result=$(curl --silent $key_url)

if [[ !($request_result =~ $key_match) ]]; then
  echo "Couldn't retrieve key from ${key_url}. "
  usage
fi

echo -n "${e}" | openssl rsautl -encrypt -pubin -inkey <(echo -e "${BASH_REMATCH[1]}") | openssl base64 -A
echo
