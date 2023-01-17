# PMS-authorization-microservice

Authorization Microservice is used for both authentication and authorization. The authentication process is used to verify the identity of a user by validating their credentials (username and password) and generating a JWT token upon successful validation. The generated JWT token can then be used for authorization, where it is passed as a Bearer token in the Authorization header while making requests to other microservices. The other microservices can then use the token to validate the identity of the user and determine whether they have access to the requested resources or not.

------------


###  Endpoints
###### /authenticate
- Method: POST
- Request Body: AuthRequest object containing username and password fields
- Description: This endpoint is used to authenticate a user and generate a JWT token. It takes a AuthRequest object as input, which contains the username and password of the user. It validates the user credentials and if they are correct, it generates a JWT token. If the credentials are incorrect, it throws a ResourceNotFound exception with the message "user not found".
###### /authorize
- Method: GET
- Request Header: Authorization with the value Bearer <JWT>
- Description: This endpoint is used to authorize a user by validating the JWT token. It takes the JWT token as a request header with the key Authorization and value Bearer <JWT>. It extracts the username from the token and validates it against the user details. If the token is valid, it returns a ResponseEntity with true and HttpStatus.OK. If the token is invalid, it returns a ResponseEntity with false and HttpStatus.FORBIDDEN.

------------


### Features
- Anonymous access to generate JWT
- Token expiration time of 30 minutes
- Customizable token expiration time

------------


### Getting Started
- Clone the repository: git clone https://github.com/ramkishore417/PMS-authorization-microservice.git
- Build the project: mvn clean install
- Run the application: mvn spring-boot:run
- Access the token generation endpoint at http://localhost:8001/authenticate

------------


### Prerequisites
- Java 8 or later
- Maven
- Built With
- Spring Boot
- JWT (Java JWT: JSON Web Token for Java)

------------


### Authors
[Ramkishore](https://github.com/ramkishore417 "Ramkishore")

------------

### Additional Information
- The token expiration time can be configured in the application.properties file.
- This microservice can be used in conjunction with other microservices that require anonymous access.
- The JWT generated can be passed as a Bearer token in the Authorization header while making requests to other microservices.
- The service can also be deployed on cloud platforms like AWS, Heroku, Azure, Google Cloud, etc.
