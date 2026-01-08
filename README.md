# First REST API - Task 2

## Project Description

This is a RESTful API application built with Spring Boot that provides CRUD (Create, Read, Update, Delete) operations for Product management. The application uses Spring Data JPA with H2 in-memory database for data persistence.




## Project Structure

```
src/main/java/pl/edu/vistula/first_rest_api/
├── FirstRestApiApplication.java          # Main Spring Boot application
└── product/
    ├── api/
    │   ├── dto/
    │   │   ├── ProductRequest.java       # DTO for incoming requests
    │   │   └── ProductResponse.java     # DTO for outgoing responses
    │   └── ProductController.java        # REST controller handling HTTP requests
    ├── domain/
    │   └── Product.java                  # JPA entity representing Product
    ├── exception/
    │   ├── ProductNotFoundException.java # Custom exception
    │   └── ProductExceptionHandler.java  # Global exception handler
    ├── repository/
    │   └── ProductRepository.java        # JPA repository interface
    └── service/
        └── ProductService.java          # Business logic layer
```

## Technologies Used

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Data JPA** - For database operations
- **H2 Database** - In-memory database for development
- **Maven** - Build tool

## Dependencies

- `spring-boot-starter-webmvc` - Web MVC support
- `spring-boot-starter-data-jpa` - JPA and Hibernate support
- `h2` - H2 in-memory database
- `spring-boot-h2console` - H2 web console
- `spring-boot-devtools` - Development tools

## Configuration

The application is configured in `application.properties`:

```properties
# H2 Database Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/console
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## API Endpoints

### 1. Create Product

<img width="1470" height="956" alt="screenshots:post-create-product" src="https://github.com/user-attachments/assets/e5eb9c35-0095-4f4e-8db6-4eee3bfef204" />

- **Method:** `POST`
- **URL:** `http://localhost:8080/products`
- **Request Body:**
```json
{
  "name": "Product Name"
}
```
- **Response:** `201 Created`
```json
{
  "id": 1,
  "name": "Product Name"
}
```

### 2. Get All Products

<img width="1470" height="956" alt="screenshots:get-all-products" src="https://github.com/user-attachments/assets/2476a65c-7056-4248-b073-ba19ded41486" />


- **Method:** `GET`
- **URL:** `http://localhost:8080/products`
- **Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Product 1"
  },
  {
    "id": 2,
    "name": "Product 2"
  }
]
```

### 3. Get Product by ID
<img width="1470" height="956" alt="screenshots:get-product-by-id" src="https://github.com/user-attachments/assets/fab81b44-bc3e-428e-88e0-9943398a74b0" />


- **Method:** `GET`
- **URL:** `http://localhost:8080/products/{id}`
- **Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Product Name"
}
```
- **Error Response (404):** If product not found
```json
"Product with id 1 not found"
```

### 4. Update Product
<img width="1470" height="956" alt="screenshots:put-update-product" src="https://github.com/user-attachments/assets/0f1e6f8a-ceff-41fe-8835-d55386926a8b" />



- **Method:** `PUT`
- **URL:** `http://localhost:8080/products/{id}`
- **Request Body:**
```json
{
  "name": "Updated Product Name"
}
```
- **Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Updated Product Name"
}
```

### 5. Delete Product
<img width="1470" height="956" alt="screenshots:delete-product" src="https://github.com/user-attachments/assets/23343c64-af75-4a41-8482-a5d5d414e4a8" />


- **Method:** `DELETE`
- **URL:** `http://localhost:8080/products/{id}`
- **Response:** `204 No Content`
- **Error Response (404):** If product not found

## How to Run

1. **Prerequisites:**
   - Java 21 installed
   - Maven installed (or use included Maven wrapper)

2. **Build the project:**
   ```bash
   ./mvnw clean install
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   Or run `FirstRestApiApplication.java` from your IDE.

4. **Access the application:**
   - API Base URL: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: (leave empty)

## Testing with Postman

### Example: Create a Product

1. Open Postman
2. Create a new request:
   - Method: `POST`
   - URL: `http://localhost:8080/products`
   - Headers: `Content-Type: application/json`
   - Body (raw JSON):
     ```json
     {
       "name": "Laptop"
     }
     ```
3. Send the request
4. You should receive a response with status `201 Created` and the created product

### Example: Get All Products

1. Method: `GET`
2. URL: `http://localhost:8080/products`
3. Send the request
4. You should receive a list of all products

### Example: Get Product by ID

1. Method: `GET`
2. URL: `http://localhost:8080/products/1`
3. Send the request
4. You should receive the product with id=1

### Example: Update Product

1. Method: `PUT`
2. URL: `http://localhost:8080/products/1`
3. Headers: `Content-Type: application/json`
4. Body (raw JSON):
   ```json
   {
     "name": "Updated Laptop"
   }
   ```
5. Send the request

### Example: Delete Product

1. Method: `DELETE`
2. URL: `http://localhost:8080/products/1`
3. Send the request
4. You should receive status `204 No Content`

## Testing with H2 Console

1. Start the application
2. Navigate to `http://localhost:8080/console`
3. Enter JDBC URL: `jdbc:h2:mem:testdb`
4. Username: `sa`
5. Password: (leave empty)
6. Click "Connect"
7. You can now run SQL queries:
   ```sql
   SELECT * FROM products;
   ```

## Architecture Overview

### Layers

1. **Controller Layer (`ProductController`)**
   - Handles HTTP requests and responses
   - Maps HTTP methods to service methods
   - Returns appropriate HTTP status codes

2. **Service Layer (`ProductService`)**
   - Contains business logic
   - Transforms between domain objects and DTOs
   - Handles exceptions

3. **Repository Layer (`ProductRepository`)**
   - Extends `JpaRepository` for database operations
   - Provides methods: `save()`, `findById()`, `findAll()`, `deleteById()`

4. **Domain Layer (`Product`)**
   - JPA entity representing the database table
   - Annotated with `@Entity`, `@Id`, `@GeneratedValue`

5. **Exception Handling**
   - `ProductNotFoundException`: Custom exception for not found cases
   - `ProductExceptionHandler`: Global exception handler using `@RestControllerAdvice`

## Key Annotations Used

- `@RestController` - Marks the class as a REST controller
- `@Service` - Marks the class as a service component
- `@Repository` - Marks the interface as a repository
- `@Entity` - Marks the class as a JPA entity
- `@Id` - Marks the field as primary key
- `@GeneratedValue` - Auto-generates the ID value
- `@RestControllerAdvice` - Global exception handling

## Notes

- The H2 database is in-memory, so data will be lost when the application stops
- SQL queries are logged to the console (enabled by `spring.jpa.show-sql=true`)
- The application uses `ddl-auto=update` which automatically creates/updates the database schema

## Author

Sagar - Student ID: 61740

## Project Requirements Checklist

✅ REST API with CRUD operations  
✅ Spring Boot project structure  
✅ JPA Entity with annotations  
✅ Repository extending JpaRepository  
✅ Service layer with business logic  
✅ Controller with REST endpoints  
✅ Exception handling  
✅ H2 database configuration  
✅ DTOs for request/response  
✅ .gitignore file  
✅ README.md documentation  

