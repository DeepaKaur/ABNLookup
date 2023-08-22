# ABNLookup

Scenarios for ABN lookup on https://abr.business.gov.au/
Tech stack - Java, Selenium, Cucumber, JUnit


To run, clone the git repository using following command on the terminal 
Pre-requisite to run - java version 9 or later and maven
  1. git clone https://github.com/DeepaKaur/ABNLookup.git
  2. cd ABNLookup
  3. mvn test-compile
  4. mvn test

Class structure
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
