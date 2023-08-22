# ABNLookup

## Scenarios for ABN lookup on https://abr.business.gov.au/

### Tech stack - Java, Selenium, Cucumber, JUnit

Cucumber - feature file is written in business readable plain english language with test scenarios and step definitionss are written to execute the features, glue options determine the location of the steps. 

Page object model is design pattern in selenium which creates object repository specific to the webpage to store it's web elements. This project contains HomePage which is used to access the webelements on the page such as company name, search button (which are web elements bound to home page) 

### Pre-requisite to run - java version 9 or later and maven
To run, clone the git repository using following command on the terminal 
  1. git clone https://github.com/DeepaKaur/ABNLookup.git
  2. cd ABNLookup
  3. mvn test-compile
  4. mvn test

### Class structure
C:.
├───.idea
├───src
│   ├───main
│   │   └───java
│   │       └───org      
│   │           └───example
│   └───test
│       ├───java
          ├───common
          ├───features
          ├───model
          │     └───pages
          └───steps
        ├───resources
          ├───features
