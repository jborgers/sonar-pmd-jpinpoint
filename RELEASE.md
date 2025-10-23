# Sonar PMD jPinpoint plugin for Java and Kotlin

This SonarQube plugin enables adding jPinpoint PMD rules for Java and Kotlin. 
This project is sponsored by [Rabobank](https://www.rabobank.com/).


## How to update the jPinpoint rules

Replace the PMD rules files with name 
* `jpinpoint-rules-java.xml` in `java-pmd7/src/main/resources/com/jpinpoint/pmd/rules/jpinpoint-rules-java.xml`.
* `jpinpoint-rules-kotlin.xml` in `kotlin-pmd7/src/main/resources/com/jpinpoint/pmd/rules/jpinpoint-rules-kotlin.xml`.

## How to build and install

Simply do a:

    ./mvnw clean package

and copy the generated jars (in `java-pmd7/target` and `kotlin-pmd7/target`) to the `downloads` directory of SonarQube. 
Finally, restart Sonar.

Once Sonar is up and running again, the new rules are available, yet they need activation.
Important: to activate the rules, add new rules to quality profile(s) through the Sonar administration interface.

# How to release

* Update the version in the `java-pmd7/pom.xml` and `kotlin-pmd7/pom.xml` file to the (non-SNAPSHOT) version number.
* Push all changes.
* Create a release in GitHub --- this will trigger a build and release in SonarQube.
  * Type in the new tag name and choose 'create new tag x.y.z on publish'.
  * Do _not_ check "Set as pre-release"
  * Check "set as the latest release"

If you first want to release a SNAPSHOT version, you can do so by creating a pre-release in GitHub.
Create a pre-release in GitHub --- this will _not_ publish the release in SonarQube.
* Type in the new tag name and choose 'create new tag x.y.z-SNAPSHOT on publish'.
* Check "Set as pre-release"
* Do _not_ check "Set as the latest release"