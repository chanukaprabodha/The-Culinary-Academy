# 🍴 The Culinary Academy - Student Registration System

Welcome to *The Culinary Academy* digital registration system! This standalone application streamlines the student registration process for a premier culinary training institute in Sri Lanka, incorporating secure data handling and an intuitive user interface.

## 🌟 Features

### 👩‍🎓 Student Management
- Add, update, delete, and view student details.
  
### 📚 Culinary Program Management
- Manage (CRUD) culinary programs offered by the academy.

### 🔐 User Authentication
- Secure password encryption using BCrypt.
- Role-based access control (Admin, Admissions Coordinator).

### 📝 Registration Workflow
- Record student interviews, registration dates, and course selections.
- Support multiple course enrollments for each student.
- Record upfront fees for selected programs.

### 🔍 Advanced Queries
- Retrieve students and their enrolled programs using HQL relationship queries.
- Join queries for students registered in all culinary programs.

### ✅ Validation & Security
- Input validation (email, phone, and other fields using RegEx).
- Custom exception handling for errors during registration and login processes.

### 🛠️ Hibernate Implementation
- Relationships (One-to-Many) between students and courses.
- Cascade operations for entity management.
- Lazy and eager fetching techniques.

![Screenshot 2024-11-28 175125](https://github.com/user-attachments/assets/fde82c4a-8c19-4f24-bd69-8f5d981bc494)

## 🚀 Technologies Used

- **Backend**: Hibernate, JPA
- **Frontend**: JavaFX
- **Database**: MySQL (configured via Hibernate property files)
- **Security**: BCrypt password encryption
- **Design Patterns**: Layered architecture, Façade/Factory patterns
- **Development Tools**: IntelliJ IDEA or Eclipse

## 🛠️ Installation & Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/chanukaprabodha/The-Culinary-Academy.git
    ```
2. **Navigate to the project directory**:
    ```bash
    cd The-Culinary-Academy
    ```
3. **Configure the database**:
   - Ensure MySQL is installed and running.
   - Update the Hibernate configuration in the `hibernate.cfg.xml` file.
4. **Run the application**:
    - Import the project into IntelliJ IDEA or Eclipse.
    - Build and execute the application.

## 🎯 Goals of This Project

- 🚀 To digitize and simplify the student registration process.
- 🔒 To ensure secure, reliable, and scalable operations for a growing institution.
- 📊 To demonstrate ORM and Hibernate concepts in a real-world scenario.

## 💡 Key Concepts

- **📋 ORM**: Object-Relational Mapping for managing database entities.
- **🛠️ Hibernate**: Simplifying database interactions with JPA specifications.
- **🔐 Secure Design**: Encryption, validation, and custom error handling for robust functionality.

## 💭 Contact

Feel free to connect with me:

- [🌐 Facebook](https://www.facebook.com/chanuka.gamage.5099)
- [🔗 LinkedIn](https://linkedin.com/in/chanuka-prabodha-a78876234)
- [👾 Reddit](https://www.reddit.com/u/ChanukaGamage)
