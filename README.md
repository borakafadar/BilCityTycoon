# BilCity Tycoon

**BilCity Tycoon** is a 2D desktop campus-building simulation game inspired by the early development of Bilkent University. The game blends strategic planning, budget management, and visual design to provide players with a unique experience where they build and manage their own university campus. Designed for educational and entertainment purposes, BilCity Tycoon challenges players to balance economic constraints while aiming for academic excellence. The game is developed using Java and the libGDX framework, supported by a JSON save system.

---

## 🎓 Overview

* **Game Type:** 2D Simulation / Tycoon
* **Platform:** Desktop (Windows/Linux/Mac)
* **Engine:** [libGDX](https://libgdx.com/)
* **Save:** JSON 
* **Language:** Java
* **Architecture:** MVC (Model-View-Controller)

---

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3.

---
## 🚀 Objective

Your goal is to construct a prosperous and reputable university campus by:

* Strategically placing faculty buildings, dorms, and other facilities
* Managing finances using BilCoins (in-game currency)
* Improving student satisfaction and university reputation
* Navigating random events and resource constraints
* Competing in the global university rankings
* Achieving one of several possible game endings based on your performance

---

## 🧠 Gameplay Features

### ✅ Core Mechanics

* **Time Flow:**

  * Game progresses through academic semesters, each consisting of 3–4 in-game months.
  * Each month lasts approximately 5 minutes in real-time.
* **BilCoin Currency:**

  * Starting budget is fixed.
  * Used for all constructions, upgrades, and repairs.
* **Income Sources:**

  * Tuition fees
  * Dormitory fees
  * Charity donations
  * Sponsorships
  * Cafe/restaurant revenues
* **Expenses:**

  * Utilities
  * Staff wages and pensions
  * Maintenance
  * Student services

### 🏫 Campus Construction

* **Buildings Include:**

  * **Faculties:** Engineering, Science, Social Sciences, Law, Arts, Business, etc.
  * **Other Buildings:** Cafeterias, cafes, dormitories (luxury and common), libraries, gyms, etc.
  * **Decorations:** Trees, fountain, ornamental pool
  * **Upgrades:** Road enhancements, decoration boosts, infrastructure expansions

* **Construction Mechanics:**

  * Buildings must be placed on designated tiles after roads are built.
  * Each building has a build cost, time, and effect on satisfaction and reputation.
  * Visual confirmation and a placement grid system are used.

### 📊 Statistics & Metrics

* **Student Satisfaction:**

  * Affected by decorations, events, and facilities.
  * Directly impacts university reputation.
* **University Reputation:**

  * Determined by satisfaction, number of faculties.
* **Leaderboard:**

  * Displays global ranking based on university reputation and satisfaction.

### 🧨 Special Events

* Random positive and negative events:

  * **Positive:** Donations, lottery wins, grant increases
  * **Negative:** Fires, earthquakes, ventilation failures
* Player choices during events affect outcomes.
* Some events provide options that impact coins, reputation, and satisfaction.

---

## 🖥️ User Interface & UX Design

### Main Screens

* **Welcome Screen:** New Game, Load Game, Settings, Exit
* **Game Screen:** Shows campus map, UI panel, player info, and timeline
* **Store Screen:** Purchase faculties, other buildings, upgrades, and decorations
* **Leaderboard Screen:** Displays university rankings and statistics
* **Settings Screen:** Music/SFX volume sliders, fullscreen toggle
* **Ending Screen:** Displays one of the four possible game outcomes

### Interactive Elements

* Scrollable menus and dropdowns in store
* Grid highlighting for buildable tiles
* Time acceleration (1x, 2x) for speeding up gameplay
* Event notification pop-ups with Stardew Valley-style character visuals

---

## 🏁 Endings

1. **Ultimate Ending:**

   * Must first achieve True Ending
   * Reach #1 globally with max satisfaction and reputation
2. **True Ending:**

   * Rank in top 5 universities with adequate stats
3. **Bankrupt Ending:**

   * Go bankrupt by mismanaging finances
4. **Lawsuit Ending:**

   * Student satisfaction drops too low, triggering a lawsuit

Each ending leads to a detailed statistics screen showing:

* Total students
* BilCoins earned and spent
* Dorm capacity
* Satisfaction & reputation scores
* Time played

---

## 🏗️ Architecture & Class Design

MVC Breakdown

Model Classes: Game logic, backend data management

Building, Faculty, Player, Event, Upgrade, Map, etc.

View Classes: User interface screens using libGDX

WelcomeScreen, GameScreen, StoreScreen, LeaderboardScreen, etc.

Controller Logic: Integrated within view classes and SaveSystem

File Structure
```
└───src
    └───main
        └───java
            └───io
                └───github
                    └───bilcitytycoon
                        │   BilCityTycoonGame.java
                        │   Building.java
                        │   Decoration.java
                        │   Event.java
                        │   Faculty.java
                        │   Leaderboard.java
                        │   Main.java
                        │   Map.java
                        │   MoneyHandler.java
                        │   OtherBuilding.java
                        │   OtherUniversity.java
                        │   PlacedBuilding.java
                        │   Player.java
                        │   Store.java
                        │   Time.java
                        │   Tutorial.java
                        │   University.java
                        │   Upgrade.java
                        │
                        ├───Save
                        │       SaveLoad.java
                        │       SaveManager.java
                        │
                        └───Screens
                            │   EconomyPopUp.java
                            │   EndingScreen.java
                            │   GameScreen.java
                            │   LeaderboardScreen.java
                            │   LoadGameScreen.java
                            │   NewGameScreen.java
                            │   PopUpPanel.java
                            │   SettingsScreen.java
                            │   TutorialScreen.java
                            │   UniversityStatsPopup.java
                            │   WelcomeScreen.java
                            │
                            └───Store
                                    DecorationsStoreScreen.java
                                    FacultiesStoreScreen.java
                                    OtherBuildingsStoreScreen.java
                                    StoreScreen.java
                                    UpgradesStoreScreen.java
```

This structure reflects the modular, MVC-aligned approach with clear separations between game logic, UI screens, and saving functionality.

## 🧰 Technologies Used

| Tool       | Purpose                     |
| ---------- | --------------------------- |
| **Java**   | Core language               |
| **libGDX** | Game engine                 |
| **LWJGL3** | Desktop rendering           |
| **JSON**   | Save/load system            |
| **Gradle** | Build/dependency automation |
|            |                             |

---

## 👨‍👩‍👧‍👦 Development Team

| Name                | Responsibility                                           |
| ------------------- | -------------------------------------------------------- |
| **Bora Kafadar**    | Save system, Store class, Settings screen                |
| **Eylül Ündem**     | UI design, financial methods, Upgrade mechanics          |
| **Vural Şimşir**    | Game and Welcome screens, building placement logic       |
| **Yaşar Delibaş**   | Leaderboard system, Building and Decoration systems      |
| **Zeynel Yıldırım** | Event system, Easter eggs, and additional store features |

---

## 🧪 How to Run the Game

### Prerequisites

* Java JDK 23
* Gradle (included in the source code)

### Clone and Run

```bash
git clone https://github.com/yourusername/BilCityTycoon.git
cd BilCityTycoon
./gradlew desktop:run
```

---

## 🔗 References

* [libGDX Documentation](https://libgdx.com/wiki/)
* Games Referenced:

  * [SimCity](https://en.wikipedia.org/wiki/SimCity)
  * [Cities: Skylines](https://en.wikipedia.org/wiki/Cities:_Skylines)
  * [Hay Day](https://en.wikipedia.org/wiki/Hay_Day)
  * [Stardew Valley](https://en.wikipedia.org/wiki/Stardew_Valley)

---

> 🏫 “Build your dream university. Earn your way to the top. Become the ultimate BilCity Tycoon.”



