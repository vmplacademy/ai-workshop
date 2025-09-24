# 🤖 AI Workshop: Master GitHub Copilot with VM.PL

## 🚀 Overview

This repository is your comprehensive guide to mastering **GitHub Copilot** through hands-on, real-world development. Learn to leverage AI-powered coding assistance while building production-ready ToDo applications with modern full-stack technologies.

**🎯 Core Focus**: Transform how you code with GitHub Copilot's AI assistance across different technology stacks.

---

## 🎪 What You'll Master

### **🤖 GitHub Copilot Superpowers**
- **Code Generation**: Generate entire functions, classes, and API endpoints
- **Smart Completions**: Context-aware code suggestions and autocompletion  
- **Test Automation**: Auto-generate comprehensive unit and integration tests
- **Documentation Magic**: Create README files, API docs, and inline comments
- **Refactoring Assistant**: Modernize and optimize existing codebases
- **Multi-Language Expertise**: Seamlessly work across Java, C#, TypeScript, and more

### **🏗️ Architecture Patterns**
- **Contract-First Development**: OpenAPI-driven backend implementation
- **Clean Architecture**: Layered design with proper separation of concerns
- **Interface-Based Contracts**: Testable, maintainable layer boundaries
- **Domain-Driven Design**: Business-focused development approach

---

## 📁 Repository Structure

```plaintext
.
├── spring-boot/         # Java 21 + Spring Boot 3.5.5 backend
│   ├── README.md             # 📋 Complete project documentation
│   └── src/main/java/...     # 🎯 Contract-first API implementation
├── dotnet/              # .NET 8.0 backend (Clean Architecture)
│   ├── README.md             # 📋 Complete project documentation
│   └── src/...               # 🎯 Interface-based layer contracts
├── react/               # React + Vite + TypeScript frontend  
│   ├── README.md             # 📋 Complete project documentation
│   └── src/components/...    # 🎯 Modern React patterns
├── docs/                # 📖 API documentation & OpenAPI specs
│   └── ToDoListOpenApi.json  # 🔗 Source of truth for API contracts
├── .github/
│   ├── prompts/         # 🎪 Custom VS Code prompts for automation
│   └── instructions/    # 📚 Framework-specific Copilot guidelines
└── README.md           # 👈 You are here
```

**🔑 Each directory includes**:
- Framework-specific `.gitignore` and `README.md`
- Complete documentation with Copilot-driven workshop phases
- Real-world code examples and best practices

---

## 🎯 Workshop Learning Path

### **Phase 1: Setup & Copilot Fundamentals** ⚡
- Configure GitHub Copilot in your IDE
- Learn prompt engineering for code generation
- Master context-driven completions

### **Phase 2: Backend Development with AI** 🏗️
- Generate REST API controllers from OpenAPI specs
- Auto-create service layers and repository patterns
- Build comprehensive test suites with Copilot assistance

### **Phase 3: Frontend Development with AI** 🎨
- Create React components with AI-generated logic
- Build responsive UI with Tailwind CSS assistance
- Implement state management patterns

### **Phase 4: Advanced Copilot Techniques** 🚀
- Custom prompts for domain-specific patterns
- AI-assisted refactoring and optimization
- Documentation generation and maintenance

**💡 Pro Tip**: Each phase includes GitHub Copilot Chat prompts and practical exercises!

---

## 🛠️ Technology Stack

| Layer | Spring Boot Stack | .NET Stack | Frontend Stack |
|-------|-------------------|------------|----------------|
| **Language** | Java 21 | C# 13 (.NET 8.0) | TypeScript |
| **Framework** | Spring Boot 3.5.5 | ASP.NET Core | React 18 + Vite |
| **Database** | PostgreSQL + JPA | PostgreSQL + EF Core | - |
| **Testing** | JUnit 5 + Mockito | xUnit + Moq | Vitest + RTL |
| **Containerization** | Docker + Testcontainers | Docker + Testcontainers | Docker |
| **API Documentation** | SpringDoc OpenAPI | Swashbuckle | - |
| **Build Tool** | Maven | MSBuild | npm/yarn |

**🎯 Copilot Integration Points**:
- Code generation and completions across all languages
- Test automation for Java, C#, and TypeScript
- Documentation generation for APIs and components
- Refactoring assistance and pattern recognition

---

## 🚀 Quick Start Guide

### **Prerequisites**
- **GitHub Copilot Subscription** ([Get Started](https://github.com/features/copilot))
- **VS Code** with GitHub Copilot extension installed
- **Git** and your preferred development environment

### **1. Clone & Explore**
```bash
git clone https://github.com/vmplacademy/ai-workshop.git
cd ai-workshop

# Explore the structure
ls -la                     # See all frameworks
cat docs/ToDoListOpenApi.json  # Review the API contract
```

### **2. Choose Your Adventure** 🎪
| Path | Description | Time | Skill Level |
|------|-------------|------|-------------|
| 🟢 **Java Path** | `spring-boot/README.md` | 2-3 hours | Beginner-Friendly |
| 🔵 **.NET Path** | `dotnet/README.md` | 2-3 hours | Intermediate |
| 🟡 **React Path** | `react/README.md` | 1-2 hours | Beginner-Friendly |
| 🔴 **Full-Stack** | All three paths | 4-6 hours | Advanced |

### **3. Activate Copilot Power** ⚡
Each project `README.md` includes:
- 📋 Copilot prompts for every step
- 🎯 Context-specific code generation
- 🧪 AI-assisted testing strategies
- 📖 Documentation automation
- 🎯 Context-specific code generation
- 🧪 AI-assisted testing strategies
- 📖 Documentation automation

---

## 🤖 GitHub Copilot: Your AI Programming Partner

### **🔥 What is GitHub Copilot?**
> **GitHub Copilot is an AI pair programmer** that helps you write code faster and with higher quality. It provides suggestions for whole functions, boilerplate code, tests, and even complex algorithms as you type.

### **🎯 Core Capabilities**
- **🧠 Contextual Code Generation**: Understands your project structure and generates relevant code
- **⚡ Intelligent Completions**: Completes functions, classes, and entire code blocks
- **🧪 Test Automation**: Generates unit tests, integration tests, and test data
- **📚 Documentation Assistant**: Creates README files, API docs, and code comments
- **🔄 Code Translation**: Converts between programming languages and frameworks
- **🛠️ Refactoring Support**: Suggests improvements and modernization patterns

### **💡 Copilot Best Practices from This Workshop**
1. **Write Clear Comments**: Use descriptive comments to guide Copilot's suggestions
   ```java
   // Create a REST endpoint that accepts a task and returns the created task with ID
   @PostMapping("/tasks")
   // Copilot will generate the method signature and implementation
   ```

2. **Leverage Context**: Open related files to give Copilot more context about your project
   ```typescript
   // With TaskDto.ts open, Copilot understands your data structures
   const handleCreateTask = (task: CreateTaskCommand) => {
     // Copilot suggests API calls matching your DTOs
   ```

3. **Use Descriptive Function Names**: Start typing meaningful names for better suggestions
   ```csharp
   // Type this and let Copilot complete the implementation
   public async Task<TaskDto> GetTasksByStatusAsync
   ```

### **🎪 Advanced Copilot Features in This Repo**
- **Custom Prompts**: Pre-built prompts in `.github/prompts/` for common tasks
- **Framework Instructions**: Specialized guidance in `.github/instructions/`
- **Chat Mode Integration**: Use `/` commands for specialized assistance

### **📖 Official Resources**
- **[GitHub Copilot Documentation](https://docs.github.com/en/copilot)** - Complete feature guide
- **[VS Code Copilot Setup](https://code.visualstudio.com/docs/copilot/overview)** - IDE configuration
- **[Copilot Chat Features](https://code.visualstudio.com/docs/copilot/copilot-chat)** - Advanced chat capabilities

### **🌟 Community Resources**
**🔗 [Awesome GitHub Copilot](https://github.com/github/awesome-copilot)** - The **largest official community repository** with 7.3k+ stars, featuring:
- **850+ Custom Prompts**: Task-specific prompts for code generation
- **Coding Standards**: Best practices for 50+ programming languages  
- **Specialized Chat Modes**: AI personas for architects, DBAs, security experts
- **MCP Server Integration**: Direct installation into VS Code
- **Community Contributions**: 96+ contributors sharing real-world patterns

**Why use Awesome Copilot?**
- ✅ **Pre-built Solutions**: Save time with tested prompts and instructions
- ✅ **Best Practices**: Learn from community-curated coding standards
- ✅ **Continuous Updates**: Stay current with latest patterns and techniques
- ✅ **Multi-Framework Support**: Coverage across all major tech stacks

---

## 🔍 Code Reference Examples

### **🎪 Copilot-Generated Code Samples**
| Component | Location | Copilot Features Demonstrated |
|-----------|----------|-------------------------------|
| **REST API** | `spring-boot/src/.../TaskController.java` | Auto-generated endpoints from OpenAPI |
| **Service Layer** | `spring-boot/src/.../TaskService.java` | Business logic with interface contracts |
| **Entity Models** | `spring-boot/src/.../TaskEntity.java` | JPA annotations and relationships |
| **Repository Pattern** | `spring-boot/src/.../TaskRepository.java` | Query method generation |
| **Unit Tests** | `spring-boot/src/test/.../TaskServiceTest.java` | Test case generation with mocking |
| **Integration Tests** | `spring-boot/src/test/.../TaskControllerTest.java` | API testing with Testcontainers |
| **React Components** | `react/src/components/TaskList.tsx` | Component logic and hooks |
| **TypeScript Interfaces** | `react/src/types/Task.ts` | Type definitions from API contract |
| **API Documentation** | `docs/ToDoListOpenApi.json` | OpenAPI specification as source of truth |

### **⚡ Copilot Prompting Examples**
```bash
# In GitHub Copilot Chat
/explain spring-boot/src/main/java/pl/vm/aiworkshop/api/TaskController.java
/generate unit tests for TaskService.createTask method
/optimize the database queries in TaskRepository
/create React component for displaying task statistics
```

### **🎯 Workshop-Specific Features**
- **Custom Prompts**: Use `.github/prompts/setup-project.backend.prompt.md` for automated project setup
- **Framework Instructions**: Follow `.github/instructions/spring-boot.instructions.md` for coding standards
- **Contract-First Development**: Generate implementations from `docs/ToDoListOpenApi.json`

---

## 🎓 Learning Outcomes

After completing this workshop, you will be able to:

### **🤖 GitHub Copilot Mastery**
- ✅ Configure and optimize Copilot for maximum productivity
- ✅ Write effective prompts that generate high-quality code
- ✅ Use Copilot Chat for complex problem-solving and refactoring
- ✅ Create custom prompts and instructions for team consistency
- ✅ Leverage context to get better AI suggestions

### **🏗️ Modern Development Practices**
- ✅ Implement contract-first API development with OpenAPI
- ✅ Build clean, testable architectures with interface-based contracts
- ✅ Apply domain-driven design principles
- ✅ Create comprehensive test suites with AI assistance
- ✅ Generate and maintain technical documentation

### **⚡ Multi-Stack Proficiency**
- ✅ Compare and contrast Spring Boot vs .NET approaches
- ✅ Build responsive React applications with TypeScript
- ✅ Use modern tooling (Vite, Testcontainers, Docker)
- ✅ Implement the same business requirements across different stacks

---

## 🤝 Contributing & Community

We welcome contributions to improve this workshop! 

### **How to Contribute**
1. **Fork the repository** and create a feature branch
2. **Add your improvements** (new prompts, better examples, etc.)
3. **Test thoroughly** with GitHub Copilot
4. **Submit a pull request** with clear description

### **Ideas for Contributions**
- 📝 Additional framework implementations (Python FastAPI, Go, Rust)
- 🧪 More advanced testing patterns and examples  
- 📚 Language-specific Copilot tips and tricks
- 🎪 Custom prompts for specialized use cases

### **Join the Community**
- ⭐ **Star this repo** to show support
- 🐛 **Report issues** to help us improve
- 💡 **Share your Copilot discoveries** in discussions
- 🔗 **Connect with us** on social media

---

## 📄 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

**🎯 Educational Purpose**: This repository is created by **VM.PL Academy** for educational purposes and community learning.

---

## 🙏 Acknowledgments

- **GitHub Copilot Team** for creating an amazing AI coding assistant
- **[Awesome GitHub Copilot](https://github.com/github/awesome-copilot)** community for inspiration and best practices
- **Spring Boot** and **.NET** communities for excellent documentation and examples
- **React** and **TypeScript** ecosystems for modern frontend development tools

---

**🚀 Ready to supercharge your coding with AI? Choose your path and let's get started!** 

[🟢 **Start with Java/Spring Boot**](spring-boot/README.md) | [🔵 **Start with .NET**](dotnet/README.md) | [🟡 **Start with React**](react/README.md)
