# .NET 9 TodoApp Workshop

![.NET 9](https://img.shields.io/badge/.NET-9.0-blue.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Ready-green.svg)
![Testcontainers](https://img.shields.io/badge/Testcontainers-Configured-orange.svg)
![GitHub Copilot](https://img.shields.io/badge/GitHub_Copilot-Ready-purple.svg)

A modern .NET 9 REST API workshop designed for learning Clean Architecture principles with GitHub Copilot assistance.

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

- ✅ .NET 9 SDK installed
- ✅ Visual Studio Code with C# Dev Kit extension
- ✅ GitHub Copilot extension enabled
- ✅ PostgreSQL (for future phases)
- ✅ Docker (for Testcontainers)
- ✅ Git for version control

## 🎯 **What We Built in Phase 1**

### **Key Features Implemented**
- ✅ **.NET 9 Web API** with controller-based architecture
- ✅ **OpenAPI Documentation** automatically generated
- ✅ **Hello World Controller** for testing: `GET /api/hello-world`
- ✅ **Clean Project Structure** with proper separation of concerns
- ✅ **Proper .gitignore** for .NET development
- ✅ **All NuGet Packages** for PostgreSQL, EF Core, and Testcontainers
- ✅ **Simplified Structure** - everything directly in `src/`

### **Test the API**

**Test the Hello World endpoint:**
```bash
curl http://localhost:5025/api/hello-world
```

**Expected Response:**
```json
{
  "message": "Hello, World from .NET 9 API!",
  "timestamp": "2024-09-14T14:30:00.0000000Z",
  "version": "1.0.0",
  "controller": "HelloWorldController",
  "environment": "Development"
}
```

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

You now have a fully functional .NET 9 Web API with:
- ✅ Controller-based architecture
- ✅ OpenAPI/Swagger documentation  
- ✅ All necessary NuGet packages
- ✅ Clean project structure
- ✅ Ready for Phase 2 development

**Next step:** Ready to implement the TodoList functionality!

## 🎯 **What You'll Learn**

### **Phase 1: Foundation** ✅
- [x] .NET 9 Controller-based API setup
- [x] Clean project structure
- [x] NuGet package management
- [x] GitHub Copilot integration
- [x] HelloWorld controller with health check

### **Phase 2: Domain** (Coming Next)
- [ ] Task entity and DTOs
- [ ] Controller-based endpoints
- [ ] Request validation

### **Phase 3: Database**
- [ ] PostgreSQL with EF Core
- [ ] Migrations and seeding
- [ ] Repository pattern

### **Phase 4: Business Logic**
- [ ] Service layer implementation
- [ ] Business rules and validation
- [ ] Error handling patterns

### **Phase 5: Testing**
- [ ] Unit tests with xUnit
- [ ] Integration tests
- [ ] Testcontainers for database testing

## 🛠️ **Tech Stack**

### **Framework**
- **.NET 9** - Latest LTS version
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
- ✅ [.NET 9 SDK](https://dotnet.microsoft.com/download/dotnet/9.0) - Latest .NET version
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

- 🔗 [.NET 9 Documentation](https://docs.microsoft.com/en-us/dotnet/core/whats-new/dotnet-9)
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