JWT Demo
============
# Sing-up request
### Example with 'curl'
```
curl -d '{"username": "test", "password": "test", "firstName": "test", "lastName": "test", "email": "test"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/auth/sign-up
```
### Example with 'HTTPpie'
```
http POST http://localhost:8080/api/v1/auth/sign-up username=test password=test fristName=test lastName=test email=test
```
# Sing-in request
### Example with 'curl'
```
curl -d '{"username":"test", "password":"test"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/auth/sign-in
```
### Example with 'HTTPpie'
```
http POST http://localhost:8080/api/v1/auth/sign-in username=test password=test
```

# Run in docker
```
1. Run <mvn clean install> command
2. Run <docker-compose build> from root
3. Run <docker-compose up>
```