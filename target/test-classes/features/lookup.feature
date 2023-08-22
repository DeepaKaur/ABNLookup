##Author: Deepa Kaur
  #Keywords Summary : ABNLookup Home
@AbnLookupTests
Feature:  ABN Lookup page navigation

  Scenario: ABN Lookup for Automic Group Pty Ltd
    Given A user is on ABR home page
    When User searches the company name and submits
      | company        |
      | Automic PTY LTD |
    Then Company's Abn, status is displayed
      |abn|status|
      | 27 152 260 814 | Active |

  Scenario: ABN Lookup for MARIO BROS PTY LTD
    Given A user is on ABR home page
    When User searches the company name and submits
      | company        |
      | MARIO BROS PTY LTD |
    Then User navigates through search result pages until ABN is found and matches company name
      |ABN| company name|
    |93 118 220 920| The Trustee for MAURO BROTHERS SENIOR SUPER FUND |





