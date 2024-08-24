# Sonar PMD jPinpoint plugin for Java

This SonarQube plugin enables adding jPinpoint PMD rules for Java. 
This project is sponsored by [Rabobank](https://www.rabobank.com/).
 
For PMD version 6.55, SonarQube 9.9.4 - 10.5+.

## How to update the jPinpoint rules

Replace the PMD rules file with name `jpinpoint-rules.xml` in `src/main/resources/com/jpinpoint/pmd/rules/`.

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
Both the PMD ruleset and the generated Sonar configuration (`sonar-pmd-jpinpoint.xml`) are then bundled into the plugin JAR, together with some minimal bootstrapping code.

The Java code in this plugin is based on the sample [sonar-pmd-extension-plugin](https://github.com/SonarSource/sonar-examples/tree/master/plugins/sonar-pmd-extension-plugin)

## What to know about PMD to Sonar translation

Basic rule XML elements can be translated mostly 1:1 from PMD to Sonar:

| PMD element                       | Sonar element |
|-----------------------------------|---------------|
| `name`                            | `key` |
| `message`                         | `name` |
| `description` and `externalInfoUrl` | `description` |


PMD rule priority translates to severity in Sonar:

| PMD priority | Sonar severity |
|--------------|----------------|
| `1`            | `BLOCKER`       |
| `2`            | `CRITICAL`      |
| `3`            | `MAJOR`         |
| `4`            | `MINOR`          |
| `5`            | `INFO`           |

Sonar rule elements `type` and `status` can simply be defined as property in PMD properties. 
PMD7 does not allow multiple properties with the same name, so `tags` are combined in PMD and translate to separate sonar `tag` rule elements. 
Example:

    <properties>
        <property name="type" value="BUG" type="String" description="for-sonar"/>
        <property name="status" value="READY" type="String" description="for-sonar"/>
        <property name="tags" value="jpinpoint-rule,performance,sustainability-medium" type="String" description="classification"/>
        <property name="version" value="2.0"/>
        <property name="xpath"><value>...

A PMD property to use in the XPath expression can translate to Sonar `param`. For this, it needs to start with `param-`, example:

    <properties>
        <property name="param-max" value="2" type="String" description="Maximum number of allowed statements"/>
        <property name="version" value="2.0"/>
        <property name="xpath">
        <value><![CDATA[
            //LambdaExpression[count(.//Statement) > number($param-max)]
                ]]></value>
        </property>
This way, it allows for setting the parameter different from the specified default value in the Sonar UI.
