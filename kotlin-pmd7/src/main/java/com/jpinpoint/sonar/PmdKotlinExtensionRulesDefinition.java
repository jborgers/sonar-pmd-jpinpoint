package com.jpinpoint.sonar;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;

import java.io.InputStreamReader;

public class PmdKotlinExtensionRulesDefinition implements RulesDefinition {
    private final RulesDefinitionXmlLoader xmlLoader;

    public PmdKotlinExtensionRulesDefinition(RulesDefinitionXmlLoader xmlLoader) {
        this.xmlLoader = xmlLoader;
    }

    @Override
    public void define(Context context) {
        // we like it to be:
        // NewRepository repository = context.createRepository("pmd-jpinpoint", "kotlin").setName("PMD jPinpoint Kotlin");
        // but this needs to be made possible in sonar-pmd
        NewRepository repository = context.createRepository("pmd-kotlin", "kotlin").setName("PMD Kotlin");
        // see javadoc of RulesDefinitionXmlLoader for the format
        xmlLoader.load(repository, new InputStreamReader(getClass().getResourceAsStream("/com/jpinpoint/sonar/rules/sonar-pmd7-jpinpoint-kotlin.xml")));
        repository.done();
    }
}

