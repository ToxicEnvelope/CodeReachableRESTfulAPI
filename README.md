# CodeReachableRESTfulAPI
---
This repository contain a Spring Boot microservice that configured to work with MongoDB as database ;

## Getting Started
---
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
1. Make sure you have Java on you machine, you can user the following command to verify via CLI (command prompt / terminal)
*  java -version
2. Make sure  you have MongoDB on you machine, you can user the following command to verify via CLI (command prompt / terminal)
* mongod --version

## Installing
A step by step series of examples that tell you have to get a development env running

### Java
- Java is the core language in our case, you can download and install by executable from this link:
* https://java.com/en/download/

- or use homebrew on Mac
* brew update
* brew cask install java

### MobgoDB
- Mongodb is our database, you can download it and install by executable from this link:
* https://www.mongodb.com/

- or use the following on Mac
1. Create a new directory on the $ROOT_DIR (make sure to have read/write permissions apply for both directories)
* mkdir -p /data/db
2. Use Homebrew to install
* brew update
* brew install mongodb

- For extra information visit the link below:
* https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/

### MongoDB Comapss
- This is not mandatory but it will make you life easier with a nice GUI structured representation of you mongodb data, you can download Compass client for the link below:
* https://www.mongodb.com/products/compass

## Deployment
---
- In addition, you will have to make sure that your MongoDB is running unless exceptions will appear and the mocroservices will not be able to start.
Use this command to start MongoDB
* mongd
or
* mongo --host 127.0.0.1:27017

## Built With
---
Maven - Dependency Management


## Authors
---
Yahav N. Hoffman - CEO - CodeReachable
