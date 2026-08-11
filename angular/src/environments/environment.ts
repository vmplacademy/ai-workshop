export const environment = {
  production: false,
  apiUrl: 'http://localhost:5025/api', // .NET backend (HTTP not HTTPS)
  endpoints: {
    tasks: '/tasks'
  }
};

// Alternative backend configurations
export const environments = {
  springBoot: {
    apiUrl: 'http://localhost:8080/api',
    endpoints: { tasks: '/tasks' }
  },
  dotnet: {
    apiUrl: 'http://localhost:5025/api', // .NET backend (HTTP not HTTPS)
    endpoints: { tasks: '/tasks' }
  },
  nodejs: {
    apiUrl: 'http://localhost:3000/api',
    endpoints: { tasks: '/tasks' }
  }
};