import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        tags = "@AbnLookupTests",
        glue = {"steps"},
        plugin = { "pretty", "html:target/html-reports.html" },
        monochrome = true,
        publish = false)

public class TestAbnLookup {
}
