
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
(https://github.com/Samrat-Sinha/Payment-Wallet-Application/blob/main/Payment_Wallet_Project/pom.xml) file.
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
       "password": "sam123"
    }
    
    {
       "mobileNumber": "9856741236",
       "password": "sin123"
    }
    
```


## Video Explainer of flow control
 <a href="https://drive.google.com/file/d/1HTNfkwdR2vCvqFxlcJUkqn5GSB3ofDDi/view?usp=sharing">**Video Drive Link** </a>
 
 
### E-R Diagram Of Online Cab Booking Application
---

<img src="https://user-images.githubusercontent.com/101389007/234064453-9e2e40df-d119-4994-8067-9d84a57bc265.png">


---

## Swagger UI

---

<img src="https://user-images.githubusercontent.com/101389007/234064993-0ad15390-1d44-45e1-840a-680193d1cfd7.png">

---

### Login Controller

---

<img src="https://user-images.githubusercontent.com/101389007/234065816-fb0e5ede-dace-4867-942a-25d810cd78dc.png">

---

### Bank Controller

---

<img src="https://user-images.githubusercontent.com/101389007/234066705-962edbe1-12b1-4d53-8518-558679aa0535.png">

---

### Wallet Controller

---

<img src="https://user-images.githubusercontent.com/101389007/234067372-38ca5b95-2abf-4120-a8fc-bed1338f47cc.png">

---

### Bill Payment Controller

---

<img src="https://user-images.githubusercontent.com/101389007/234068817-08e5e1d2-144f-44b9-bff7-14c2080fab61.png">

---

### Transaction Controller

---

<img src="https://user-images.githubusercontent.com/101389007/234069446-691dbcc9-2dc1-4c0b-aea2-a781c264db2d.png">

---

### Beneficiary Controller

---

<img src="https://user-images.githubusercontent.com/101389007/234069820-01b56dc8-0dc2-498f-abdd-32c4dfc02d40.png">


---

## Thank You for Visiting








