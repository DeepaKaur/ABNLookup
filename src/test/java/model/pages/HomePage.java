package model.pages;

import common.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    public HomePage() {

    }


    public void cleanUp() {
        System.out.println(this.getClass().getName() + " cleanUp");
        Util.closeWebDriver();
    }

    public void navigateHomePage() {
        Util.getWebDriver().get("https://abr.business.gov.au/");
    }


    public void searchABN(String companyName) {
        Util.getWebDriver().findElement(By.id("SearchText")).sendKeys(companyName);
    }

    public void clickSearch() {
        Util.getWebDriver().findElement(By.id("MainSearchButton")).click();
    }

    public boolean validateResultsOverVisibleTable(String abn, String status) {

        abn = abn.replaceAll("\\s", "");
        status = status.replaceAll("\\s", "");
        List<WebElement> items = Util.getWebDriver().findElements(By.xpath("//tbody/tr"));
        System.out.println("table serach result items = " + items.size());

        //process headings
        List<WebElement> headings = items.get(0).findElements(By.xpath("./th"));
        int abnPosition = -1;

        //find index of ABN in the row
        for (int index = 0; index < headings.size(); index++) {
            if (headings.get(index).getText().equals("ABN")) {
                abnPosition = index;
            }
        }
        if (abnPosition < 0) {
            System.out.println("ABN not part of the table");
            return false;
        }

        //remove th header
        items.remove(0);

        //compare for abn and status
        for (WebElement item : items) {
            List<WebElement> details = item.findElements(By.xpath("./td"));
            String verifyabn = details.get(abnPosition).findElement(By.xpath("./a")).getText().replaceAll("\\s", "");
            String verifystatus = details.get(abnPosition).findElement(By.xpath("./span")).getText().replaceAll("\\s", "");
            if (verifyabn.equals(abn) && verifystatus.equals(status)) {
                System.out.println("Found!!");
                return true;
            }
        }
        return false;
    }


    public boolean validateAbnAndCompanynameOverVisibleTable(String abn, String status, String name) {

        abn = abn.replaceAll("\\s", "");
        status = status.replaceAll("\\s", "");
        name = name.replaceAll("\\s","");
        List<WebElement> items = Util.getWebDriver().findElements(By.xpath("//tbody/tr"));
        System.out.println("table serach result items = " + items.size());

        //process headings
        List<WebElement> headings = items.get(0).findElements(By.xpath("./th"));
        int abnPosition = -1;
        int namePosition = -1;

        //find index of ABN in the row
        for (int index = 0; index < headings.size(); index++) {
            if (headings.get(index).getText().equals("ABN")) {
                abnPosition = index;
            }
        }

        //find index of company name in the row
        for (int index = 0; index < headings.size(); index++) {
            if (headings.get(index).getText().equals("Name")) {
                namePosition = index;
            }

        }
        if (abnPosition < 0 || namePosition < 0) {
            System.out.println("ABN/Name not part of the table");
            return false;
        }

        //remove th header
        items.remove(0);

        //compare for abn and status
        for (WebElement item : items) {
            List<WebElement> details = item.findElements(By.xpath("./td"));
            String verifyabn = details.get(abnPosition).findElement(By.xpath("./a")).getText().replaceAll("\\s", "");
            String verifystatus = details.get(abnPosition).findElement(By.xpath("./span")).getText().replaceAll("\\s", "");
            String verifyName = details.get(namePosition).getText().replaceAll("\\s","");
            if (verifyabn.equals(abn.replaceAll("\\s", "")) && verifystatus.equals(status.replaceAll("\\s", ""))) {
                System.out.println("Found!!");
                System.out.println("verifying name " + name + "with result" + verifyName);

                return true;
            }
        }
        return false;
    }

    public void navigateResultsNextPage() {
            Util.getWebDriver().findElement(By.className("PagedList-skipToNext")).click();
        }


    public boolean isNextPageAvailable() {
        if(Util.getWebDriver().findElement(By.className("PagedList-skipToNext")).isEnabled())
            return true;
        else
            return false;
    }

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

    public boolean searchABNInSearchResultsTableandMatchCompany(String abnNumber,String companyName) {
        abnNumber = abnNumber.replaceAll("\\s", "");
        companyName = companyName.replaceAll("\\s", "");

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

        //compare for abn and status
        for (WebElement item : items) {
            List<WebElement> details = item.findElements(By.xpath("./td"));
            String verifyabn = details.get(abnPosition).findElement(By.xpath("./a")).getText().replaceAll("\\s", "");
            if (verifyabn.equals(abnNumber)) {
                System.out.println("Found!!");
                String verifyName = details.get(namePosition).getText();
                System.out.println("Here is company name = " + verifyName);
                if(verifyName.replaceAll("\\s","").equals(companyName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
