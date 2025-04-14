# Stock Order Management System - Backend API

## Overview
Spring Boot backend API for brokerage firm with complete order management including bonus features.

## Features

### Core Features
- Create, list and cancel stock orders
- Track assets and usable quantities
- Admin authorization for all endpoints

### Bonus Features
1. Customer authentication with JWT
2. Role-based authorization (ADMIN/USER)
3. Customer-specific data access
4. Admin order matching endpoint

## Technologies
- Java 21
- Spring Boot 3.x
- Spring Security
- H2 Database
- JWT Authentication
- Maven
- Lombok

## API Endpoints

### Authentication (Bonus)
- `POST /api/auth/login` - Customer login

### Orders
- `POST /api/orders` - Create new order
- `GET /api/orders` - List orders
- `DELETE /api/orders/{orderId}` - Cancel order

### Assets
- `GET /api/assets` - List assets

### Admin (Bonus)
- `POST /api/admin/orders/{orderId}/match` - Match pending order

## Database Schema
- **Customer**: id, username, password, role
- **Asset**: id, assetName, size, usableSize, customerId
- **Order**: id, size, price, side, status, createDate, customerId, assetId

## How to Run

### Requirements
- Java 21 JDK
- Maven 3.6+


```bash
mvn clean package
java -jar target/stock-orders-0.0.1-SNAPSHOT.jar
```

## 📮 Postman Collection

You can download and import the Postman collection for API testing:

📥 [Download Postman Collection (JSON)](./docs/postman/postman_collection.json)

### 📦 Collection Features
- Sample requests for all API endpoints
- Easy testing with environment variables
- Authorization examples for both **Admin** and **Customer** roles
- Sample request/response payloads for reference

### ⚙️ Setup Instructions
1. Download the collection JSON file above and import it into your Postman workspace.
2. Set up the following environment variables:

   ```json
   {
     "base_url": "http://localhost:9797",
     "admin_token": "YOUR_ADMIN_JWT",
     "customer_token": "YOUR_CUSTOMER_JWT"
   }

