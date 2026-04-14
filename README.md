# HelloDoc: Healthcare Management System

A centralized healthcare facility management system built using a 3-tier client-server architecture.

## 🚀 Tech Stack
* **Backend:** Java 17, Spring Boot, Spring JDBC
* **Database:** MySQL 8.x (with HikariCP Connection Pooling)
* **Frontend:** HTML5, CSS3, Vanilla JavaScript (Single-Page Application)
* **Build Tool:** Maven

## 🛠️ Setup & Installation
1.**Database:** Create a schema named `healthcare_db` in MySQL
2. **Configuration:** Update `src/main/resources/application.properties` with your MySQL `username` and `password`].
3. **Run:** Execute the main application class in IntelliJ.
4. **Access:** Open `http://localhost:8080` in your browser

## 📂 Project Structure
* `com.healthcare.controller`: REST API Handlers.
* `com.healthcare.service`: Business Logic.
* `com.healthcare.repository`: Data Access via JDBC.
* `com.healthcare.model`: Entity POJOs.
