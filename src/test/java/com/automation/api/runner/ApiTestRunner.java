package com.automation.api.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features/api")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.automation.api.steps"
)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty,html:build/reports/cucumber/api-report.html,json:build/reports/cucumber/api-report.json"
)
public class ApiTestRunner {
}