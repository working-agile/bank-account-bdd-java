package com.workingagile.acsd.bankaccount;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.workingagile.acsd.bankaccount")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-report/bankaccount/cucumber.html," +
        "json:target/cucumber-report/bankaccount/cucumber.json"
)
public class RunBankAccountCucumberTest {
}