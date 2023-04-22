#!/bin/bash

# Creates a new SQL migration template.

VERSION="1.0.0"
MARKER=$(date +"%Y%m%d%H%M%S")
FILE_NAME="V${VERSION}.${MARKER}__temp_data.sql"

FLYWAY_LOCATION="./db/migration"
mkdir -p "$FLYWAY_LOCATION"

touch "${FLYWAY_LOCATION}/${FILE_NAME}"

echo "Created a new migration file: ${FILE_NAME}"
