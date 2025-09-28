# Angular ToDo List Application

Angular implementation of the ToDo List application following the specifications in `docs/frontend/PRD_frontend.md`.

## 🚀 Getting Started

This Angular project will be set up using the frontend setup prompt:

```bash
# Use the setup prompt to create Angular implementation
/setup-project framework=angular
```

## 📋 Requirements

This implementation must follow:
- **[PRD Document](../docs/frontend/PRD_frontend.md)** - Complete UI/UX requirements
- **[API Specification](../docs/ToDoListOpenApi.json)** - Backend integration contract
- **[Visual Mockups](../docs/frontend/mockups/)** - Detailed PNG images of all views

## 🎯 Key Features (Per PRD)

- ✅ Single-screen application (no navigation between views)
- ✅ Dialog-based Create/Edit forms (overlay modals)
- ✅ Top-right "Add Task" button in header
- ✅ Inline Edit/Delete buttons on each task
- ✅ Sidebar with filters, search, and sort options
- ✅ Responsive design for mobile/tablet/desktop
- ✅ Integration with Spring Boot, .NET, or Node.js backends

## 🛠️ Technology Stack

- **Angular 20**
- **TypeScript**
- **Tailwind CSS**
- **Angular Router** (minimal usage due to single-screen design)
- **Angular HttpClient** (API communication)
- **NgRx or Akita** (state management)
- **Jasmine + Karma** (testing)

## 📁 Project Structure

```
angular/
├── README.md
├── angular.json
├── package.json
├── tsconfig.json
├── src/
│   ├── app/
│   │   ├── components/
│   │   │   ├── header/
│   │   │   ├── sidebar/
│   │   │   ├── task-list/
│   │   │   ├── task-item/
│   │   │   ├── create-task-dialog/
│   │   │   └── edit-task-dialog/
│   │   ├── services/
│   │   ├── models/
│   │   └── app.component.ts
│   └── environments/
└── tests/
```

## 🔗 Backend Integration

Configurable for multiple backends:
- **Spring Boot**: http://localhost:8080/api
- **.NET**: https://localhost:5025/api

## 📱 Responsive Design

- **Mobile**: Collapsible sidebar, floating action button
- **Tablet**: Adaptive layout with touch-friendly interactions
- **Desktop**: Full sidebar layout with hover effects

---

**Note**: This directory will be populated when using the setup prompt with `framework=angular`.