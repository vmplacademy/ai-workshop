# .NET 8 TodoApp Workshop

![.NET 8](https://img.shields.io/badge/.NET-8.0-blue.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Ready-green.svg)
![Testcontainers](https://img.shields.io/badge/Testcontainers-Configured-orange.svg)
![GitHub Copilot](https://img.shields.io/badge/GitHub_Copilot-Ready-purple.svg)

A modern .NET 8 REST API workshop designed for learning Clean Architecture principles with GitHub Copilot assistance.

## 🚀 **Quick Start**

```bash
# Navigate to source directory
cd dotnet/src

# Restore packages and run
dotnet restore
dotnet run
```

Visit: `http://localhost:5025/api/hello-world` to test the API!

**Available endpoints:**
- `http://localhost:5025/api/hello-world` - Hello World test endpoint
- `http://localhost:5025/swagger` - API documentation

## � **Prerequisites**

- ✅ .NET 8 SDK installed
- ✅ Visual Studio Code with C# Dev Kit extension
- ✅ GitHub Copilot extension enabled
- ✅ PostgreSQL (for future phases)
- ✅ Docker (for Testcontainers)
- ✅ Git for version control



## 🎪 Workshop Agenda: GitHub Copilot and .NET Integration

### **Phase 1: Getting Started**
- **Overview**: Introduction to the ToDo List application.
- **Project Goals**: Discuss the objectives and functionality.

### **Phase 2: TaskController Implementation**

#### Create Controller with GitHub Copilot Chat

**TaskController**: Implement `TaskController` with injected `ITaskService`.

**Prompt examples:**
```text
We are going to create simple ToDo list application. Do not! provide any suggestions at this point. It just for context.
We are going to implement it step by step starting with TaskController. Just wait for my next prompts.
```
```text
Create TaskController with injected ITaskService as interface in ASP.NET Core as Controller for CRUD operations for ToDo application.
Provide only empty bodies without implementations. Handle proper operations via suitable Http methods
```

#### CRUD Operations Implementation

**Create Method**: Step-by-step guide to implementing the `Create` method.
```text
Create "Create" method using ITaskService. It takes object named CreateTaskCommand as parameter. Returned type should be TaskQuery.
```

**Get Method**: Step-by-step guide to implementing the `Get` method.
```text
Create "Get" method using ITaskService. It takes TaskId as long as parameter. Returned type should be TaskQuery.
```

**Update Method**: Step-by-step guide to implementing the `Update` method.
```text
Create "Update" method using ITaskService. It takes object named UpdateTaskCommand as parameter. Returned type should be TaskQuery.
```

**Delete Method**: Step-by-step guide to implementing the `Delete` method.
```text
Create "Delete" method using ITaskService. It takes TaskId as long as parameter. This method should be 'void'.
```

### **Phase 3: Service Layer Implementation**

**TaskServiceAdapter**: Implement `TaskServiceAdapter` with dependency injection.

**Prompt example:**
```text
Based on TaskController provide proper implementation for interface ITaskService as TaskServiceAdapter. It should be registered for DI.
```

### **Phase 4: Entity and Repository Setup**

#### Create TaskEntity
**TaskEntity**: Define with relevant C# attributes and EF Core configuration.
```text
Create TaskEntity with all relevant C# attributes for storing EF Core entity. Use table name as "t_task".
Provide all relevant columns as string: "Name", TaskStatus (enum): "Status". Add proper data types for each column.
```

#### Create TaskRepository
**TaskRepository**: Implement `TaskRepository` extending `IRepository<TaskEntity>` or using EF Core DbContext.
```text
Create TaskRepository, which extends IRepository based on TaskEntity or uses EF Core DbContext.
```

#### Use TaskRepository in TaskService
**TaskService**: Use `TaskRepository` and implement CRUD operations.
```text
In TaskService use injected TaskRepository to implement CRUD operations.
```

### **Phase 5: Exercise 1 - New controller's method**

**Exercise**: Implement the `GetAllTasks` method in both `TaskController` and `ITaskService`.
```text
Create new method in both TaskController and ITaskService for "GetAllTasks".
It will be "GET" operation and should return List<TaskQuery>.
```

***Optional**: If needed, checkout proper project version at this point:*
```bash
git checkout https://github.com/vmplacademy/ai-workshop.git/dotnet/task-1
```

### **Phase 6: Unit Testing**

#### Generate Unit Tests using xUnit and Moq
**TaskServiceTests**: Create unit tests with mock operations and input validation.
```text
Create TaskServiceTests, which should be unit test class based on TaskService. Provide test methods for each operation.
Handle proper input validation. Mock operations with TaskRepository.
```
**Review and Refine**: Evaluate generated test cases for accuracy.

### **Phase 7: Integration Testing**

#### Testcontainers and Integration Tests
**TaskControllerApiTests**: Implement using `WebApplicationFactory` and `HttpClient`.
```text
Create TaskApiTests, which should be integration test class based on TaskController. Provide test methods for each operation.
Use WebApplicationFactory and HttpClient. Use Testcontainers for PostgreSQL.
```
**Extend TestContainer Configuration**: Ensure comprehensive integration testing.

### **Phase 8: Exercise 2 - Additional test cases**

**Exercise**: Implement `TaskRepositoryTests` for handling database interaction.
```text
Create new test class for TaskRepository. It should contain unit test cases for handling database interaction.
It should use EF Core InMemory or Testcontainers for tests.
```

***Optional**: If needed, checkout proper project version at this point:*
```bash
git checkout https://github.com/vmplacademy/ai-workshop.git/dotnet/task-2
```

### **Phase 9: Code Refactoring**

#### Refactor Existing Code with GitHub Copilot
**Improve Code Quality**: Refactor `LegacyNotificationService` and entire legacy code for better readability and performance.

**Prompt examples:**
```text
Explain in detail the role of the given class based on TaskOutOfDateService class.
```
```text
Provide potential code improvements for <method_name>. Use C# best practices as of .NET 8. Make sure that the suggestions given improve code quality and readability.
```
**Use Best Practices**: Ensure refactored code aligns with .NET 8 standards.

### **Phase 10: Documentation Generation**

#### Generate Technical Documentation
**XML Docs**: Automatically generate for `TaskController` and `ITaskService`.

**README.md**: Create comprehensive project documentation using GitHub Copilot.

**Prompt examples:**
```text
Create simple project documentation as README.md based on already existing XML documentation in TaskController.
Add information about used technologies such as ASP.NET Core 9.x, PostgreSQL, Testcontainers.
Provide different sections with proper headers as "Main project goal", "Used technologies", "How to start it?".
Use markdown format.
```
```text
Provide suggestions what could be also included in such a README.md?
```

---

## 🏗️ **Project Structure**

```
dotnet/
├── 📄 README.md                # This complete guide
├── 🚫 .gitignore              # .NET-specific gitignore
└── 📁 src/                    # All source code
    ├── 📦 TodoApp.csproj       # Project with all NuGet packages
    ├── ⚙️  Program.cs          # Application entry point
    ├── ⚙️  appsettings.json    # Configuration
    ├── 📁 Controllers/         # API Controllers
    │   └── HelloWorldController.cs # Test controller (✅ Phase 1)
    ├── 📁 Dtos/               # Data Transfer Objects (Phase 2)
    └── 📁 Domains/            # Domain logic (Phase 2)
        ├── 📁 Models/         # Entities
        ├── 📁 Services/       # Business logic
        └── 📁 Data/           # Data access
```

## 📦 **NuGet Packages Included**

Our project comes with production-ready packages:

### **Core Packages**
- `Microsoft.AspNetCore.OpenApi` - OpenAPI/Swagger support
- `Swashbuckle.AspNetCore` - Swagger UI

### **Entity Framework & Database**
- `Microsoft.EntityFrameworkCore` - Core EF functionality
- `Microsoft.EntityFrameworkCore.Design` - Design-time tools
- `Microsoft.EntityFrameworkCore.Tools` - Package Manager Console tools
- `Npgsql.EntityFrameworkCore.PostgreSQL` - PostgreSQL provider

### **Health Checks**
- `Microsoft.Extensions.Diagnostics.HealthChecks.EntityFrameworkCore`
- `AspNetCore.HealthChecks.Npgsql`

### **Validation & Mapping**
- `FluentValidation.AspNetCore` - Input validation
- `AutoMapper` + `AutoMapper.Extensions.Microsoft.DependencyInjection`

### **Logging**
- `Serilog.AspNetCore` - Structured logging
- `Serilog.Sinks.Console` + `Serilog.Sinks.File`

### **Testing**
- `Microsoft.AspNetCore.Mvc.Testing` - Integration testing
- `Testcontainers.PostgreSql` - Database testing with containers
- `Microsoft.EntityFrameworkCore.InMemory` - In-memory testing

## 🔧 **Development Commands**

```bash
# Build the project
dotnet build

# Run the application
dotnet run

# Run with watch (auto-reload)
dotnet watch run

# Clean build artifacts
dotnet clean

# Restore NuGet packages
dotnet restore
```

## 🧪 **Architecture Patterns**

This project follows **Clean Architecture** principles:

- **Controllers** - Handle HTTP requests and responses
- **DTOs** - Data transfer objects for API boundaries  
- **Domain Models** - Core business entities
- **Services** - Business logic implementation
- **Data Layer** - Repository pattern with Entity Framework

## 🚀 **Phase 2 Preview**

Coming next in the workshop:
- 📝 **Task Entity** and **TaskController**
- 🗄️ **PostgreSQL** integration with **Entity Framework Core**
- ✅ **CRUD operations** for Todo items
- 🧪 **Unit and Integration Tests** with **Testcontainers**
- 🏗️ **Clean Architecture** implementation
- 📊 **Health Checks** and **Monitoring**

## 🤖 **GitHub Copilot Tips**

This project is optimized for GitHub Copilot assistance:
- Use descriptive comments to guide Copilot suggestions
- Follow consistent naming conventions
- Leverage the rich context from NuGet packages
- Ask Copilot to implement patterns like Repository or Service layers

## 🎉 **Phase 1 Complete!**

You now have a fully functional .NET 8 Web API with:
- ✅ Controller-based architecture
- ✅ OpenAPI/Swagger documentation  
- ✅ All necessary NuGet packages
- ✅ Clean project structure
- ✅ Ready for Phase 2 development

**Next step:** Ready to implement the TodoList functionality!


## 🎯 **Workshop Phases Checklist**

### **Phase 1: Foundation**
- [x] Set up ASP.NET Core REST API with HelloWorld controller
- [x] Clean project structure for future expansion
- [x] Integrate GitHub Copilot in your IDE

### **Phase 2: Domain**
- [ ] Define Task entity and DTOs (C# 13 record types)
- [ ] Create controller endpoints for CRUD operations (empty bodies at first)
- [ ] Add request validation (FluentValidation/DataAnnotations)

### **Phase 3: Database**
- [ ] Integrate PostgreSQL using Entity Framework Core
- [ ] Set up EF Core migrations for schema management
- [ ] Implement repository interfaces for data access

### **Phase 4: Business Logic**
- [ ] Implement service layer for business rules
- [ ] Add validation and error handling
- [ ] Use Copilot to suggest/refactor business logic

### **Phase 5: Testing**
- [ ] Write unit tests with xUnit and Moq
- [ ] Add integration tests using Testcontainers for PostgreSQL
- [ ] Ensure all layers are covered by tests

## 🛠️ **Tech Stack**

### **Framework**
- **.NET 8** - Latest LTS version
- **ASP.NET Core** - Web API framework
- **C# 13** - Modern language features

### **Database**
- **PostgreSQL** - Primary database
- **Entity Framework Core 9** - ORM
- **Testcontainers** - Testing infrastructure

### **Development Tools**
- **OpenAPI/Swagger** - API documentation
- **Serilog** - Structured logging
- **FluentValidation** - Request validation
- **AutoMapper** - Object mapping
- **GitHub Copilot** - AI-assisted development

## 🎓 **Learning Approach**

### **AI-Assisted Development**
This workshop is designed to work seamlessly with **GitHub Copilot**:
- 🧠 **Clear patterns** that Copilot understands
- 📝 **Well-documented code** for better suggestions
- 🏗️ **Consistent structure** for predictable completions
- 🧪 **Testing patterns** that Copilot can extend

### **Progressive Complexity**
- Start with simple hello-world endpoint
- Gradually add layers of complexity
- Each phase builds on previous knowledge
- Real-world patterns and practices

## 🔧 **Prerequisites**

### **Required Software**
- ✅ [.NET 8 SDK](https://dotnet.microsoft.com/download/dotnet/9.0) - Latest .NET version
- ✅ [Visual Studio Code](https://code.visualstudio.com/) - Primary IDE for this workshop
- ✅ [PostgreSQL](https://www.postgresql.org/download/) (for later phases)
- ✅ [Docker](https://www.docker.com/get-started) (for Testcontainers)

### **VS Code Extensions for .NET Development**

#### **Essential Extensions (Required)**

**🎯 C# Dev Kit** - `ms-dotnettools.csdevkit`
- Complete C# development experience in VS Code
- Includes IntelliSense, debugging, and project management
- Built on the same foundation as Visual Studio
- **Installation**: [C# Dev Kit](https://marketplace.visualstudio.com/items?itemName=ms-dotnettools.csdevkit)

The **C# Dev Kit** automatically includes:
- **C# Extension** (`ms-dotnettools.csharp`) - Base language services
- **IntelliCode for C# Dev Kit** (optional) - AI-powered development experience

#### **GitHub Copilot Extensions (Workshop Focus)**

**🤖 GitHub Copilot** - `GitHub.copilot`
- AI-powered code completions and suggestions
- **Essential for this workshop**
- **Installation**: [GitHub Copilot](https://marketplace.visualstudio.com/items?itemName=GitHub.copilot)

**💬 GitHub Copilot Chat** - `GitHub.copilot-chat`
- Interactive AI assistance and code explanations
- **Installation**: [GitHub Copilot Chat](https://marketplace.visualstudio.com/items?itemName=GitHub.copilot-chat)

#### **Alternative: .NET Extension Pack (Complete Package)**

If you prefer to install everything at once:

**📦 .NET Extension Pack** - `ms-dotnettools.vscode-dotnet-pack`
- Includes C# Dev Kit + additional tools
- **Installation**: [.NET Extension Pack](https://marketplace.visualstudio.com/items?itemName=ms-dotnettools.vscode-dotnet-pack)

#### **Quick Setup Commands**

```bash
# Install essential extensions via command line
code --install-extension ms-dotnettools.csdevkit
code --install-extension GitHub.copilot
code --install-extension GitHub.copilot-chat

# OR install the complete pack
code --install-extension ms-dotnettools.vscode-dotnet-pack
```

#### **Verify Installation**

After installing extensions, verify your setup:

```bash
# Check .NET SDK version
dotnet --version

# Verify VS Code can find .NET
dotnet --info
```

**Expected output**: .NET version should show 9.0.x or later

## 🐛 **Troubleshooting**

### **Common Issues**

**API won't start?**
```bash
cd dotnet/src
dotnet clean
dotnet restore
dotnet build
dotnet run
```

**Port 5025 in use?**
```bash
lsof -ti:5025 | xargs kill -9
```

**HTTPS redirect warning?**
This is normal in development mode - the warning is disabled for local development.

### **VS Code .NET Setup Issues**

**C# IntelliSense not working?**
1. Ensure C# Dev Kit extension is installed and enabled
2. Reload VS Code window: `Ctrl+Shift+P` → "Developer: Reload Window"
3. Check VS Code status bar for .NET SDK version

**Missing "Required assets to build and debug"?**
- When VS Code asks to add required assets, click **"Yes"**
- This creates `.vscode/launch.json` and `.vscode/tasks.json`

**GitHub Copilot not working?**
1. Verify you have an active GitHub Copilot subscription
2. Sign in to GitHub in VS Code: `Ctrl+Shift+P` → "GitHub: Sign In"
3. Check Copilot status in VS Code status bar

**Project not loading in VS Code?**
- Open the `dotnet/src` folder (containing `TodoApp.csproj`)
- VS Code should show "Restore" notification - click it
- Wait for OmniSharp to finish loading (status bar indicator)

## 📖 **Additional Resources**

- 🔗 [.NET 8 Documentation](https://docs.microsoft.com/en-us/dotnet/core/whats-new/dotnet-9)
- 🔗 [ASP.NET Core Minimal APIs](https://docs.microsoft.com/en-us/aspnet/core/fundamentals/minimal-apis)
- 🔗 [Entity Framework Core](https://docs.microsoft.com/en-us/ef/core/)
- 🔗 [GitHub Copilot Best Practices](https://github.com/features/copilot)
- 🔗 [Clean Architecture in .NET](https://docs.microsoft.com/en-us/dotnet/architecture/modern-web-apps-azure/)

## 🤝 **Contributing**

This is a workshop project designed for learning. Feel free to:
- 🐛 Report issues
- 💡 Suggest improvements
- 🚀 Add new features
- 📚 Enhance documentation

## 📄 **License**

MIT License - see [LICENSE](../LICENSE) file for details.

---

**Happy Coding with GitHub Copilot!** 🚀🤖