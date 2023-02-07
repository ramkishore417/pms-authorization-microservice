# Pension Management System | Authorization Microservice
### Functional Requirements
- This microservice is used with anonymous access to Generate JWT. 
- Authorization Microservice is used for both authentication and authorization. The authentication process is used to verify the identity of a user by validating their credentials (username and password) and generating a JWT token upon successful validation. The generated JWT token can then be used for authorization, where it is passed as a Bearer token in the Authorization header while making requests to other microservices. The other microservices can then use the token to validate the identity of the user and determine whether they have access to the requested resources or not.

------------


### Security Requirements
- Create JWT 
- Have the token expired after specific amount of time say 30 minutes 
- Has anonymous access to get the token detail


------------

### Client Portal Requirements

- Pension Management Portal must allow a member to Login. Once successfully logged in, the member do the following operations:
- Provide the pensioner detail
- Invoke the ProcessPension microservice to get the pension detail
- UI should receive validation message if the pensioner detail provided as input has invalid data
- Display the processed pension detail on the UI
- The pensioner and pension detail should be saved to the database
- Each of the above operations should reach out to the middleware Microservices that are
- hosted in cloud.

------------


### End Points
###### /authenticate
- Method: POST
- Request Body: AuthRequest object containing username and password fields
- Description: This endpoint is used to authenticate a user and generate a JWT token. It takes a AuthRequest object as input, which contains the username and password of the user. It validates the user credentials and if they are correct, it generates a JWT token. If the credentials are incorrect, it throws a ResourceNotFound exception with the message "user not found".

###### /authorize
- Method: GET
- Request Header: Authorization with the value Bearer
- Description: This endpoint is used to authorize a user by validating the JWT token. It takes the JWT token as a request header with the key Authorization and value Bearer . It extracts the username from the token and validates it against the user details. If the token is valid, it returns a ResponseEntity with true and HttpStatus.OK. If the token is invalid, it returns a ResponseEntity with false and HttpStatus.FORBIDDEN.

