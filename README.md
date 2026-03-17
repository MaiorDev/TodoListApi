# 📝 TaskFlow API - Spring Boot Task Manager

https://roadmap.sh/projects/todo-list-api

A professional RESTful API built with Spring Boot to manage personal tasks. This project implements secure authentication using **JWT (JSON Web Tokens)** and follows a clean architecture with a modular security structure.

## 🚀 Key Features
* **Secure Authentication:** User registration and login with JWT.
* **CRUD Operations:** Create, Read, Update, and Delete tasks.
* **Ownership Security:** Users can only access and manage their own tasks.
* **Automatic Mapping:** Uses ModelMapper for efficient Entity-DTO conversion.
* **Global Exception Handling:** Standardized API responses for validation and security errors.
* **Validation:** Strict input validation using Jakarta Bean Validation.

## 🛠️ Tech Stack
* **Java 21**
* **Spring Boot 3.4.x**
* **Spring Security & JWT**
* **Spring Data JPA**
* **H2 Database** (In-memory)
* **ModelMapper**
* **Maven**

## 📥 Getting Started

### Prerequisites
* **JDK 21** or higher.
* **Maven 3.8+**
* An IDE (IntelliJ IDEA recommended).

### Installation & Setup
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/tu-usuario/todo-list-api.git](https://github.com/tu-usuario/todo-list-api.git)
    cd todo-list-api
    ```

2.  **Install dependencies:**
    ```bash
    mvn clean install
    ```

3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    The server will start at `http://localhost:8080`.

## 🛣️ API Endpoints

### Authentication
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Login and receive JWT Token |

### Tasks (Requires Bearer Token)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/todos/list` | Get all tasks for the logged user |
| `POST` | `/api/todos/create` | Create a new task |
| `PUT` | `/api/todos/update/{id}` | Update an existing task |
| `DELETE` | `/api/todos/delete/{id}` | Delete a task |

## 🗄️ Database Access
The project uses an H2 in-memory database for rapid development.
* **Console URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:tododb`
* **User:** `sa`
* **Password:** (empty)

## 🏗️ Project Structure
```text
src/main/java/TodoList/API/TodoListApi/
 ┣ 📂 Config        # App configurations (ModelMapper, AppConfig)
 ┣ 📂 Controller    # REST Endpoints
 ┣ 📂 Entity        # Database Entities (JPA)
 ┣ 📂 Model         # DTOs / Beans (Data Transfer Objects)
 ┣ 📂 Repository    # Spring Data JPA Repositories
 ┣ 📂 Security      # Filter, Utils, Config, and Exception Handlers
 ┣ 📂 Service       # Business Logic Layer
 ┗ 📜 TodoListApiApplication.java

Developed with ❤️ by MaiorDev



