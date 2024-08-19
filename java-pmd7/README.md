# Sonar PMD jPinpoint plugin for Java

This SonarQube plugin enables adding jPinpoint PMD rules for Java. 
This project is sponsored by [Rabobank](https://www.rabobank.com/).
 
For PMD version 7.x, SonarQube 9.9.5 - 10.5+.

## How to update the jPinpoint rules

Replace the PMD rules file with name `jpinpoint-rules-pmd7.xml` in `src/main/resources/com/jpinpoint/pmd/rules/`.

## How to build and install

Simply do a:

    mvn clean package

and copy the generated JAR (in `target`) to the `extensions/plugins` directory of SonarQube. 
Finally, restart Sonar.

Once Sonar is up and running again, the new rules are available, yet they need activation.
To activate the rules, change the quality profile(s) through the Sonar administration interface.

