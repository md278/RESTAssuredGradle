package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
        plugin = {"json:C:\\RestAssuredFrameworkGradle\\out\\reports\\jsonReports\\cucumber_report.json",
                "pretty:C:\\RestAssuredFrameworkGradle\\out\\reports\\xmlReports\\cucumber_report.xml",
                "html:C:\\RestAssuredFrameworkGradle\\out\\reports\\htmlReports\\cucumber_report.html"},
        glue = {"stepDefinitions"},//, tags = "@DeletePlace"
        monochrome = true)
public class TestRunner {
}