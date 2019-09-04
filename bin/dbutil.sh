#!/bin/bash
docker run -it --rm --link=qwanda_qwanda-db_1:db mysql:latest sh -c 'mysql -h db -u testuser --password=password testdb'
