# aws-demo-rdg
demo code for Contoso Gaming Platform : 

The goal is to build an API to locate the actors in the game. All actors are tagged to the Landmarks and the purpose of the API is to provide routes between landmarks to suggest routes to the actors.  In order to achieve this, the game engine should be able to calculate - 
 
- The distance along certain routes via landmarks.
- The number of different routes between landmarks.

# Prerequisite
1) Install Java 8.
2) Install Git.
3) Install maven.
4) Install tomcat.
5) Postman tool for API testing

# Build and Deployment
- Clone code from git repository.
- do maven build (mvn clean install).
- war file will be created in targetfolder .
- alternatively you can download the war file from S3 bucket (https://demo-contoso.s3.amazonaws.com/aws-demo-rdg-0.0.1-SNAPSHOT.war).
- deploy the war file in tomcat and start the server .
- Alternative you can use STS IDE. 
- Import cloned project as a sptingboot application.
- Run the project in STS as a sptingboot application.

# Swagger-API
Download from S3 bucket : https://demo-contoso.s3.amazonaws.com/contoso-api.yml 

# Testing steps 
- Ensure that project is deployed successfully in tomcat and server started without any error (Port: 8080)
- Or springboot project started successfully in STS . 
- Open postman and use URL provided in swagger doc.
- Alternatively you can run **JUNIT test cases** with different inputs to understand the edge case scenarions

# Sample postman URL 
- http://localhost:8080/pathdistance/ABCDE
- http://localhost:8080/pathdistance/AE
- http://localhost:8080/pathdetails/a/e/2
- http://localhost:8080/pathdetails/a/a/3
- http://localhost:8080/pathdetails/A/E/4

# Cloud deployment 

**Using AWS Elastic Beanstalk**

- Open AWS console , search for Elastic Beanstalk.
- Click on 'Create application'
- Provide application name (it will be part of deployed URL) . 
- You can add tag as optional . 
- Choose tomcat as platform , it will use version 8.5 internally. 
- Under 'Application code' choose upload your code option and upload the war file either from S3 or from locally downloaded path . 
- Click on 'Create application' (in console you can see the steps that AWS is performing internally). 
- Once deployed health status of application will be green . 
- Copy application base URL and append API URL to it to access the resources . 


![image](https://user-images.githubusercontent.com/50136741/129696275-375c0bea-9590-41ba-ab04-3155b602ad4d.png)
![image](https://user-images.githubusercontent.com/50136741/129696226-3d8f560b-b606-466b-ae76-1b9a0894b5b2.png)
![image](https://user-images.githubusercontent.com/50136741/129696451-86656fcf-654b-4740-bb26-12d796c98d4b.png)
![image](https://user-images.githubusercontent.com/50136741/129696532-bbad7ced-335b-41e4-98aa-c4e26f9d39ed.png)


