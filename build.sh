#!/bin/bash
mvnw generate-sources
mvn clean install -DskipTests=true
mvn eclipse:eclipse
