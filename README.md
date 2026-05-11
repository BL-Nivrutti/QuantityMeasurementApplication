# Quantity Measurement Application

[![Java](https://img.shields.io/badge/Java-21-orange)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-blue)](https://reactjs.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.x-blue)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

---

## Project Overview

The **Quantity Measurement Application** is an enterprise-grade full-stack application that handles measurement conversions across multiple unit categories — **Length**, **Weight**, **Volume**, and **Temperature**. Built incrementally following industry-standard practices, it evolves from a pure Java OOP solution to a complete microservices-based cloud-deployed application.

---

## Project Structure

```
QuantityMeasurementApplication/
│
├── qma-service/              # Spring Boot backend monolith (UC1–UC17)
├── microservices/            # Microservices architecture (UC21)
│   ├── eureka-server
│   ├── api-gateway
│   ├── auth-service
│   └── qma-service
│
├── frontend-basic/           # HTML/CSS/JS frontend (UC19)
├── frontend-react/           # React + Vite frontend (UC20)
├── database/                 # SQL schema and migrations
├── docker/                   # Docker configurations (UC22)
├── jenkins/                  # Jenkins pipeline (UC22)
└── README.md
```

---

## Tech Stack

### Backend

| Technology            | Purpose                        |
| --------------------- | ------------------------------ |
| Java 21               | Core language                  |
| Spring Boot 3         | REST API framework             |
| Spring Security + JWT | Authentication & Authorization |
| OAuth2 / Google Auth  | Social login                   |
| Spring Cloud          | Microservices infrastructure   |
| Eureka Server         | Service registry               |
| API Gateway           | Routing & load balancing       |
| JPA / Hibernate       | ORM                            |
| JDBC                  | Low-level DB access            |
| MySQL 8               | Relational database            |
| Maven                 | Build tool                     |
| Swagger / OpenAPI     | API documentation              |

### Frontend

| Technology                 | Purpose               |
| -------------------------- | --------------------- |
| HTML5, CSS3, JavaScript    | Basic frontend (UC19) |
| AJAX                       | Async communication   |
| React + Vite               | Modern SPA (UC20)     |
| Axios                      | HTTP client           |
| React Router               | Client-side routing   |
| Tailwind CSS / Material UI | Styling               |

### DevOps

| Technology             | Purpose             |
| ---------------------- | ------------------- |
| Git / GitHub           | Version control     |
| Jenkins                | CI/CD pipeline      |
| Docker                 | Containerization    |
| Render / Railway / AWS | Backend deployment  |
| Vercel                 | Frontend deployment |

---

## UC1–UC22 Implementation Status

| UC   | Feature                         | Branch                                      | Status      |
| ---- | ------------------------------- | ------------------------------------------- | ----------- |
| UC1  | Feet Measurement Equality       | `feature/UC1-FeetMeasurementEquality`       | ✅ Complete |
| UC2  | Feet and Inches Equality        | `feature/UC2-FeetAndInchesEquality`         | ✅ Complete |
| UC3  | Generic Quantity Class          | `feature/UC3-GenericQuantityClass`          | ✅ Complete |
| UC4  | Extended Unit Support           | `feature/UC4-ExtendedUnitSupport`           | ✅ Complete |
| UC5  | Unit Conversion                 | `feature/UC5-UnitConversion`                | ✅ Complete |
| UC6  | Addition of Length Units        | `feature/UC6-AdditionOfLengthUnits`         | ✅ Complete |
| UC7  | Addition with Target Unit       | `feature/UC7-AdditionWithTargetUnit`        | ✅ Complete |
| UC8  | Refactor Unit Enum              | `feature/UC8-RefactorUnitEnum`              | ✅ Complete |
| UC9  | Weight Measurement              | `feature/UC9-WeightMeasurement`             | ✅ Complete |
| UC10 | Generic Quantity with Interface | `feature/UC10-GenericQuantityWithInterface` | ✅ Complete |
| UC11 | Volume Measurement              | `feature/UC11-VolumeMeasurement`            | ✅ Complete |
| UC12 | Arithmetic Operations           | `feature/UC12-ArithmeticOperations`         | ✅ Complete |
| UC13 | Centralized Arithmetic Logic    | `feature/UC13-CentralizedArithmeticLogic`   | ✅ Complete |
| UC14 | Temperature Measurement         | `feature/UC14-TemperatureMeasurement`       | ✅ Complete |
| UC15 | N-Tier Architecture             | `feature/UC15-NTierArchitecture`            | ✅ Complete |
| UC16 | Database Integration (JDBC)     | `feature/UC16-DatabaseIntegration`          | ✅ Complete |
| UC17 | Spring Boot Backend             | `feature/UC17-SpringBackend`                | ✅ Complete |
| UC18 | Google Authentication           | `feature/UC18-GoogleAuthentication`         | 🔜 Planned  |
| UC19 | HTML/CSS/JS Frontend            | `feature/UC19-FrontendHTMLCSSJS`            | 🔜 Planned  |
| UC20 | React Frontend                  | `feature/UC20-ReactFrontend`                | 🔜 Planned  |
| UC21 | Microservices Architecture      | `feature/UC21-MicroservicesArchitecture`    | 🔜 Planned  |
| UC22 | CI/CD and Deployment            | `feature/UC22-CICDAndDeploymentCommit`      | 🔜 Planned  |

---

## Architecture Diagram

```
                        ┌─────────────────────────────────────────┐
                        │           CLIENT LAYER                   │
                        │  React Frontend (Vercel)                 │
                        │  HTML/CSS/JS Frontend                    │
                        └──────────────┬──────────────────────────┘
                                       │ HTTPS
                        ┌──────────────▼──────────────────────────┐
                        │           API GATEWAY                    │
                        │  Spring Cloud Gateway (Port 8080)        │
                        └──────┬───────────────┬───────────────────┘
                               │               │
               ┌───────────────▼──┐    ┌───────▼──────────────┐
               │   AUTH SERVICE   │    │    QMA SERVICE        │
               │   (Port 8081)    │    │    (Port 8082)        │
               │  JWT + OAuth2    │    │  Conversions + CRUD   │
               └───────────────┬──┘    └───────┬──────────────┘
                               │               │
                        ┌──────▼───────────────▼──────────────────┐
                        │           EUREKA SERVER                  │
                        │  Service Registry (Port 8761)            │
                        └──────────────┬──────────────────────────┘
                                       │
                        ┌──────────────▼──────────────────────────┐
                        │           DATABASE LAYER                 │
                        │  MySQL 8 (quantity_measurement_db)       │
                        └─────────────────────────────────────────┘
```

---

## Branch Strategy

```
main (production-ready)
 └── dev (integration branch)
      ├── feature/UC1-FeetMeasurementEquality
      ├── feature/UC2-FeetAndInchesEquality
      ├── feature/UC3-GenericQuantityClass
      ├── ...
      └── feature/UC22-CICDAndDeploymentCommit
```

---

## Git Workflow

1. All development happens on feature branches created from `dev`
2. Feature branches follow naming: `feature/UCX-FeatureName`
3. Commit messages follow: `[Nivrutti] <description>`
4. Feature branches are merged into `dev` after completion
5. `dev` is merged into `main` for production releases
6. Never push directly to `main`

---

## Backend Setup

### Prerequisites

- Java 21+
- Maven 3.9+
- MySQL 8+

### QMA Service — Spring Boot Monolith (UC1–UC17)

```bash
cd qma-service
mvn clean install
mvn spring-boot:run
```

Application starts on `http://localhost:8080`

### Microservices (UC21)

```bash
# 1. Start Eureka Server first
cd microservices/eureka-server
mvn spring-boot:run

# 2. Start API Gateway
cd microservices/api-gateway
mvn spring-boot:run

# 3. Start Auth Service
cd microservices/auth-service
mvn spring-boot:run

# 4. Start QMA Service
cd microservices/qma-service
mvn spring-boot:run
```

---

## Frontend Setup

### Basic Frontend (UC19)

```bash
cd frontend-basic
# Open index.html in browser or use Live Server
```

### React Frontend (UC20)

```bash
cd frontend-react
npm install
npm run dev
```

---

## API Documentation

Swagger UI available at: `http://localhost:8080/swagger-ui.html`

### Key Endpoints

| Method | Endpoint                      | Description            |
| ------ | ----------------------------- | ---------------------- |
| POST   | `/api/auth/register`          | Register new user      |
| POST   | `/api/auth/login`             | Login and get JWT      |
| POST   | `/api/quantity/convert`       | Convert between units  |
| GET    | `/api/quantity/history`       | Get conversion history |
| GET    | `/api/quantity/history/{cat}` | History by category    |
| GET    | `/api/quantity/health`        | Health check           |

---

## Database Setup

```sql
CREATE DATABASE quantity_measurement_db;
USE quantity_measurement_db;
-- Tables are auto-created by JPA/Hibernate on startup
-- Or run: database/schema.sql for manual setup
```

---

## Deployment Steps

### Backend (Render/Railway)

1. Push to GitHub
2. Connect repository to Render/Railway
3. Set environment variables:
   - `DB_USERNAME`, `DB_PASSWORD`
   - `JWT_SECRET`
   - `GOOGLE_CLIENT_ID`, `GOOGLE_CLIENT_SECRET`
4. Deploy from `qma-service/` directory

### Frontend (Vercel)

1. Push `frontend-react/` to GitHub
2. Import project in Vercel
3. Set `VITE_API_URL` environment variable
4. Deploy

---

## Screenshots

> _Screenshots will be added after deployment_

---

## Future Enhancements

- [ ] Add more unit categories (Energy, Pressure, Speed)
- [ ] Mobile app (React Native)
- [ ] GraphQL API
- [ ] Redis caching
- [ ] Kubernetes deployment
- [ ] Unit conversion history analytics dashboard
- [ ] Multi-language support (i18n)

---

## License

MIT License — see [LICENSE](LICENSE) for details.

---

_Developed by Nivrutti | Enterprise-grade incremental development following UC1–UC22 roadmap_
