# JWT Demo

This is a demo project to learn how the JWT authorization work in springboot environment.

## Working
- First start the mysql service and then start the springboot application.
- The working can be verified in either the swagger-ui or in Postman.
- For Postman, 

Use this route for signup(Takes username, password in json format) ,
```bash
POST http:localhost:8080/auth/signup 
``` 
Use this route for login(Takes username, password in json format),
```bash
POST http:localhost:8080/auth/login
```
This returns the Token as string. Use it for accessing restricted routes like /dashboard.

For /dashboard route(Takes JWTtoken)
```bash
GET http://localhost:8080/dashboard
```