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
- http://localhost:8080/routecount/a/e/2
- http://localhost:8080/routecount/a/a/3
- http://localhost:8080/routecount/A/E/4

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

**Using A Linux EC2 instance**
- Spin up an Linux EC2 T2 micro instance .
- You can keep all SG and network setting default .
- Allow all traffic for inbound rules (this is not suggested but only for demo purpose) .
- You can use existing key pair or create a new one.
- Connect to the instance using terminal . 
- Update operating system by running : sudo yum update -y
- Install open JDK using command : sudo yum install java-1.8.0-openjdk
- Download Springboot jar file to current directory using command "wget https://demo-contoso.s3.amazonaws.com/aws-demo-rdg-0.0.1-SNAPSHOT.jar ."
- Run command to extract jar file : " java -jar aws-demo-rdg-0.0.1-SNAPSHOT.jar" 
- Spring boot appliation should get started in tomcat server 8080 port wthout any error . 
- Grab the public IP of the EC2 instance from AWS management console and use it to connect to application endpoint. 

http://34.201.251.178:8080/pathdistance/ABCDE
![image](https://user-images.githubusercontent.com/50136741/130029083-462309e7-5b1b-47fc-98ed-9352bf471bde.png)


http://34.201.251.178:8080/pathdistance/ABPRDE

![image](https://user-images.githubusercontent.com/50136741/130029137-e9537be5-0e6a-455c-b4f0-988d2eed6657.png)

http://ec2-34-201-251-178.compute-1.amazonaws.com:8080/routecount/a/a/2

![image](https://user-images.githubusercontent.com/50136741/130029262-87725089-d7cd-4e9c-a08c-fab8cd5b4486.png)


# Postman testing using local deployment 

![image](https://user-images.githubusercontent.com/50136741/129712626-49c37669-d391-4442-8c22-3ec649c5ef7f.png)

![image](https://user-images.githubusercontent.com/50136741/129712705-24d16829-3063-4592-a98d-315b4f12d39f.png)

![image](https://user-images.githubusercontent.com/50136741/129712813-ef4eb325-96ec-4d0a-9a54-0334709135e6.png)

![image](https://user-images.githubusercontent.com/50136741/129712850-17cee6f4-c38c-4229-bbaf-200222c5544a.png)


# Assumption

- All the landmark inputs are in upper case, application loads input from properties file while starting the server. Example (AB3, BC9, CD3 etc. is valid and aB3, Vc9 are not).
- Path between 2 landmarks always unidirectional.
- Any landmark appearing more than once in given inputs for finding route distance, will be considered as wrong input.
