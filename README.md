# NPHC-SWE-Challenge
NPHC SWE Take Home Challenge

## Instruction to Setup

**1. Clone the application**

```bash
https://github.com/manas-ku-swain/NPHC-SWE-Challenge.git
```

**2. Build and run the app using maven**

```bash
mvn package
java -jar target/nphc-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /
    
    POST /createEmployee ,/users/upload
    
    GET /api/v1/users/{userId}
    
    PUT /edit/{id},/edit/,/delete/{id}
    
    DELETE /api/v1/users/{userId}


## Testing


## Assumtions
- It's expected from users to use the functionalities as instructed in a fair manner
- Trying to exploit a feature following a negative/abusive testing scenario may result unexpected output 

## Notes
- Negative test cases & Edge cases(if any) are not verified
- Basic validation has been used
