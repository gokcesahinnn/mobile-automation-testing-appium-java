package test.runners;

import test.stepDefinations.Hooks;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.testng.SkipException;


@CucumberOptions(
        plugin = {"html:target/cucumber-html-report",
                "pretty"
        },
        monochrome = true,
        features = "src/test/java/test/features",
        glue = "test.stepDefinations",
        publish = true
)
public class TestRunner extends Hooks {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        System.setProperty("cucumber.filter.tags", System.getProperty("tags", "@smoke"));
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = {"cucumber"}, dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.updateTestCase(testResult -> testResult.setName(pickleWrapper.toString()));
        if (!pickleWrapper.getPickle().getTags().contains("@ignore")) {
            this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
        } else {
            throw new SkipException("Skipped Test");
        }

    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}
