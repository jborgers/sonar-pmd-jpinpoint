package com.jpinpoint.sonar;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class PmdJavaExtensionRulesDefinitionTest {

    static final  String testContent = "<rules xmlns:pmd=\"http://pmd.sourceforge.net/ruleset/2.0.0\">\n" +
            "    <rule>\n" +
            "        <key>AvoidApacheCommonsFileItemNonStreaming</key>\n" +
            "        <name>Avoid memory intensive FileItem.get and FileItem.getString</name>\n" +
            "        <internalKey>nl/rabobank/sonar/performance/jpinpoint-rules-pmd7.xml/AvoidApacheCommonsFileItemNonStreaming</internalKey>\n" +
            "        <severity>CRITICAL</severity>\n" +
            "        <description>\n" +
            "            <![CDATA[\n" +
            "            Problem: Use of FileItem.get and FileItem.getString could exhaust memory since they load the entire file into memory&#13;\n" +
            "            Solution: Use streaming methods and buffering.\n" +
            "        (jpinpoint-rules)\n" +
            "            <br/>\n" +
            "            <br/>\n" +
            "                                    More information:\n" +
            "                        \n" +
            "            <a href=\"https://github.com/jborgers/PMD-jPinpoint-rules/tree/master/docs/JavaCodePerformance.md#isio03\">\n" +
            "                            Java Code Performance Details\n" +
            "                        </a>\n" +
            "            ]]>\n" +
            "        </description>\n" +
            "        <tag>jpinpoint-rule</tag>\n" +
            "    </rule>" +
            "</rules>";

    @org.junit.jupiter.api.Test
    void define() {
        final RulesDefinitionXmlLoader xmlLoader = new RulesDefinitionXmlLoader();
        final PmdJavaExtensionRulesDefinition extRulesDef = new PmdJavaExtensionRulesDefinition(xmlLoader);
        final RulesDefinition.Context ctx = new RulesDefinition.Context();
        extRulesDef.setInputReader(new StringReader(testContent));

        extRulesDef.define(ctx);
        final RulesDefinition.Repository repo = ctx.repository("pmd");

        assertEquals("java", repo.language(), "language not java");
        assertEquals("PMD", repo.name(), "name not PMD");
        assertTrue(repo.rules().size() >= 1, "rules size not >= 1");
    }
}