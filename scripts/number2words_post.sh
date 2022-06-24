#!/bin/bash
#

show_usage()
{
    echo "Usage:    "`basename $0`" <protocol://host:port> number"
    echo "Example:  "`basename $0`" http://127.0.0.1:8080 123"
}

if test -z "$1" -o -z "$2"
then
    show_usage >&2
    exit 1
fi

BASE_URL="$1"
VALUE="$2"

URL="${BASE_URL}/v1/converter/words"

set -x
curl -q -sSw "\n\nHTTP code: %{http_code}\n" \
-k --ciphers 'DEFAULT:!DH' \
-X POST \
"${URL}" \
-H "Cache-Control: no-cache" \
-H "Content-Type: application/json" \
-d '{"value":'"${VALUE}"'}'


# End
