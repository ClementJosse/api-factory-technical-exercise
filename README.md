# API Factory Technical Exercise

This project is a backend system for an insurance company to manage clients and their contracts. It exposes a RESTful API that allows counselors to create, read, update, and delete clients (persons or companies) and manage contracts associated with them. The API returns JSON and implements validation on dates, emails, phone numbers, and numeric fields. All business rules from the technical exercise specification are implemented, including automatic updates of contract end dates when a client is deleted, cost updates with timestamps, filtering active contracts, and computing the sum of active contract amounts.

---

## 🚀 How to Run Locally

### Prerequisites
- Docker installed (or Java 17 + Maven if running without Docker)

### Option 1: Using Docker
```bash
# Build the Docker image
docker build -t api-factory .

# Run the container
docker run -p 8080:8080 api-factory
```

### Option 2: Using Docker Compose (optional for H2 persistence)

```bash
docker compose up --build
```

### Access Points

- API: **http://localhost:8080/api**

- H2 Console: **http://localhost:8080/h2-console**
  - Driver Class: **org.h2.Driver**

  - JDBC URL: **jdbc:h2:file:/data/h2db**

  - User: **sa**

  - Password: **(empty)**

## Testing

Import the Postman collection **./api-factory.postman_collection.json** to test all endpoints quickly.

## Architecture & Design Choices

The backend API is built with Java and Spring Boot using a layered architecture with controllers, 
services, and repositories. 

Clients and Contracts are modeled as JPA entities with bidirectional relationships,
with careful JSON serialization to avoid recursion. 


H2 was chosen as an embedded database to simplify local setup while ensuring persistence in Docker, allowing easy and consistent execution without external dependencies.

Validation for dates, emails, phone numbers, and numeric fields ensures data integrity. 

All endpoints follow RESTful principles and JSON format, supporting client and contract management, cost updates with timestamps, filtering active contracts, and computing sums efficiently.