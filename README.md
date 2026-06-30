# 💰 SmartSpend — Personal Finance & Budget Tracker

SmartSpend is a full-stack personal finance management application that helps users track income and expenses, set category-wise monthly budgets, and visualize their spending habits through an interactive dashboard.

Built from the ground up using **Spring Boot, Hibernate/JPA, Spring MVC, Spring Security, and Thymeleaf**, this project demonstrates end-to-end full-stack development — from database design to secure authentication to data visualization.

---

## 🚀 Features

### 🔐 Authentication & Security
- Secure user registration and login powered by **Spring Security**
- Passwords hashed using **BCrypt** — never stored in plain text
- Role-based structure (`USER`, `ADMIN`) for future extensibility
- Custom `UserDetailsService` implementation for database-backed authentication

### 💸 Transaction Management
- Add, edit, and delete income/expense transactions
- Transactions linked to predefined categories (Food, Rent, Travel, Salary, EMI, etc.)
- Each category is tagged as `INCOME` or `EXPENSE`, enabling automatic classification
- Full transaction history view per logged-in user

### 📊 Budget Tracking
- Set monthly budgets per expense category
- Smart duplicate handling — setting a budget for an existing category/month updates it instead of creating duplicates
- View and delete budgets with a clean, organized interface

### 📈 Interactive Dashboard
- Real-time summary cards: **Total Income**, **Total Expense**, and **Balance** for the current month
- Dynamic **pie chart** (powered by Chart.js) visualizing category-wise expense distribution
- Data-driven insights computed directly from custom JPQL aggregate queries — no hardcoded values

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Backend** | Java, Spring Boot, Spring MVC |
| **Security** | Spring Security, BCrypt |
| **ORM / Persistence** | Hibernate, Spring Data JPA |
| **Database** | PostgreSQL |
| **Frontend** | Thymeleaf, Bootstrap 5, Chart.js |
| **Build Tool** | Maven |

---

## 🏗️ Architecture

The project follows a clean, layered architecture for separation of concerns and maintainability:

```
Controller → Service → Repository → Database
```

- **Controller Layer** — Handles HTTP requests and routes them to the appropriate service
- **Service Layer** — Contains all business logic (password encoding, budget duplicate checks, aggregate calculations)
- **Repository Layer** — Spring Data JPA interfaces with custom JPQL queries for reporting
- **Entity Layer** — JPA-mapped domain models (`User`, `Transaction`, `Category`, `Budget`)

```
com.smartSpend
├── controller     → UserController, TransactionController, BudgetController
├── service        → Business logic & interfaces
├── repository     → Spring Data JPA repositories
├── entity         → User, Transaction, Category, Budget
├── enums          → Role, CategoryType
├── config         → SecurityConfig
└── dto            → CustomUserDetails
```

---

## 🗄️ Database Design

The schema is normalized around 4 core entities with clearly defined relationships:

- **User** (1) → (many) **Transaction**
- **User** (1) → (many) **Budget**
- **Category** (1) → (many) **Transaction**
- **Category** (1) → (many) **Budget**

Categories are seeded automatically on application startup via `data.sql`, covering both income sources (Salary, Freelance, Loan, Pocket Money) and expense types (Food, Rent, Travel, Entertainment, Recharge, EMI, Medical, Other).

---

## 📌 Key Technical Highlights

- **Custom JPQL aggregate queries** to compute monthly income/expense totals and category-wise breakdowns directly at the database level for performance
- **Custom Spring `Converter`** to map form-submitted category IDs back into full `Category` entities
- **BCrypt password encoding** integrated through a custom `DaoAuthenticationProvider`
- **Defensive null handling** for users with no transaction history (dashboard gracefully defaults to ₹0)
- **Data integrity safeguards** — one budget per category per user per month, enforced at the service layer

---

## 🎯 Why This Project

SmartSpend was built to demonstrate practical, real-world full-stack skills relevant to enterprise Java development:

- Secure authentication and session management
- Relational database design with proper foreign key relationships
- Business logic separation across a layered MVC architecture
- Data visualization translating raw database records into actionable insights

---

## 👤 Author

**Pratik (Bapurao) Jadhav**
Java Full Stack Developer
📍 Open to relocation across India
🔗 [GitHub](https://github.com/Bapurao-jadhav)

---

## 📄 License

This project is open source and available for educational and portfolio purposes.
