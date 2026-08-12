# AI Workshop — projekt szkoleniowy VM.pl

Wielostackowa aplikacja ToDo używana jako poligon na warsztatach z narzędzi AI dla developerów.
Ten sam kontrakt API zaimplementowany w kilku technologiach, z gotową dokumentacją produktową
i frontendem — po to, żeby ćwiczyć na czymś, co przypomina realny projekt, a nie na przykładzie z tutoriala.

**Aktualne szkolenie: Claude Code Advanced — od narzędzia do platformy.**

---

## Struktura

```plaintext
.
├── spring-boot/     Java 21 + Spring Boot 3.5.5, PostgreSQL, Testcontainers
├── dotnet/          .NET 8, EF Core, Clean Architecture
├── angular/         Angular 19 + Tailwind 4 — frontend aplikacji
├── docs/
│   ├── ToDoListOpenApi.json      kontrakt API — źródło prawdy dla backendów
│   └── frontend/                 PRD frontendu + mockupy widoków
└── .github/         instrukcje i prompty GitHub Copilot (patrz niżej)
```

### `.github/` — to zostaje celowo

Katalog zawiera `instructions/`, `prompts/`, `chatmodes/` i `memory/` z poprzedniego cyklu warsztatów,
tym razem poświęconego GitHub Copilotowi. Nie jest to martwy kod — to punkt odniesienia.
Na szkoleniu zestawiamy te pliki z ich odpowiednikami w `.claude/`, żeby zobaczyć,
czym różni się konfiguracja jednego narzędzia od drugiego.

---

## Szybki start

### Frontend — Angular

```bash
cd angular
npm install
npm run start:dotnet     # backend .NET (:5025)
npm run start:spring     # backend Spring Boot (:8080)
```

Aplikacja startuje na **http://localhost:4200**. `npm start` jest równoważne `start:dotnet`.

**Wybór backendu to wybór skryptu, nie zmiana w kodzie.** Frontend odpytuje relatywny adres `/api`,
a proxy dev servera kieruje ruch pod właściwy port (`proxy.conf.spring.json` / `proxy.conf.dotnet.json`).
Oba backendy implementują ten sam kontrakt z `docs/ToDoListOpenApi.json`, więc aplikacja nie musi
wiedzieć, który z nich odpowiada.

Działa też **bez uruchomionego backendu** — przy braku odpowiedzi przechodzi na wbudowane dane
demonstracyjne (konsola zgłasza wtedy błąd połączenia, to normalne). Dzięki temu można pracować
nad frontendem bez Dockera i bazy danych.

### Backend — Spring Boot

```bash
cd spring-boot
./mvnw spring-boot:test-run
```

Swagger UI: **http://localhost:8080/swagger-ui.html**. Wymaga Dockera (Testcontainers uruchamia PostgreSQL).

Budowa i testy:

```bash
./mvnw clean install
```

### Backend — .NET

```bash
cd dotnet/src
dotnet run
```

Swagger: **http://localhost:5025/swagger**.

Używa bazy **in-memory** — nie wymaga Dockera ani PostgreSQL. Razem z Angularem daje pełny działający
stack przy zerowej konfiguracji, kosztem tego, że dane znikają po restarcie procesu.

---

## Kontrakt API

Oba backendy implementują tę samą specyfikację z `docs/ToDoListOpenApi.json`:

| Metoda | Ścieżka | Opis |
|---|---|---|
| `POST` | `/api/tasks` | utworzenie zadania |
| `GET` | `/api/tasks` | lista zadań |
| `GET` | `/api/tasks/{id}` | zadanie po ID |
| `PUT` | `/api/tasks/{id}` | aktualizacja |
| `DELETE` | `/api/tasks/{id}` | usunięcie |

Statusy zadania: `TODO`, `IN_PROGRESS`, `DONE`.

---

## Materiał do ćwiczeń

Kilka miejsc w repo istnieje po to, żeby było na czym pracować:

- **`spring-boot/.../domain/legacy/`** i **`dotnet/src/Legacy/`** — system powiadomień napisany tak,
  żeby prosił się o refaktoryzację. Siedem klas, wzorzec do wyciągnięcia.
- **`docs/frontend/`** — PRD z mockupami siedmiu widoków. Materiał na zadania „zaimplementuj zgodnie ze specyfikacją".
- **`docs/LocalApiClient.http`** — gotowe zapytania do ręcznego odpytania API.

---

## Wymagania

| | |
|---|---|
| Node.js | 20.11+ (frontend) |
| JDK | 21 (Spring Boot) |
| .NET SDK | 8.0 |
| Docker | **tylko dla Spring Boota** (Testcontainers) — frontend i backend .NET działają bez niego |

---

## Licencja

MIT — patrz [LICENSE](LICENSE). Repozytorium powstało w **VM.PL Academy** do celów szkoleniowych.
