Quizzy 

Assignment Submission

⸻

Overview

Quizzy is an Android application developed to demonstrate clean architecture, modern Android practices, and scalable code structure.
The app displays a student dashboard including student details, quiz progress, accuracy metrics, and topic-wise performance using data fetched from a remote API.

The implementation focuses on clarity, maintainability, and extensibility.

⸻

Architecture

The application follows MVVM with a clean separation of concerns.

UI (Fragment)
↓
ViewModel
↓
Repository
↓
API

Architectural Decisions
• MVVM ensures lifecycle awareness and UI state safety
• Repository pattern acts as a single source of truth
• Dependency Injection avoids tight coupling and improves testability

This structure allows the app to scale easily with additional features.

Injected components include:
• Network client
• API service
• Repository
• ViewModels

This approach ensures:
• Loose coupling
• Cleaner code
• Easier testing
• Better scalability

Data Handling
• Student dashboard data is fetched from a remote JSON endpoint
• The response is mapped to strongly typed Kotlin data models
• UI observes data via ViewModel

Data flow is strictly one-directional:

API → Repository → ViewModel → UI
⸻
Dependencies Used 
• AndroidX Core
• AppCompat
• Material Components
• ConstraintLayout
• ViewModel
• LiveData
• Retrofit
• Navigation Library
• Gson
• Hilt (Dependency Injection)
• Kotlin Coroutines
• Firebase Authentication


⸻

Features Implemented
• Student profile summary
• Quiz streak visualization
• Accuracy progress indicator
• Topic-wise performance display
• Clean Material UI implementation
• Navigation Component for screen transitions
• Logout functionality via Firebase

⸻

Future Scope & Improvements

The current solution is intentionally kept simple but is architecture-ready for improvements.

Caching Layer
• Introduce local caching using Room
• Load cached data first, then update from API
• Enable offline support

Domain Layer
• Add a Domain layer with UseCases
• Move business logic out of ViewModel
• Improve testability and scalability

Shimmer Loading UI
• Add shimmer placeholders while data is loading
• Improve perceived performance and UX

Testing
• Unit tests for ViewModel and Repository
• Fake DataSource for test environments

⸻

Conclusion

This submission demonstrates:
• Proper use of modern Android architecture
• Clean and readable code structure
• Scalable design choices
• Alignment with the provided UI and requirements

The project is structured to support future enhancements without major refactoring.


