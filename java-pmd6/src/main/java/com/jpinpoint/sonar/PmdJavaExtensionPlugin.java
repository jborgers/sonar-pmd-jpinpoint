package com.jpinpoint.sonar;

import org.sonar.api.Plugin;

import java.util.Collections;

public class PmdJavaExtensionPlugin implements Plugin {

    @Override
    public void define(Context context) {
        context.addExtensions(Collections.singleton(PmdJavaExtensionRulesDefinition.class));
    }

    @Override
    public String toString() {
        return "PmdJavaExtensionPlugin";
    }
}
