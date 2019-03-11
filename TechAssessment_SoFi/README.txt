*Required:
- The machine should have Java 1.8v. 
- Google chrome version should be 72.x.

*Instructions to run the scripts: 
- Double click 'ScriptsRunner.bat'. It will run all the automated scripts. 
OR 
- Open the project in eclipse tool and Run testng.xml file as a TestNG suite. 

*Test Results: 
- Test results of the scripts get stored under test-output > Tech_Assessment folder
- Web_API.html is the report for all Web-API scripts.
- Web_UT.html is the report for all Web-UI scripts.

*Automated Tests:
# Web_API:
 - Following endpoints are automated in this project. 
  1. Company - Get Details.(test_scripts\API\Companies_Details)
  2. Search - Get Movies.  (test_scripts\API\Search_Movies)

Along with Happy paths, 401 & 404 error codes scenarios are also automated under the same java file for each endpoint.  

# Web_UI:	
 - Following scenarios are automated in this project.
  1. Invalid Login. (test_scripts\Web)
  2. Adding items in cart. (test_scripts\Web) 

Page Object Model(POM) approach is followed. Each page java file is created under Pages.UI folder with required pages elements stored under respective java file.  

*Config:
- Under the config folder, test.properties stores the Web API & UI sites credentials and URL. 
- Web-UI and API related credentials and data are stored under this file. 
- Further, expected data can be stored under Excel files instead of a properties file and fetched into scripts similar to how is now fetched from a properties file. OR can be fetched from the data source such as Database.
