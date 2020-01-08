#!/bin/bash

curl -v -X POST localhost:8080/api/v1/secured/usercourses -H "Authorization: bearer $1" -H "Content-type: application/json" -d '{"courseId":1}'

