package com.jpinpoint.sonar;

import org.sonar.api.server.rule.RulesDefinition;

import static org.junit.jupiter.api.Assertions.*;

class PmdJavaExtensionRulesDefinitionTest {

    @org.junit.jupiter.api.Test
    void define() {
        final PmdJavaExtensionRulesDefinition extRulesDef = new PmdJavaExtensionRulesDefinition();
        final RulesDefinition.Context ctx = new RulesDefinition.Context();

        extRulesDef.define(ctx);
        final RulesDefinition.Repository repo = ctx.repository("pmd");

        assertEquals("java", repo.language(), "language not java");
        assertEquals("PMD", repo.name(), "name not PMD");
        assertFalse(repo.rules().isEmpty(), "rules is empty");
    }
}