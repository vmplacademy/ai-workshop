// Adres relatywny — o tym, który backend obsłuży żądanie, decyduje proxy dev servera.
// Spring Boot (:8080)  →  npm run start:spring
// .NET       (:5025)   →  npm run start:dotnet
// Konfiguracja: proxy.conf.spring.json / proxy.conf.dotnet.json
export const environment = {
  production: false,
  apiUrl: '/api',
  endpoints: {
    tasks: '/tasks',
  },
};
