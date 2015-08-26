#!/bin/bash
# start datomic
/opt/datomic/bin/transactor /opt/haushalt.properties &
# start haushalt 
cd /opt/haushalt; lein run /opt/server-config.edn
