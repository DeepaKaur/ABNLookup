package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.pages.HomePage;
import org.junit.Assert;

public class LookupSteps {

    private HomePage homePage = new HomePage();


    @After
    public void cleanUp() {
        System.out.println(this.getClass().getName() + " cleanUp");
        homePage.cleanUp();
    }

    @Given("A user is on ABR home page")
    public void navigateToABRHome() {
        homePage.navigateHomePage();
    }


    @When("User searches the company name and submits")
    public void userSearchesTheCompanyAndSubmits(DataTable dataTable) {
        String companyName = dataTable.cell(1, 0);
        homePage.searchABN(companyName);
        homePage.clickSearch();
    }

    @Then("Company's Abn, status is displayed")
    public void abnAndStatusIsDisplayed(DataTable dataTable) {
        String abnNumber = dataTable.cell(1, 0);
        String status = dataTable.cell(1, 1);
        System.out.println("Abn : " + abnNumber + "  Status : " + status);
        boolean found = homePage.validateResultsOverVisibleTable(abnNumber, status);

        if (!found) {
            do {
                //check if there are more pages
                boolean nextPage = false;
                nextPage = homePage.isNextPageAvailable();
                if (nextPage) {
                    homePage.navigateResultsNextPage();
                    found = homePage.validateResultsOverVisibleTable(abnNumber, status);
                } else
                    break;
            } while (!found);
        }
        Assert.assertTrue(found);
    }

    @Then("User navigates through search result pages until ABN is found and matches company name")
    public void userNavigatesThroughSearchResultPagesUntilABNIsFound(DataTable dataTable) {
        String abnNumber = dataTable.cell(1, 0);
        String companyName = dataTable.cell(1,1);
        boolean foundABN = false;
        foundABN = homePage.searchABNInSearchResultsTableandMatchCompany(abnNumber,companyName);
        if (!foundABN) {
            do {
                //check if there are more pages
                boolean nextPage = false;
                nextPage = homePage.isNextPageAvailable();
                if (nextPage) {
                    homePage.navigateResultsNextPage();
                    foundABN = homePage.searchABNInSearchResultsTableandMatchCompany(abnNumber, companyName);
                } else
                    break;
            } while (!foundABN);
        }
    }

}
