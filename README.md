# We have 2 parts. 

**1st part is Building an springboot application which will talk to the external api.**

1: Under getRepos we have 3 methods.
    
    a: get Repos. 
        - Authorisation Bearer token should come from Postman or Rest client.
    b: get Issues
        - No Authorisation. 
    c: Create Repo  
        - Create a Repo using MY Git Hub Test API KEY. 
        - Authorisation Bearer token is in the application code and NOT exposed to the public/postman/rest client.

    Used GET and POST Methods for Testing purpose.

    2: To run this application: Right click on '_ApiApplication_' and click _run_.
    
    This application will run default port number 8080. i.e LOCALHOST:8080

**2nd part is Running Cucumber Test with Rest assured tool.**
    
    1- Import Maven Dependencies using POM.XML.
    2- Get and Post Services are in the file 'httpServices.java'
    3- Designed 2 feature files
        a: createRepo.feature
            - Scenario1: Create a Repositories (+ve Scenario)
            - Scenario2: Invalid Method name (-ve Scenario)
            - Scenario3: Create a Repositories without Name (-ve Scenario)
        b: getRepos.feature
            - Scenario1: get Github Repos. Used my test API KEY.(+ve Scenario)
            - Scenario2: get service with Invalid Bearer token (-ve Scenario)
            - Scenario3: get service with Empty Bearer token (-ve Scenario)
            - Scenario4: Invalid Method name (-ve Scenario)

respective tags are added to the Feature files.
    
    4: To run the Test
        Goto 'runCukes.java' -> Provide the respective Tags. in this case we can provide @getRepos or @createRepo or BOTH.
            These tags are user defined.
    5: Generated Cucumber HTML File.
        Goto 'target/report/cucumber-reports.html'
        *for review purpose, adding 'cucumber-reports.html' file under 'resources' folder. This is only for Review purpose not for actual framework.