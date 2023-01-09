package com.jpinpoint.sonar;

import org.sonar.api.Plugin;

import java.util.Collections;

public class PmdKotlinExtensionPlugin implements Plugin {

    @Override
    public void define(Context context) {
        context.addExtensions(Collections.singleton(PmdKotlinExtensionRulesDefinition.class));
    }

    @Override
    public String toString() {
        return "PmdKotlinExtensionPlugin";
    }
}
