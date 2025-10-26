# 🚗 Vehicle Rental System (Core Java + JDBC)

A console-based application that simulates a real-world vehicle rental platform. Built entirely using **Core Java**, **JDBC**, and **MySQL**, this project demonstrates object-oriented design, database integration, and clean modular architecture — without relying on frameworks.

---

## 🔧 Technologies Used

- **Java (Core + OOP)**
- **JDBC (Java Database Connectivity)**
- **MySQL**
- **Console UI**

---

## 📦 Features

✅ Customer registration with auto-generated IDs  
✅ Dynamic vehicle listing from the database  
✅ Rental booking with duration and cost calculation  
✅ Rental history tracking with timestamps  
✅ Clean menu-driven console interface  
✅ Modular architecture with service and model layers

---

## 🧱 Project Structure


---

## 🗃️ Database Schema (MySQL)

### `customer`
| customer_id | name         |
|-------------|--------------|

### `vehicle`
| vehicle_id | vehicle_name | brand | type  | rent_per_day |
|------------|--------------|-------|-------|--------------|

### `rental`
| rental_id | customer_id | vehicle_id | days | rental_time |

---

## 🚀 How to Run

1. Clone the repository
2. Set up MySQL database and import schema
3. Update DB credentials in `DBConnection.java`
4. Compile and run `Main.java`

---

## 📸 Sample Console Output


---

## 🎯 Learning Outcomes

- JDBC integration with real SQL queries
- Object-oriented design: inheritance, encapsulation, polymorphism
- Clean separation of concerns (model, service, main)
- Timestamp handling and ID generation
- Console-based user experience

---

## 📌 Author

**Manoj Kumar Pinniboyina**  
Ambitious Java Full Stack Developer  
🔗 [LinkedIn](https://www.linkedin.com/in/your-profile)  
🔗 [GitHub](https://github.com/your-username)

---

## 📬 Feedback & Contributions

Feel free to fork, star, or suggest improvements. Let’s build better systems together!
