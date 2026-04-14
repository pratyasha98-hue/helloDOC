# HelloDoc: Healthcare Management System

A centralized healthcare facility management system built using a 3-tier client-server architecture.

## 🚀 Tech Stack
* **Backend:** Java 17, Spring Boot, Spring JDBC
* **Database:** MySQL 8.x (with HikariCP Connection Pooling)
* **Frontend:** HTML5, CSS3, Vanilla JavaScript (Single-Page Application)
* **Build Tool:** Maven

## 🏗️ System Architecture
The application follows the **MVC (Model-View-Controller)** pattern:
1. [cite_start]**Presentation Layer:** Web-based UI using Fetch API for JSON communication[cite: 5, 49].
2. [cite_start]**Application Layer:** - **Controllers:** Handle REST endpoints (e.g., `/api/patients`)[cite: 49, 256].
   - [cite_start]**Services:** Manage business logic and coordination[cite: 49, 251].
   - [cite_start]**Repositories:** Execute manual SQL via `JdbcTemplate`[cite: 41, 49].
3. [cite_start]**Data Layer:** MySQL database (`healthcare_db`) with relational integrity[cite: 52, 68].

## 🛠️ Setup & Installation
1. [cite_start]**Database:** Create a schema named `healthcare_db` in MySQL[cite: 52].
2. [cite_start]**Configuration:** Update `src/main/resources/application.properties` with your MySQL `username` and `password`[cite: 38].
3. **Run:** Execute the main application class in IntelliJ.
4. [cite_start]**Access:** Open `http://localhost:8080` in your browser[cite: 35].

## 📂 Project Structure
* [cite_start]`com.healthcare.controller`: REST API Handlers[cite: 207, 252].
* [cite_start]`com.healthcare.service`: Business Logic[cite: 207, 247].
* [cite_start]`com.healthcare.repository`: Data Access via JDBC[cite: 207].
* [cite_start]`com.healthcare.model`: Entity POJOs[cite: 207].
