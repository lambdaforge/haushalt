#!/bin/bash
docker stop haushalt; docker rm haushalt;  docker run -d -v <shared/data>:/opt/data -v <shared/log>:/opt/log --name haushalt -p 8092:8092 kordano/haushalt
