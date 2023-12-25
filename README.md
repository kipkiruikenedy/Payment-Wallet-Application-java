
# REST API for Payment Wallet Application


#Customers will be able to park their money in the wallet.

#Customer should be able to pay different bills using this wallet.

#They should be able to connect bank account with this payment wallet and add money. 

#Application should allow customers to check the balance, deposit money etc.

## Tech Stack:

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Lombok
* Swagger

## Modules:
	
* Login Module
* Bank Module
* Wallet Module
* Bill Payment Module
* Transaction Module
* Beneficiary Module

## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties]
(https://github.com/kipkiruikenedy/Payment-Wallet-Application-java//pom.xml) file.
* Update the port number, username and password as per your local database config.

```
    server.port=1995

spring.datasource.url=jdbc:mysql://localhost:3306/pay
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

```

## Swagger Deployed link
http://localhost:1995/swagger-ui/index.html#/

### Sample API Response for User Login

`POST   localhost:1995/online_Cab_Booking_Application/login`

* Request Body

```
    {
       "mobileNumber": "7001869682",
       "password": "kenedy123"
    }
    
  
    
```










