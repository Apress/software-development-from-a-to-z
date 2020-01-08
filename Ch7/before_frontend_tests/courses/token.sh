#!/bin/bash

curl -u webapp:test -X POST localhost:8080/oauth/token -H "Content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=test@example.com&password=w1secret$"

