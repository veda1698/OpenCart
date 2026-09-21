# OpenCart Web Test Automation Framework

A modular, scalable Test Automation Framework built using **Selenium WebDriver (v4.x)**, **Java 17**, and **TestNG**, implementing the **Page Object Model (POM)** design pattern.

This framework automates core user authentication and account management flows for the **OpenCart** e-commerce application.

---

## 💡 Key Framework Features

* **Page Object Model (POM):** Strict separation between page element locators/actions (`BasePage`, `LoginPage`, `AccountRegisterPage`) and test execution logic (`BaseTest`).
* **Data-Driven Testing:** Integrated with **Apache POI** and TestNG `@DataProvider` to execute login scenarios dynamically using multiple datasets from Excel workbooks.
* **Selenium 4 Architecture:** Utilizes native W3C protocol standardization for direct browser communication and automatic driver management via Selenium Manager.
* **Reporting & Logging:** 
  * Interactive HTML execution reports generated via **ExtentReports**.
  * Detailed execution logging configured using **Log4j2** (`log4j.xml`).
  * Automatic screenshot capture on test failure linked directly to Extent Reports.
* **Centralized Configuration:** Externalized environment parameters (application URLs, browser selection, implicit waits) using standard Java `.properties` files (`config.properties`).
* **Build Management:** Configured with **Apache Maven** for dependency handling and test execution.

---

## 🛠️ Tech Stack & Tools

* **Target Application:** OpenCart (E-Commerce Platform)
* **Language:** Java 17
* **Automation Library:** Selenium WebDriver 4
* **Testing Framework:** TestNG
* **Design Pattern:** Page Object Model (POM)
* **Build Tool:** Apache Maven
* **Reporting & Logs:** ExtentReports, Log4j2
* **Data Utilities:** Apache POI (Excel)
* **Version Control:** Git & GitHub

---

## 🚀 Automated Scenarios Covered

1. **Account Registration Module (`AccountRegisterPageTest`):** 
   * Account creation form completion with dynamic test data.
   * Input field assertions and mandatory field checks.
2. **User Authentication Module (`LoginPageTest`):**
   * Positive login validation with valid user credentials.
   * Negative login assertions for invalid credentials.
   * Data-driven testing running multiple login combinations directly from Excel.