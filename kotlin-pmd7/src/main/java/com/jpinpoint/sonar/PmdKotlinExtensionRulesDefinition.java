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
        NewRepository repository = context.createRepository("pmd-kotlin", "kotlin").setName("PMD-jPinpoint");
        // see javadoc of RulesDefinitionXmlLoader for the format
        xmlLoader.load(repository, new InputStreamReader(getClass().getResourceAsStream("/com/jpinpoint/pmd/rules/pmd7-custom-kotlin.xml")));
        repository.done();
    }
}

