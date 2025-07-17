# 🛠️ FixIt Local – Local Service Booking Android App

![Kotlin](https://img.shields.io/badge/Language-Kotlin-blueviolet)
![Firebase](https://img.shields.io/badge/Backend-Firebase-orange)
![UI](https://img.shields.io/badge/UI-Material%20Design-brightgreen)
![Payments](https://img.shields.io/badge/Payments-Razorpay-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)

**FixIt Local** is a dual-role Android app built for users and local service providers. It connects users with nearby verified professionals like plumbers, electricians, etc., and enables providers to register their services. The app supports service search by location and experience, in-app calling, booking with date/time, and secure online payments via Razorpay.

---

## 📱 App Modules

### 👤 User App Features:
- Search professionals by **location**, **service type**, and **experience**
- View **profiles**, **ratings**, and **past work**
- Schedule bookings with **date & time** selection
- Make secure payments through **Razorpay**
- Call service providers directly from the app
- Track and manage previous bookings

### 🧰 Admin (Service Provider) Features:
- Register as a service provider with **name, category, experience, and location**
- Manage personal profile and services
- View and manage incoming booking requests

---

## 🚀 Tech Stack & Tools

| Category            | Technologies Used                          |
|---------------------|--------------------------------------------|
| Language            | Kotlin                                     |
| UI Design           | XML, Material Design Components            |
| Architecture        | Activity + Fragment based navigation       |
| Database            | Firebase Realtime Database                 |
| Authentication      | Firebase Email/Password Auth               |
| Payment Gateway     | Razorpay Android SDK                       |
| IDE & Tools         | Android Studio, Git, GitHub, Postman       |

---

## 🧭 App Workflow
```plaintext
SplashActivity
    ↓
LoginActivity / RegisterActivity (User or Admin)
    ↓
If User:
    → HomeActivity (View Services)
        → SearchFragment (Location / Experience)
        → ProviderProfileFragment (Call or Book)
        → BookingFragment (Pick Date & Time + Pay via Razorpay)
        → MyBookingsActivity

If Admin:
    → AdminDashboardActivity
        → AddServiceActivity
        → ManageBookingsActivity
        → ProfileActivity  
```

## ✨ Key Highlights

- 🔍 Advanced service filtering by **location**, **service type**, and **experience**
- 📞 One-tap **call integration** to contact service providers directly
- 💳 Secure and seamless **Razorpay** payment gateway integration
- 🧾 Real-time **Firebase Authentication** and Database for user/service data
- 📱 Responsive UI built with **Material Design** principles
- 👥 Two-role structure with **separate user and admin panels**
- 💾 User session stored with **SharedPreferences** for offline support

---

## 📈 Project Impact

- Onboarded **20+ verified service providers** across multiple categories
- Enabled **100+ successful bookings** during testing with dynamic scheduling
- Completed **100+ Razorpay test transactions** securely
- Improved overall user experience with intuitive design and smooth navigation
- Reduced manual coordination by automating **booking, payment, and service discovery**

