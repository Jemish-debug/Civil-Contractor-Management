# Civil Contractor Management System

![Java](https://img.shields.io/badge/Java-007396?logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Database%20Connector-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Status](https://img.shields.io/badge/Project%20Status-Active-brightgreen)

A console-based Java application designed to streamline the workflow of civil construction contractors. This system manages client requests, contractor assignments, project templates, and payment tracking to make project coordination efficient and organized.

---

## 🚀 Features

- **Client Registration & Login**
- **Contractor Login & Project Management**
- **Admin Dashboard Controls**
- **Construction Template Selection**
- **Secure Payment Tracking (MySQL Integrated)**

---

## 🛠️ Tech Stack

| Component | Technology |
|----------|------------|
| Language | Java (Core Java OOP) |
| Database | MySQL |
| Connector | JDBC |
| Development | IntelliJ / Eclipse / VS Code |

---

## 📁 Project Structure

```
Civil-Contractor-Management/
│── src/
│   ├── Main.java
│   ├── Admin/
│   ├── Client/
│   ├── Contractor/
│   └── Database/
│
│── Database Screenshots/
│── README.md
```

---

## 🗄️ Database Setup

1. Install MySQL
2. Create a database:
   ```sql
   CREATE DATABASE contractor_db;
   ```
3. Create the required tables (refer to Database Screenshots folder)
4. Update database credentials inside code:
   ```java
   Connection con = DriverManager.getConnection(
       "jdbc:mysql://localhost:3306/contractor_db",
       "YOUR_USERNAME",
       "YOUR_PASSWORD"
   );
   ```

---

## ▶️ Run Instructions

1. Open the project in your IDE
2. Add **mysql-connector-j** JAR to project classpath
3. Ensure MySQL server is running
4. Run **Main.java**
5. Follow terminal menu navigation

---

## 🧪 Example Credentials

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |
| Client | Created at registration | - |
| Contractor | Created at registration | - |

---

## 📌 Future Improvements

- Convert CLI to GUI (JavaFX / Swing)
- Add SMS / Email notifications
- Automated cost estimation module

---

## 🤝 Contributing

Pull Requests are welcome.  
Feel free to open issues and suggest improvements.

---

## 📜 License

This project is created for **educational & demonstration purposes**.

---

Made with ❤️ using **Java**
