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


## 📮 Postman Collection

API testleri için hazırladığım Postman koleksiyonunu aşağıdaki bağlantıdan indirebilirsiniz:

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/YOUR_COLLECTION_ID)

### Koleksiyon Özellikleri:
- Tüm API endpoint'leri için örnek istekler
- Ortam değişkenleriyle kolay test imkanı
- Yetkilendirme örnekleri (Admin/Customer)
- Örnek request/response'lar

### Kurulum:
1. Yukarıdaki butona tıklayarak koleksiyonu içe aktarın
2. Ortam değişkenlerini ayarlayın:
   ```json
   {
     "base_url": "http://localhost:9797",
     "admin_token": "YOUR_ADMIN_JWT",
     "customer_token": "YOUR_CUSTOMER_JWT"
   }

### Steps
```bash
mvn clean package
java -jar target/stock-orders-0.0.1-SNAPSHOT.jar

