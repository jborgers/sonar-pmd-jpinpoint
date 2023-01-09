# Sonar PMD custom plugin for Java

This SonarQube plugin enables adding custom PMD rules for Java. 
PMD version 6.x.

## How to add your own custom pmd rules

Add/replace the PMD rules file with name `pmd-custom-java.xml` in `src/main/resources/com/jpinpoint/pmd/rules/`.

## How to set the plugin description as shown in SonarQube
In `plugin.properties` replace:

    plugin.description=Custom PMD rules

with a description of your custom rules, like:

    plugin.description=MyCompany specific Java rules

## How to build and install

Simply do a:

    mvn clean package

and copy the generated JAR (in `target`) to the `extensions/plugins` directory of SonarQube. 
Finally, restart Sonar.

Once Sonar is up and running again, the new rules are available, yet they need activation.
To activate the rules, change the quality profile(s) through the Sonar administration interface.


## How the build works

Apart from the PMD ruleset, a Sonar-specific rule configuration is required. 
This configuration is generated from the PMD ruleset, using the XSLT `src/main/xslt/create-rules-from-pmd.xsl`. 
Both the PMD ruleset and the generated Sonar configuration (`sonar-pmd-custom-java.xml`) are then bundled into the plugin JAR, together with some minimal bootstrapping code.

The Java code in this plugin is based on the sample [sonar-pmd-extension-plugin](https://github.com/SonarSource/sonar-examples/tree/master/plugins/sonar-pmd-extension-plugin)
