# AI Workshop — VM.pl training project

A multi-stack ToDo application used as a playground for developer AI tooling workshops.
The same API contract implemented in several technologies, with ready product documentation
and a frontend — so that the exercises run on something that resembles a real project
rather than a tutorial example.

**Current workshop: Claude Code Advanced — from a tool to a platform.**

---

## Structure

```plaintext
.
├── spring-boot/     Java 21 + Spring Boot 3.5.16, PostgreSQL, Testcontainers
├── dotnet/          .NET 8, EF Core, Clean Architecture
├── angular/         Angular 20 + Tailwind 4 — application frontend
├── docs/
│   ├── ToDoListOpenApi.json      API contract — source of truth for both backends
│   └── frontend/                 frontend PRD + view mockups
└── .github/         GitHub Copilot instructions and prompts (see below)
```

### `.github/` — kept on purpose

The directory holds `instructions/`, `prompts/`, `chatmodes/` and `memory/` from the previous
workshop cycle, that one dedicated to GitHub Copilot. This is not dead code — it is a reference point.
During the workshop we put those files side by side with their `.claude/` counterparts to see
how configuring one tool differs from configuring the other.

---

## Quick start

### Frontend — Angular

```bash
cd angular
npm install
npm run start:dotnet     # .NET backend (:5025)
npm run start:spring     # Spring Boot backend (:8080)
```

The application starts on **http://localhost:4200**. `npm start` is equivalent to `start:dotnet`.

**Picking a backend means picking a script, not editing code.** The frontend calls the relative
`/api` address and the dev-server proxy routes traffic to the right port
(`proxy.conf.spring.json` / `proxy.conf.dotnet.json`). Both backends implement the same contract
from `docs/ToDoListOpenApi.json`, so the application does not need to know which one answers.

It also works **without a running backend** — when there is no response it falls back to built-in
demo data (the console reports a connection error, which is expected). That makes it possible
to work on the frontend without Docker and a database.

### Backend — Spring Boot

```bash
cd spring-boot
./mvnw spring-boot:test-run
```

Swagger UI: **http://localhost:8080/swagger-ui.html**. Requires Docker (Testcontainers starts PostgreSQL).

Build and tests:

```bash
./mvnw clean install
```

### Backend — .NET

```bash
cd dotnet/src
dotnet run
```

Swagger: **http://localhost:5025/swagger**.

Uses an **in-memory** database — no Docker and no PostgreSQL needed. Together with Angular it gives
a fully working stack at zero configuration, at the cost of losing data when the process restarts.

---

## API contract

Both backends implement the same specification from `docs/ToDoListOpenApi.json`:

| Method | Path | Description |
|---|---|---|
| `POST` | `/api/tasks` | create a task |
| `GET` | `/api/tasks` | list tasks |
| `GET` | `/api/tasks/{id}` | task by ID |
| `PUT` | `/api/tasks/{id}` | update |
| `DELETE` | `/api/tasks/{id}` | delete |

Task statuses: `CREATED`, `IN_PROGRESS`, `DONE`.

---

## Exercise material

A few places in the repository exist purely to give the workshop something to work on:

- **`dotnet/src/Legacy/`** — a notification service written to beg for refactoring: one method
  branching on a channel string. The Spring Boot counterpart in **`spring-boot/.../domain/legacy/`**
  already shows the target shape (a `NotificationType → NotificationSender` strategy map),
  so it doubles as a reference for where the .NET version should land.
- **`docs/frontend/`** — a PRD with mockups of seven views. Material for "implement it according
  to the specification" tasks.
- **`docs/LocalApiClient.http`** — ready-made requests for hitting the API by hand.

---

## Requirements

| | |
|---|---|
| Node.js | 20.19+ (frontend — required by Angular 20) |
| JDK | 21 (Spring Boot) |
| .NET SDK | 8.0 |
| Docker | **Spring Boot only** (Testcontainers) — the frontend and the .NET backend run without it |

---

## License

MIT — see [LICENSE](LICENSE). The repository was created in **VM.PL Academy** for training purposes.
