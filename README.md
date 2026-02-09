# 🏥 Hospital Management System

A full-featured **Role-Based Hospital Management System** developed using **Spring Boot** to streamline and digitalize hospital operations.

The system enables secure management of **patients, doctors, appointments, medical records, and insurance services** with JWT-based authentication and authorization.

---

## 🚀 Key Features

### 🔐 Authentication & Authorization
- User Registration & Login
- JWT Token-Based Security
- Role-Based Access Control (RBAC)
- BCrypt Password Encryption

### 👥 Role Management
- **Admin**
  - Full system access
- **Doctor**
  - Manage appointments & patients
- **Patient**
  - Book appointments & view records

---

## 🧑‍🤝‍🧑 Patient Management

- Register new patients
- Update patient details
- Delete patient records
- View patient medical history
- Link insurance policies

---

## 🧑‍⚕️ Doctor Management

- Add / Update / Delete doctors
- Assign specialization
- View scheduled appointments
- Access patient records

---

## 📅 Appointment Management

- Book appointments
- Assign doctor to patient
- Update appointment status
- Cancel appointments
- Track appointment history

---

## 🧾 Insurance Management

- Add insurance providers
- Create insurance policies
- Assign insurance to patients
- Update policy details

---

## 🏗 Tech Stack

- **Backend:** Spring Boot, Spring MVC  
- **Security:** Spring Security, JWT  
- **Database:** MySQL / PostgreSQL  
- **ORM:** Hibernate, Spring Data JPA  
- **Build Tool:** Maven  
- **API Testing:** Postman  

---

# 🔌 Base URL

---

# 🔑 Authentication Endpoints

| Method | URL | Access | Description |
|--------|-----|--------|-------------|
| POST | `/api/auth/register` | Public | Register new user |
| POST | `/api/auth/login` | Public | Login & receive JWT |

---

# 👥 User Endpoints

| Method | URL | Role | Description |
|--------|-----|------|-------------|
| GET | `/api/users/me` | All Authenticated | Get logged-in user profile |
| GET | `/api/users` | Admin | Get all users |

---

# 🧑 Patient Endpoints

| Method | URL | Role | Description |
|--------|-----|------|-------------|
| GET | `/api/patients` | Admin, Doctor | Get all patients |
| GET | `/api/patients/{id}` | Admin, Doctor, Patient | Get patient by ID |
| POST | `/api/patients` | Admin | Create patient |
| PUT | `/api/patients/{id}` | Admin | Update patient |
| DELETE | `/api/patients/{id}` | Admin | Delete patient |

---

# 🧑‍⚕️ Doctor Endpoints

| Method | URL | Role | Description |
|--------|-----|------|-------------|
| GET | `/api/doctors` | Admin, Patient | Get all doctors |
| GET | `/api/doctors/{id}` | Admin | Get doctor by ID |
| POST | `/api/doctors` | Admin | Add doctor |
| PUT | `/api/doctors/{id}` | Admin | Update doctor |
| DELETE | `/api/doctors/{id}` | Admin | Delete doctor |

---

# 📅 Appointment Endpoints

| Method | URL | Role | Description |
|--------|-----|------|-------------|
| GET | `/api/appointments` | Admin, Doctor | Get all appointments |
| GET | `/api/appointments/{id}` | Admin, Doctor, Patient | Get appointment by ID |
| POST | `/api/appointments` | Patient | Book appointment |
| PUT | `/api/appointments/{id}` | Admin, Doctor | Update appointment status |
| DELETE | `/api/appointments/{id}` | Admin, Patient | Cancel appointment |

---

# 🧾 Insurance Endpoints

| Method | URL | Role | Description |
|--------|-----|------|-------------|
| GET | `/api/insurance` | Admin | Get all policies |
| GET | `/api/insurance/{id}` | Admin, Patient | Get policy by ID |
| POST | `/api/insurance` | Admin | Add policy |
| PUT | `/api/insurance/{id}` | Admin | Update policy |
| DELETE | `/api/insurance/{id}` | Admin | Delete policy |
