
# Microservices E-Commerce Platform

## Overview

This project is a microservices-based e-commerce platform with advanced features such as Role-Based Access Control (RBAC), complex transactions, event handling using Kafka, GraphQL, caching, and centralized logging. The backend is developed using Spring Boot, and the frontend is built with React.js. The project is deployed using Docker and Docker Compose, with a CI/CD pipeline for automated deployment and testing.

## Services

### 1. **User Service**
Handles user authentication, authorization, and management.

- **Endpoints:**
  - `POST /users/register`: Register a new user.
  - `POST /users/login`: Log in a user.
  - `GET /users/{id}`: Get user details.
  - `PUT /users/{id}`: Update user details.
  - `DELETE /users/{id}`: Delete a user.

### 2. **Product Service**
Manages product catalog, including CRUD operations on products.

- **Endpoints:**
  - `GET /products`: List all products.
  - `GET /products/{id}`: Get product details.
  - `POST /products`: Create a new product.
  - `PUT /products/{id}`: Update a product.
  - `DELETE /products/{id}`: Delete a product.

### 3. **Order Service**
Handles order management, including creating and tracking orders.

- **Endpoints:**
  - `GET /orders`: List all orders.
  - `GET /orders/{id}`: Get order details.
  - `POST /orders`: Create a new order.
  - `PUT /orders/{id}`: Update an order.
  - `DELETE /orders/{id}`: Cancel an order.

### 4. **Inventory Service**
Manages inventory levels and tracks product stock.

- **Endpoints:**
  - `GET /inventory`: List all inventory items.
  - `GET /inventory/{id}`: Get inventory details.
  - `POST /inventory`: Add new inventory.
  - `PUT /inventory/{id}`: Update inventory.
  - `DELETE /inventory/{id}`: Remove inventory.

### 5. **Payment Service**
Processes payments and handles payment-related data.

- **Endpoints:**
  - `POST /payments`: Process a payment.
  - `GET /payments/{id}`: Get payment details.

### 6. **Notification Service**
Sends notifications for various events (e.g., order status updates).

- **Endpoints:**
  - `POST /notifications`: Send a notification.
  - `GET /notifications/{id}`: Get notification details.

### 7. **Review Service (GraphQL)**
Allows users to review products.

- **GraphQL Schema:**
  ```graphql
  type Review {
    id: ID!
    productId: ID!
    userId: ID!
    rating: Int!
    comment: String
    createdAt: String
  }

  type Query {
    reviews(productId: ID!): [Review]
    review(id: ID!): Review
  }

  type Mutation {
    addReview(productId: ID!, userId: ID!, rating: Int!, comment: String): Review
    updateReview(id: ID!, rating: Int!, comment: String): Review
    deleteReview(id: ID!): Boolean
  }
  ```

### 8. **Caching Service**
Utilizes Redis for caching frequently accessed data.

## Architecture

- **Frontend:** React.js
- **Backend:** Spring Boot microservices
- **Database:** PostgreSQL (or any other RDBMS)
- **Messaging:** Apache Kafka
- **Caching:** Redis
- **Logging:** ELK Stack (Elasticsearch, Logstash, Kibana)
- **Containerization:** Docker, Docker Compose
- **CI/CD:** Jenkins or GitHub Actions

## Setup and Installation

### Prerequisites

- Docker and Docker Compose
- Java 17+
- Node.js and npm
- Redis
- Kafka

### Running Locally

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/microservices-ecommerce.git
   cd microservices-ecommerce
   ```

2. **Build and start services using Docker Compose:**
   ```bash
   docker-compose up --build
   ```

3. **Access the frontend:**
   - Navigate to `http://localhost:3000` in your browser.

4. **API Documentation:**
   - Access API documentation at `http://localhost:8080/swagger-ui.html`.

### Testing

- **Unit Tests:**
  ```bash
  ./gradlew test
  ```
- **Integration Tests:**
  ```bash
  ./gradlew integrationTest
  ```

## CI/CD Pipeline

- **Continuous Integration:** 
  - Set up to run on each push to the repository. It includes building, testing, and packaging the application.
- **Continuous Deployment:** 
  - Automatically deploys to the staging environment on successful builds.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on the code of conduct, and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Contact

For any inquiries, please contact the project maintainer at [sivakg2000@gmail.com](mailto:sivakg2000@gmail.com).
