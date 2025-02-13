# 🎬 Movie API

### 🚀 A RESTful API for managing movies, allowing users to add, update, retrieve, and delete movie records securely.

---

## 📖 Purpose

The **Movie API** is designed to help users store, organize, and manage movie data. 

Each user can:
- ✅ Add new movies to the database.
- ✅ Retrieve, update, and delete movies.
- ✅ Securely access movie records using **JWT authentication**.
- ✅ Admin users have special permissions for managing movies.

---

## ⚙️ Services and Methods

### 🎥 **Movie Service**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/movies/create` | Adds a new movie (Only Admins). |
| `GET` | `/api/movies/all` | Retrieves all movies. |
| `GET` | `/api/movies/{id}` | Retrieves a specific movie by ID. |
| `PUT` | `/api/movies/update/{id}` | Updates an existing movie (Only Admins). |
| `DELETE` | `/api/movies/delete/{id}` | Deletes a movie (Only Admins). |

---

## 🔒 Security & Authentication
- **JWT Authentication** is implemented to ensure secure access.
- **Roles & Permissions:**  
  - 🎭 **ADMIN** → Can create, update, and delete movies.
  - 🎟️ **USER** → Can only view movies.

---

## 🛠️ Tools and Technologies
- **Java 17**  
- **Spring Boot (Spring Data JPA, Spring Security)**  
- **ModelMapper**  
- **MySQL (for development and testing)**  
- **Swagger (API documentation)**  
- **JWT for authentication**  

---

## 🚀 Installation and Setup
### 🛠️ **1. Clone the repository**
```sh
git clone https://github.com/mrrufat353/MovieAPI.git
cd MovieAPI
