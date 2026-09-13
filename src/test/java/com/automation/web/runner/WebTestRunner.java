package com.automation.web.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features/web")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.automation.web.steps"
)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty,html:build/reports/cucumber/web-report.html,json:build/reports/cucumber/web-report.json"
)
public class WebTestRunner {
}