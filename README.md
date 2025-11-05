# Employee REST API

### Overview  
This **Spring Boot REST API** manages employee records, allowing creation, reading, updating, deletion, and advanced searching (JPA Specification, HQL, and Native SQL).  
The API uses an **H2 in-memory database**, so data resets on application restart.

---

## Technologies Used
- **Spring Boot 3.3+**
- **Spring Data JPA**
- **H2 Database**
- **Hibernate**
- **JUnit 5 & Mockito**
- **Postman** (for testing)

---

## Base URL
```
http://localhost:8080
```

---

## Employee Schema

| Field      | Type   | Description                |
|-------------|--------|----------------------------|
| id          | Long   | Auto-generated unique ID   |
| firstName   | String | Employee’s first name      |
| lastName    | String | Employee’s last name       |
| email       | String | Unique employee email      |
| phone       | String | Employee’s phone number    |
| address     | String | Employee’s address         |



---

## API Endpoints

### 1.Create Employee (Basic)
**Method:** `POST`  
**URL:** `/api/employees/create/basic`  
**Input Type:** JSON Body  

#### Example Input
```json
{
  "firstName": "Praneeth",
  "email": "praneeth@gmail.com"
}
```

#### Example Output
```json
{
  "id": 1,
  "firstName": "Praneeth",
  "email": "praneeth@gmail.com",
  "lastName": null,
  "phone": null,
  "address": null
}
```

---

### 2.Create Employee (Full)
**Method:** `POST`  
**URL:** `/api/employees/create/full`  
**Input Type:** JSON Body  

#### Example Input
```json
{
  "firstName": "Reddy",
  "email": "reddy@gmail.com",
  "phone": "9876543210"
}
```

#### Example Output
```json
{
  "id": 2,
  "firstName": "Reddy",
  "email": "reddy@gmail.com",
  "phone": "9876543210",
  "lastName": null,
  "address": null
}
```

---

### 3.Fetch Employee by Email
**Method:** `GET`  
**URL:** `/api/employees/email/{email}`  
**Input Type:** Path Param  

#### Example Request
```
GET /api/employees/email/reddy@gmail.com
```

#### Example Output
```json
{
  "id": 2,
  "firstName": "Reddy",
  "email": "reddy@gmail.com",
  "phone": "9876543210"
}
```

---

### 4.Fetch Employee by Name
**Method:** `GET`  
**URL:** `/api/employees/name/{name}`  
**Input Type:** Path Param  

#### Example Request
```
GET /api/employees/name/Praneeth
```

#### Example Output
```json
{
  "id": 1,
  "firstName": "Praneeth",
  "email": "praneeth@gmail.com"
}
```

---

### 5.Update Employee (Last Name, Phone, Address)
**Method:** `PUT`  
**URL:** `/api/employees/update`  
**Input Type:** JSON Body  

#### Example Input
```json
{
  "email": "reddy@gmail.com",
  "lastName": "Kumar",
  "phone": "9876501234",
  "address": "Hyderabad"
}
```

#### Example Output
```json
{
  "id": 2,
  "firstName": "Reddy",
  "lastName": "Kumar",
  "email": "reddy@gmail.com",
  "phone": "9876501234",
  "address": "Hyderabad"
}
```

---

### 6.Update Employee Phone Only
**Method:** `PATCH`  
**URL:** `/api/employees/update/phone`  
**Input Type:** JSON Body  

#### Example Input
```json
{
  "email": "reddy@gmail.com",
  "phone": "9999999999"
}
```

#### Example Output
```json
{
  "id": 2,
  "firstName": "Reddy",
  "email": "reddy@gmail.com",
  "phone": "9999999999"
}
```

---

### 7.Delete Employee by Email
**Method:** `DELETE`  
**URL:** `/api/employees/delete/{email}`  
**Input Type:** Path Param  

#### Example Request
```
DELETE /api/employees/delete/reddy@gmail.com
```

#### Example Output
```
Employee deleted successfully.
```

---

## Search Functionality

### A. JPA Specification Search
**Method:** `GET`  
**URL:** `/api/employees/search/spec?email={email}`  
**Input Type:** Query Param  

#### Example
```
GET /api/employees/search/spec?email=praneeth@gmail.com
```

#### Output
```json
{
  "id": 1,
  "firstName": "Praneeth",
  "email": "praneeth@gmail.com"
}
```

---

### B. HQL (Hibernate Query Language) Search
**Method:** `GET`  
**URL:** `/api/employees/search/hql?name={name}`  
**Input Type:** Query Param  

#### Example
```
GET /api/employees/search/hql?name=Reddy
```

#### Output
```json
{
  "id": 2,
  "firstName": "Reddy",
  "email": "reddy@gmail.com"
}
```

---

### C. Native SQL Search
**Method:** `GET`  
**URL:** `/api/employees/search/native?name={name}`  
**Input Type:** Query Param  

#### Example
```
GET /api/employees/search/native?name=Praneeth
```

#### Output
```json
{
  "id": 1,
  "firstName": "Praneeth",
  "email": "praneeth@gmail.com"
}
```

---

## Testing

| Type              | Tool              | Description                                           |
|-------------------|-------------------|-------------------------------------------------------|
| Unit Tests        | JUnit 5 + Mockito | Tests service and repository logic                    |
| Integration Tests | Spring Boot Test  | Verifies end-to-end API behavior                      |
| Manual Testing    | Postman           | Uses `EmployeeAPI_Assessment.postman_collection.json` |

---

## Run Instructions

1. Open project in **Spring Tool Suite** or **Eclipse**  
2. Run → `EmployeeApiApplication.java` → as **Spring Boot App**  
3. Open browser: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
   - JDBC URL: `jdbc:h2:mem:employee_db`  
   - Username: `sa`  
   - Password: *(blank)*  
4. Use **Postman** to test all endpoints  
   - Import: `EmployeeAPI_Assessment.postman_collection.json`


