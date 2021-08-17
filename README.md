# aws-demo-rdg
demo code for Contoso Gaming Platform : 

The goal is to build an API to locate the actors in the game. All actors are tagged to the Landmarks and the purpose of the API is to provide routes between landmarks to suggest routes to the actors.  In order to achieve this, the game engine should be able to calculate - 
 
•The distance along certain routes via landmarks.
•The number of different routes between landmarks.

# Prerequisite
1) Install Java 8.
2) Install Git.
3) Install maven.
4) Install tomcat.

# Build and Deployment
- Clone code from git repository.
- do maven build (mvn clean install).
- war file will be created in targetfolder .
- alternatively you can download the war file from S3 bucket (https://demo-contoso.s3.amazonaws.com/aws-demo-rdg-0.0.1-SNAPSHOT.war).
- deploy the war file in tomcat and start the server .
- Alternative you can use STS IDE. 
- Import cloned project as a sptingboot application.
- Run the project in STS as a sptingboot application.
