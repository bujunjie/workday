#!/bin/bash
mvn exec:java -Dexec.mainClass="org.hsqldb.Server" -Dexec.args="-database.0 file:src/main/resources/data/hdb -dbname.0 xdb" &
