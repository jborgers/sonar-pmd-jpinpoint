# Sonar PMD jPinpoint plugins for Java and Kotlin

These SonarQube plugins enable adding the [PMD-jPinpoint-rules](https://github.com/jborgers/PMD-jPinpoint-rules) for responsible programming to SonarQube.

Currently, it has a Java PMD6, a Java PMD7 and Kotlin PMD7 edition. 
The goal is to move to one plugin as soon as the rules are all migrated to PMD7.

This project is sponsored by [Rabobank](https://www.rabobank.com/).

Rules are on performance, sustainability, multi-threading, data mix-up, and more.

Each plugin need the respective PMD plugin (6 or 7) installed in SonarQube. 
So, for supporting Java on PMD6 and Kotlin on PMD7 jPinpoint rules, 4 Sonar plugins are needed.

Supports SonarQube version 9.9.4 - 10.5+

## License
sonar-pmd-jpinpoint is licensed under the [Apache License, Version 2.0](https://github.com/jborgers/sonar-pmd-jpinpoint/blob/master/LICENSE).

## How to update the jPinpoint rules, build and install

See README in the directory of each of the plugins.