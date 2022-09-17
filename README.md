# UserRestfullApiExample
Application USER RESTfull API based on the web Spring Boot:
controller, responsible for the resource named Users.
Has the following fields:
 - Email (required). Add validation against email pattern
 - First name (required)
 - Last name (required)
 - Birth date (required). Value must be earlier than current date
 - Address (optional)
 - Phone number (optional)
Has CRUD functionality and Search for users by birthdate range  with checks that “From” is less than “To”. 
Return a list of objects.

This project was carried out with the aim of self-education and demonstration of the studied technologies:
1. Validation fields (including custom annotations)
2. Exception handlers (including custom exceptions)
3. Code is covered by unit tests using Spring (used JUnit and Mockito libraries)
4. the database was intentionally not used. Data is stored in an array
5. Logging with @Slf4j
6. Docker Containerisation

To run the program, you need to run the command in the terminal in the root folder of the project:

        docker-compose up

The following endpoints are provided for executing the program:

GET localhost:8080/users

POST localhost:8080/users

        {
                "email": "mail@m",
                "firstname": "fn",
                "lastname": "ln",
                "birthDate": "2002-05-09",
                "address": "address1",
                "phoneNumber": "097 777 7777"
        }
    
PUT localhost:8080/users/1

    {
        "email": "mail@m.com",
        "firstname": "rename",
        "lastname": "ln",
        "birthDate": "2002-05-09",
        "address": "address1",
        "phoneNumber": "097 777 7777"
    }
    
GET localhost:8080/user?from=1999-01-01&to=2004-01-01

DELETE localhost:8080/users/1
