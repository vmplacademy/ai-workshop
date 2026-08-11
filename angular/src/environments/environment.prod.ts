export const environment = {
  production: true,
  apiUrl: process.env['API_URL'] || 'https://api.todoapp.com/api',
  endpoints: {
    tasks: '/tasks',
  },
};
