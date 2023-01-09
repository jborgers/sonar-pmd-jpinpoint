package com.jpinpoint.sonar;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class PmdJavaExtensionRulesDefinition implements RulesDefinition {
    private final RulesDefinitionXmlLoader xmlLoader;
    private Reader inputReader;

    //@VisibleForTesting
    void setInputReader(Reader reader) {
        this.inputReader = reader;
    }

    public PmdJavaExtensionRulesDefinition(RulesDefinitionXmlLoader xmlLoader) {
        this.xmlLoader = xmlLoader;
        final InputStream input = getClass().getResourceAsStream("/com/jpinpoint/sonar/rules/sonar-pmd-custom-java.xml");
        // input may be null if file not there, in unit test
        if (input != null) {
            inputReader = new InputStreamReader(input);
        }
    }

    @Override
    public void define(Context context) {
        assert(inputReader != null);
        NewRepository repository = context.createRepository("pmd", "java").setName("PMD");
        // see javadoc of RulesDefinitionXmlLoader for the format
        xmlLoader.load(repository, inputReader);
        repository.done();
    }
}

