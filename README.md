# HelloDoc: Healthcare Management System

A centralized healthcare facility management system built using a 3-tier client-server architecture.

## 🚀 Tech Stack
* **Backend:** Java 17, Spring Boot, Spring JDBC
* **Database:** MySQL 8.x (with HikariCP Connection Pooling)
* **Frontend:** HTML5, CSS3, Vanilla JavaScript (Single-Page Application)
* **Build Tool:** Maven

## 🏗️ System Architecture
The application follows the **MVC (Model-View-Controller)** pattern:
1. **Presentation Layer:** Web-based UI using Fetch API for JSON communication[cite: 5, 49].
2. **Application Layer:** - **Controllers:** Handle REST endpoints (e.g., `/api/patients`)[cite: 49, 256].
   - **Services:** Manage business logic and coordination[cite: 49, 251].
   - **Repositories:** Execute manual SQL via `JdbcTemplate`[cite: 41, 49].
3. **Data Layer:** MySQL database (`healthcare_db`) with relational integrity[cite: 52, 68].

## 🛠️ Setup & Installation
1.**Database:** Create a schema named `healthcare_db` in MySQL[cite: 52].
2. **Configuration:** Update `src/main/resources/application.properties` with your MySQL `username` and `password`[cite: 38].
3. **Run:** Execute the main application class in IntelliJ.
4. **Access:** Open `http://localhost:8080` in your browser[cite: 35].

## 📂 Project Structure
* `com.healthcare.controller`: REST API Handlers[cite: 207, 252].
* `com.healthcare.service`: Business Logic[cite: 207, 247].
* `com.healthcare.repository`: Data Access via JDBC[cite: 207].
* `com.healthcare.model`: Entity POJOs[cite: 207].
