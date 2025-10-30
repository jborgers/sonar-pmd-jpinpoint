package com.jpinpoint.sonar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PmdJavaExtensionRulesDefinition implements RulesDefinition {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmdJavaExtensionRulesDefinition.class);

    static void extractRulesData(NewRepository repository, String xmlRulesFilePath, String htmlDescriptionFolder) {
        try (InputStream inputStream = PmdJavaExtensionRulesDefinition.class.getResourceAsStream(xmlRulesFilePath)) {
            if (inputStream == null) {
                LOGGER.error("Cannot read {}", xmlRulesFilePath);
            }
            else {
                new RulesDefinitionXmlLoader()
                        .load(
                                repository,
                                inputStream,
                                StandardCharsets.UTF_8
                        );
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load PMD RuleSet.", e);
        }
    }

    @Override
    public void define(Context context) {
        // we like to make it:
        // NewRepository repository = context.createRepository("pmd-jpinpoint", "java").setName("PMD-jPinpoint");
        // downside:  it is not compatible with the existing use of @SuppressWarnings("pmd:Rule")
        // and it needs to be made possible in sonar-pmd
        NewRepository repository = context.createRepository("pmd", "java").setName("PMD");
        // see javadoc of RulesDefinitionXmlLoader for the format
        final String sonarRulesXmlFile = "/com/jpinpoint/sonar/rules/sonar-pmd-jpinpoint-java.xml";
        extractRulesData(repository, sonarRulesXmlFile, "/org/sonar/l10n/pmd/rules/pmd");

        repository.done();
    }

}

