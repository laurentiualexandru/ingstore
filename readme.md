# IT Store Management System

A Spring Boot application for managing products in an online store. Supports product CRUD operations, price updates, and search by ID or name.


## Tech Stack
- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- H2 Database
- Lombok
- MapStruct
- Vavr
- JUnit 5 & Mockito



## Getting Started

### Prerequisites
- Java 21+
- Maven 3.8+

### Run locally


git clone https://github.com/laurentiualexandru/ingstore.git
cd store-management
mvn spring-boot:run


Run tests
mvn test


EndpointRest API :
- `GET api/products/product?name=?&id=?` – find product
- `POST api/products/product` – add new product
- `PATCH api/products/productPrice` – patch price

Check `src/test/` for unit test coverage.


## Error Handling

Custom exceptions handled via `@ControllerAdvice`:
- `ResourceNotFoundException`
- `ResourceException`

Standardized error response with `ApiError`.

## Author

- **Laurentiu Plesoiu** – Initial work
