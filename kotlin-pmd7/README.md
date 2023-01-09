# Sonar PMD custom plugin for Kotlin

This Sonar plugin enables adding custom PMD rules for Kotlin. 
This project is sponsored by [Rabobank](https://www.rabobank.com/).

For PMD version 7.x.

## How to add your own custom pmd rules

Add/replace the PMD rules file with name `pmd7-custom-kotlin.xml` in `src/main/resources/com/jpinpoint/pmd/rules/`.

## How to set the plugin description as shown in SonarQube
In `plugin.properties` replace:

    plugin.description=Custom PMD7 Kotlin rules

with a description of your custom rules, like:

    plugin.description=MyCompany specific Kotlin rules

## How to build and install

Simply do a:

    mvn clean package

and copy the generated JAR (in `target`) to the `extensions/plugins` directory. 
Finally, restart Sonar.

Once Sonar is up and running again, the new rules are available, yet they need activation. 
To activate the rules, change the quality profile(s) through the Sonar administration interface.

## How the build works

Apart from the PMD ruleset, a Sonar-specific rule configuration is required. 
This configuration is generated automatically from the PMD ruleset, using the XSLT `src/main/xslt/create-rules-from-pmd.xsl`. 
Both the PMD ruleset and the Sonar configuration (`sonar-pmd7-custom-kotlin.xml`) are then bundled into the plugin JAR, together with some minimal bootstrapping code.

The Java code in this plugin is based on the sample [sonar-pmd-extension-plugin](https://github.com/SonarSource/sonar-examples/tree/master/plugins/sonar-pmd-extension-plugin)
