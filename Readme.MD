# Cinematica-Project

This project according to the parameters of cinematica: https://cinematica.kg/

## Installation


```bash
git clone https://github.com/muratbek2255/cinematica-demo.git
cd cinematica-demo
```

## Create Postgresql DB
```bash
CREATE DATABASE cinematica_db;

spring.datasource.url=jdbc:postgresql://your_localhost/your_db
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=your_postgres_username
spring.datasource.password=your_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Usage

## 1.Authentication
```auth
    http://localhost:8080/auth/register
          {
            "phoneNumber": "some_number",
            "password": "password"
          }
```
```auth
   http://localhost:8080/auth/authenticate
   {
        "phoneNumber": "some_number",
        "password": "password",
        "deviceInfo": {
            "deviceId": "1",
            "deviceType": "DEVICE_TYPE_ANDROID"
        }
   }
```
```auth
   http://localhost:8080/auth/logout
   {
        "deviceInfo": {
            "deviceId": "1",
            "deviceType": "DEVICE_TYPE_ANDROID"
        }
   }
```

## Rent 
```rent
   http://localhost:8080/rent/{id}
   Get By Id rent
```
```rent
   http://localhost:8080/rent/{id}
   Get By Id rent
```
```rent
   http://localhost:8080/rent/{id}
   Put Add Status rent
```
```rent
   http://localhost:8080/rent/rollback/{id}   
   Put Rollback Status Rent
```

## Payment
```rent
  http://localhost:8000/api/v1/payment:
        Post-method: Payment is checked
```    
      http://localhost:8000/api/v1/payment/check/{id}:
        Post-method: Payment is checked
```
  http://localhost:8000/api/v1/payment
          Put-method:Result: Payment Created!
```              
      http://localhost:8000/api/v1/payment/setStatus/{id}
          Put-method: Result: Your status in payment: STATUS_SUCCESS
``` 
  http://localhost:8000/api/v1/payment/rollbackStatus/{id}
          Put-method: Result: Rollback payment
```           
