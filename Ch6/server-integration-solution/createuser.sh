#!/bin/bash

curl -v -X POST localhost:8080/api/v1/public/users -H "Content-type: application/json" -d '{"username":"test@example.com","password":"w1secret$"}'

