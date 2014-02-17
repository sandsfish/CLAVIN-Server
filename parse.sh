#!/bin/sh

# Run geo-parsing on single file, specified as the only command-line option,
# in quotes if path contains spaces
MAVEN_OPTS="-Xmx2048M" mvn exec:java -Dexec.mainClass="edu.mit.civic.mediacloud.CommandLineParser" -Dexec.args="'$1'"
