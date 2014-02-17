#!/bin/sh
MAVEN_OPTS="-Xmx2048M" mvn exec:java -Dexec.mainClass="edu.mit.civic.mediacloud.CommandLineParser" -Dexec.args="'./src/test/resources/sample-docs/multi-country.txt'"
