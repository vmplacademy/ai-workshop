# Commit Instructions
This file provides guidelines for writing clear and effective commit messages. Following these instructions will help maintain a well-organized project history, making it easier for team members to understand changes and collaborate effectively.

## Commit Message Structure
1. Use Conventional Commits (v1.0.0) format:
   `<type>(<scope>)!: <subject>`
   - `scope` is optional but recommended (e.g., `react`, `spring-boot`, `dotnet`, `api`, `docs`).
   - `!` indicates a breaking change.
2. Subject line:
   - Imperative mood: "Add feature" not "Added feature".
   - ≤ 50 characters, sentence case, no trailing period.
3. Allowed types:
   - feat: A new feature
   - fix: A bug fix
   - docs: Documentation changes
   - style: Code style/formatting (no code behavior change)
   - refactor: Code change that neither fixes a bug nor adds a feature
   - test: Adding or updating tests
   - chore: Repo maintenance or tooling
   - ci: CI configuration and scripts
   - build: Build system or external dependencies
   - perf: Performance improvements
   - revert: Revert a previous commit
4. Breaking changes:
   - Add `!` after type/scope and/or include a `BREAKING CHANGE:` footer describing the impact and migration notes.
5. Reference issues and PRs in the footer (e.g., `Closes #123`, `Refs #456`).
   - style: Code style/formatting (no code behavior change)
   - refactor: Code change that neither fixes a bug nor adds a feature
   - test: Adding or updating tests
   - chore: Repo maintenance or tooling
   - ci: CI configuration and scripts
   - build: Build system or external dependencies
   - perf: Performance improvements
   - revert: Revert a previous commit
4. Breaking changes:
   - Add `!` after type/scope and/or include a `BREAKING CHANGE:` footer describing the impact and migration notes.
5. Reference issues and PRs in the footer (e.g., `Closes #123`, `Refs #456`).
   - style: Code style/formatting (no code behavior change)
   - refactor: Code change that neither fixes a bug nor adds a feature
   - test: Adding or updating tests
   - chore: Repo maintenance or tooling
   - ci: CI configuration and scripts
   - build: Build system or external dependencies
   - perf: Performance improvements
   - revert: Revert a previous commit
4. Breaking changes:
   - Add `!` after type/scope and/or include a `BREAKING CHANGE:` footer describing the impact and migration notes.
5. Reference issues and PRs in the footer (e.g., `Closes #123`, `Refs #456`).
   - refactor: Code change that neither fixes a bug nor adds a feature
   - test: Adding or updating tests
   - chore: Repo maintenance or tooling
   - ci: CI configuration and scripts
   - build: Build system or external dependencies
   - perf: Performance improvements
   - revert: Revert a previous commit
4. Breaking changes:
   - Add `!` after type/scope and/or include a `BREAKING CHANGE:` footer describing the impact and migration notes.
5. Reference issues and PRs in the footer (e.g., `Closes #123`, `Refs #456`).
5. For additional details, use a well-structured body section.
    - Use bullet points (-) for clarity.
    - Explain the what and why of the changes, not the how.
    - Wrap lines at 72 characters.

