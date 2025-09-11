# AI Workshop: GitHub Copilot, Spring Boot & .NET, React

## Overview

This repository is designed for hands-on workshops introducing GitHub Copilot and modern full-stack development. It contains parallel implementations of a ToDo List application in both Java (Spring Boot) and .NET, with a React frontend. The goal is to help you learn how to leverage GitHub Copilot for code generation, testing, and documentation in real-world projects.

---

## Repository Structure

```plaintext
.
├── spring-boot/   # Java 21, Spring Boot 3.5.5 backend
├── dotnet/        # .NET 9.0 backend (to be implemented)
├── react/         # React + Vite frontend
├── docs/          # API documentation, OpenAPI specs
├── .gitignore     # Global ignore rules
└── README.md      # This file
```

Each backend and frontend folder contains its own framework-specific `.gitignore` and `README.md`.

---

## Workshop Goals

- **Learn GitHub Copilot**: Use Copilot to generate code, tests, and documentation.
- **Compare Technologies**: See the same app built with Spring Boot and .NET.
- **Modern Dev Practices**: Use containers, automated tests, and OpenAPI.
- **Hands-on Coding**: Follow step-by-step instructions and commit phases.

---

## Technologies Used

- **Java 21, Spring Boot 3.5.5, Maven, Lombok, Testcontainers, PostgreSQL**
- **.NET 9.0, ASP.NET Core, Entity Framework Core, xUnit**
- **React, Vite, Tailwind CSS**
- **Docker, Swagger/OpenAPI**

---

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/vmplacademy/ai-workshop.git
   cd ai-workshop
   ```
2. **Choose your stack**
   - Java: see `spring-boot/README.md`
   - .NET: see `dotnet/README.md` (coming soon)
   - React: see `react/README.md`
3. **Follow the workshop phases**
   - Each backend has a `GETTING_STARTED.md` with step-by-step tasks.

---

## GitHub Copilot: What & Why?

> "GitHub Copilot is an AI pair programmer that helps you write code faster and with less work. It draws context from comments and code, and suggests individual lines and whole functions instantly."

- **Official Docs:** [What is GitHub Copilot?](https://docs.github.com/en/copilot/get-started/what-is-github-copilot)
- **Supported IDEs:** VS Code, Visual Studio, JetBrains IDEs, Neovim
- **Capabilities:**
  - Code completion and suggestions
  - Generate tests and documentation
  - Context-aware code generation
  - Multi-language support

**Example usage:**
```java
// Write a function to reverse a string
// Copilot will suggest the implementation as you type
public String reverse(String input) {
    // ...Copilot suggestion...
}
```

**Learn more:**
- [Getting started with Copilot](https://docs.github.com/en/copilot/get-started)
- [Copilot for Individuals](https://docs.github.com/en/copilot/using-github-copilot/getting-started-with-github-copilot)
- [Copilot for Business](https://docs.github.com/en/copilot/using-github-copilot-in-your-organization)

---

## Code Reference Examples

- **Spring Boot:** See `spring-boot/src/main/java/pl/vm/aiworkshop/api/TaskController.java` for REST API endpoints.
- **.NET:** See `dotnet/TodoList.Api/Controllers/TaskController.cs` (to be implemented).
- **React:** See `react/src/App.jsx` for the main UI logic.
- **OpenAPI:** See `docs/ToDoListOpenApi.json` for the API contract.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Credits

- Inspired by the official [GitHub Copilot documentation](https://docs.github.com/en/copilot/get-started/what-is-github-copilot)
- Created by VM.PL Academy for educational purposes
