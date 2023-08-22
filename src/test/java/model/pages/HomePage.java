package model.pages;

import common.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    public HomePage() {

    }

    //closes webdriver
    public void cleanUp() {
        System.out.println(this.getClass().getName() + " cleanUp");
        Util.closeWebDriver();
    }

    //Navigates to home page for ABN lookup
    public void navigateHomePage() {
        Util.getWebDriver().get("https://abr.business.gov.au/");
    }

    //Enters company name in the textbox
    public void enterCompanyNameToSearchABN(String companyName) {
        Util.getWebDriver().findElement(By.id("SearchText")).sendKeys(companyName);
    }

    //Clicks on the searchglass button
    public void clickSearchButton() {
        Util.getWebDriver().findElement(By.id("MainSearchButton")).click();
    }

    //Validates ABN and Status in the search results displayed on screen
    public boolean validateResultsOverVisibleTable(String expectedABN, String expectedStatus) {

        //trim the spaces out of variables for string comparison
        expectedABN = expectedABN.replaceAll("\\s", "");
        expectedStatus = expectedStatus.replaceAll("\\s", "");

        //gets row from the search results table
        List<WebElement> items = Util.getWebDriver().findElements(By.xpath("//tbody/tr"));
        System.out.println("table serach result items = " + items.size());

        //process headings row
        List<WebElement> headings = items.get(0).findElements(By.xpath("./th"));
        int abnPosition = -1;
        abnPosition = findABNIndexInRow(headings);

        if (abnPosition < 0) {
            System.out.println("ABN not part of the table");
            return false;
        }

        //remove th header from the search table results list
        items.remove(0);

        //compare for abn and status in the search results to match with the expected results
        for (WebElement item : items) {
            List<WebElement> details = item.findElements(By.xpath("./td"));
            String actualABN = details.get(abnPosition).findElement(By.xpath("./a")).getText().replaceAll("\\s", "");
            String actualStatus = details.get(abnPosition).findElement(By.xpath("./span")).getText().replaceAll("\\s", "");
            if (actualABN.equals(expectedABN) && actualStatus.equals(expectedStatus)) {
                System.out.println("Found record!!");
                return true;
            }
        }
        return false;
    }

    //navigates to next page on search results
    public void navigateResultsNextPage() {
            Util.getWebDriver().findElement(By.className("PagedList-skipToNext")).click();
        }

    //checks if next page is available for search results
    public boolean isNextPageAvailable() {
        if(Util.getWebDriver().findElement(By.className("PagedList-skipToNext")).isEnabled())
            return true;
        else
            return false;
    }

    //finds position for 'ABN' column header in the search result table
    public int findABNIndexInRow(List<WebElement> headings){
        int abnIndex = -1;
        //find index of ABN in the row
        for (int index = 0; index < headings.size(); index++) {
            if (headings.get(index).getText().equals("ABN")) {
                abnIndex = index;
            }
        }
        return abnIndex;
    }

    //finds position for 'Name' (company name) column header in the search result table
    public int findNameIndexInRow(List<WebElement> headings){
        int nameIndex = -1;
        //find index of Name in the row
        for (int index = 0; index < headings.size(); index++) {
            if (headings.get(index).getText().equals("Name")) {
                nameIndex = index;
            }
        }
        return nameIndex;
    }

    //verifies if the expected ABN is present in the search results and verifies with the expected company name for that ABN
    public boolean searchABNInSearchResultsTableandMatchCompany(String expectedAbnNumber,String expectedCompanyName) {
        expectedAbnNumber = expectedAbnNumber.replaceAll("\\s", "");
        expectedCompanyName = expectedCompanyName.replaceAll("\\s", "");

        List<WebElement> items = Util.getWebDriver().findElements(By.xpath("//tbody/tr"));
        System.out.println("table serach result items = " + items.size());

        //process headings
        List<WebElement> headings = items.get(0).findElements(By.xpath("./th"));
        int abnPosition = -1;
        int namePosition = -1;

     abnPosition = findABNIndexInRow(headings);
     namePosition = findNameIndexInRow(headings);


        if (abnPosition < 0) {
            System.out.println("ABN not part of the table");
            return false;
        }

        //remove th header
        items.remove(0);

        //compare for abn and if present then verify the expected company name
        for (WebElement item : items) {
            List<WebElement> details = item.findElements(By.xpath("./td"));
            String actualAbn = details.get(abnPosition).findElement(By.xpath("./a")).getText().replaceAll("\\s", "");
            if (actualAbn.equals(expectedAbnNumber)) {
                System.out.println("Found!!");
                String actualCompanyName = details.get(namePosition).getText();
                System.out.println("Here is company name = " + actualCompanyName);
                if(actualCompanyName.replaceAll("\\s","").equals(expectedCompanyName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
