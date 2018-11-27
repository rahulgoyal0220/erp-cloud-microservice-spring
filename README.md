# erp-cloud-microservice-spring

1. Build the whole project once by doing mvn clean install at the root directory erp-cloud-microservice-spring/

Start the modules in the below sequence:
1. Config-service - (Configuration server maintaing the configuration for all the modules)
2. discovery-service - (Service registry/discovery) 
3. authentication-service - (Authentication module)
4. employee-service - (Employee- module)
5. gateway-service - (API -gateway that will be used to route the request)


Test-Data

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_HR');
INSERT INTO roles(name) VALUES('ROLE_MANAGER');


Employee Json
{
        "firstName": "Rahul",
        "lastName": "hello",
        "dob": "1998-11-21T05:00:00.000+0000",
        "gender": "F",
        "addressId": 2,
        "email": "rahulgoyal0118@gmail.com",
        "mobile": "7654890",
        "departmentId": 2,
        "jobId": 2
 }
