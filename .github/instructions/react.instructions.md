---
description: 'React development guidelines and best practices for modern frontend applications'
applyTo: '**/package.json, **/*.jsx, **/*.tsx, **/*.js, **/*.ts, **/vite.config.js, **/tailwind.config.js'
---

# React Development Guidelines

## Technology Stack Requirements
- **React Version**: React 18+
- **Build Tool**: Vite 5+
- **Language**: TypeScript (preferred) or JavaScript
- **Styling**: Tailwind CSS
- **State Management**: React Context + useReducer or Zustand
- **HTTP Client**: Axios or Fetch API with React Query/TanStack Query

## Project Structure
Standard Vite React project structure with feature-based organization:
```
src/
├── components/           # Reusable UI components
│   ├── ui/              # Basic UI primitives (Button, Input, etc.)
│   └── layout/          # Layout components (Header, Footer, etc.)
├── features/            # Feature-based modules
│   └── tasks/           # Task management feature
│       ├── components/  # Task-specific components
│       ├── hooks/       # Custom hooks for tasks
│       ├── services/    # API services for tasks
│       └── types/       # TypeScript types for tasks
├── hooks/               # Global custom hooks
├── services/            # Global API services
├── utils/               # Utility functions
├── types/               # Global TypeScript types
├── styles/              # Global styles and Tailwind config
├── App.tsx              # Main application component
├── main.tsx             # Application entry point
└── vite-env.d.ts        # Vite type definitions
```

## Essential Dependencies

### Core Dependencies
```json
{
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-router-dom": "^6.15.0",
    "@tanstack/react-query": "^4.35.0",
    "axios": "^1.5.0",
    "clsx": "^2.0.0",
    "tailwind-merge": "^1.14.0"
  },
  "devDependencies": {
    "@types/react": "^18.2.15",
    "@types/react-dom": "^18.2.7",
    "@vitejs/plugin-react": "^4.0.3",
    "vite": "^4.4.5",
    "typescript": "^5.0.2",
    "tailwindcss": "^3.3.0",
    "postcss": "^8.4.24",
    "autoprefixer": "^10.4.14",
    "@testing-library/react": "^13.4.0",
    "@testing-library/jest-dom": "^5.16.4",
    "vitest": "^0.34.0",
    "jsdom": "^22.1.0"
  }
}
```

## Configuration Files

### Vite Configuration (vite.config.js)
```javascript
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
  server: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
  },
})
```

### Tailwind Configuration (tailwind.config.js)
```javascript
/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: {
          50: '#eff6ff',
          500: '#3b82f6',
          600: '#2563eb',
          700: '#1d4ed8',
        },
      },
    },
  },
  plugins: [],
}
```

### TypeScript Configuration (tsconfig.json)
```json
{
  "compilerOptions": {
    "target": "ES2020",
    "useDefineForClassFields": true,
    "lib": ["ES2020", "DOM", "DOM.Iterable"],
    "module": "ESNext",
    "skipLibCheck": true,
    "moduleResolution": "bundler",
    "allowImportingTsExtensions": true,
    "resolveJsonModule": true,
    "isolatedModules": true,
    "noEmit": true,
    "jsx": "react-jsx",
    "strict": true,
    "noUnusedLocals": true,
    "noUnusedParameters": true,
    "noFallthroughCasesInSwitch": true,
    "baseUrl": ".",
    "paths": {
      "@/*": ["./src/*"]
    }
  },
  "include": ["src"],
  "references": [{ "path": "./tsconfig.node.json" }]
}
```

## Component Development Patterns

### Functional Components with TypeScript
```tsx
import React from 'react'
import { Task } from '@/types/task'

interface TaskItemProps {
  task: Task
  onToggle: (id: string) => void
  onDelete: (id: string) => void
}

export const TaskItem: React.FC<TaskItemProps> = ({ task, onToggle, onDelete }) => {
  return (
    <div className="flex items-center justify-between p-4 border rounded-lg">
      <div className="flex items-center space-x-3">
        <input
          type="checkbox"
          checked={task.completed}
          onChange={() => onToggle(task.id)}
          className="w-4 h-4 text-primary-600 rounded focus:ring-primary-500"
        />
        <span className={task.completed ? 'line-through text-gray-500' : ''}>
          {task.title}
        </span>
      </div>
      <button
        onClick={() => onDelete(task.id)}
        className="text-red-500 hover:text-red-700"
      >
        Delete
      </button>
    </div>
  )
}
```

### Custom Hooks
```tsx
import { useState, useEffect } from 'react'
import { Task } from '@/types/task'
import { taskService } from '@/services/taskService'

export const useTasks = () => {
  const [tasks, setTasks] = useState<Task[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        setLoading(true)
        const fetchedTasks = await taskService.getTasks()
        setTasks(fetchedTasks)
        setError(null)
      } catch (err) {
        setError(err instanceof Error ? err.message : 'Failed to fetch tasks')
      } finally {
        setLoading(false)
      }
    }

    fetchTasks()
  }, [])

  const addTask = async (title: string, description?: string) => {
    try {
      const newTask = await taskService.createTask({ title, description })
      setTasks(prev => [...prev, newTask])
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to create task')
    }
  }

  const toggleTask = async (id: string) => {
    try {
      const task = tasks.find(t => t.id === id)
      if (task) {
        const updatedTask = await taskService.updateTask(id, {
          ...task,
          completed: !task.completed
        })
        setTasks(prev => prev.map(t => t.id === id ? updatedTask : t))
      }
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to update task')
    }
  }

  const deleteTask = async (id: string) => {
    try {
      await taskService.deleteTask(id)
      setTasks(prev => prev.filter(t => t.id !== id))
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to delete task')
    }
  }

  return {
    tasks,
    loading,
    error,
    addTask,
    toggleTask,
    deleteTask
  }
}
```

## State Management

### Context API with useReducer
```tsx
import React, { createContext, useContext, useReducer, ReactNode } from 'react'
import { Task } from '@/types/task'

interface TaskState {
  tasks: Task[]
  loading: boolean
  error: string | null
}

type TaskAction =
  | { type: 'SET_LOADING'; payload: boolean }
  | { type: 'SET_TASKS'; payload: Task[] }
  | { type: 'ADD_TASK'; payload: Task }
  | { type: 'UPDATE_TASK'; payload: Task }
  | { type: 'DELETE_TASK'; payload: string }
  | { type: 'SET_ERROR'; payload: string | null }

const initialState: TaskState = {
  tasks: [],
  loading: false,
  error: null
}

const taskReducer = (state: TaskState, action: TaskAction): TaskState => {
  switch (action.type) {
    case 'SET_LOADING':
      return { ...state, loading: action.payload }
    case 'SET_TASKS':
      return { ...state, tasks: action.payload, error: null }
    case 'ADD_TASK':
      return { ...state, tasks: [...state.tasks, action.payload] }
    case 'UPDATE_TASK':
      return {
        ...state,
        tasks: state.tasks.map(task =>
          task.id === action.payload.id ? action.payload : task
        )
      }
    case 'DELETE_TASK':
      return {
        ...state,
        tasks: state.tasks.filter(task => task.id !== action.payload)
      }
    case 'SET_ERROR':
      return { ...state, error: action.payload }
    default:
      return state
  }
}

const TaskContext = createContext<{
  state: TaskState
  dispatch: React.Dispatch<TaskAction>
} | undefined>(undefined)

export const TaskProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [state, dispatch] = useReducer(taskReducer, initialState)

  return (
    <TaskContext.Provider value={{ state, dispatch }}>
      {children}
    </TaskContext.Provider>
  )
}

export const useTaskContext = () => {
  const context = useContext(TaskContext)
  if (!context) {
    throw new Error('useTaskContext must be used within a TaskProvider')
  }
  return context
}
```

## API Integration

### API Service Layer
```tsx
import axios from 'axios'
import { Task, CreateTaskRequest, UpdateTaskRequest } from '@/types/task'

const api = axios.create({
  baseURL: '/api',
  timeout: 10000,
})

// Request interceptor for adding auth headers
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('authToken')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// Response interceptor for error handling
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Handle unauthorized access
      localStorage.removeItem('authToken')
      window.location.href = '/login'
    }
    return Promise.reject(error)
  }
)

export const taskService = {
  async getTasks(): Promise<Task[]> {
    const response = await api.get<Task[]>('/tasks')
    return response.data
  },

  async createTask(task: CreateTaskRequest): Promise<Task> {
    const response = await api.post<Task>('/tasks', task)
    return response.data
  },

  async updateTask(id: string, task: UpdateTaskRequest): Promise<Task> {
    const response = await api.put<Task>(`/tasks/${id}`, task)
    return response.data
  },

  async deleteTask(id: string): Promise<void> {
    await api.delete(`/tasks/${id}`)
  }
}
```

## React Query Integration
```tsx
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { taskService } from '@/services/taskService'
import { CreateTaskRequest, UpdateTaskRequest } from '@/types/task'

export const useTasksQuery = () => {
  return useQuery({
    queryKey: ['tasks'],
    queryFn: taskService.getTasks,
    staleTime: 5 * 60 * 1000, // 5 minutes
  })
}

export const useCreateTaskMutation = () => {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: (task: CreateTaskRequest) => taskService.createTask(task),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })
}

export const useUpdateTaskMutation = () => {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: ({ id, task }: { id: string; task: UpdateTaskRequest }) =>
      taskService.updateTask(id, task),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })
}

export const useDeleteTaskMutation = () => {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: (id: string) => taskService.deleteTask(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['tasks'] })
    },
  })
}
```

## Testing Standards

### Component Testing with Vitest and React Testing Library
```tsx
import { render, screen, fireEvent, waitFor } from '@testing-library/react'
import { vi } from 'vitest'
import { TaskItem } from '@/components/TaskItem'
import { Task } from '@/types/task'

const mockTask: Task = {
  id: '1',
  title: 'Test Task',
  description: 'Test Description',
  completed: false,
  createdAt: new Date().toISOString(),
  updatedAt: new Date().toISOString(),
}

describe('TaskItem', () => {
  const mockOnToggle = vi.fn()
  const mockOnDelete = vi.fn()

  beforeEach(() => {
    vi.clearAllMocks()
  })

  it('should render task title correctly', () => {
    render(
      <TaskItem
        task={mockTask}
        onToggle={mockOnToggle}
        onDelete={mockOnDelete}
      />
    )

    expect(screen.getByText('Test Task')).toBeInTheDocument()
  })

  it('should call onToggle when checkbox is clicked', () => {
    render(
      <TaskItem
        task={mockTask}
        onToggle={mockOnToggle}
        onDelete={mockOnDelete}
      />
    )

    const checkbox = screen.getByRole('checkbox')
    fireEvent.click(checkbox)

    expect(mockOnToggle).toHaveBeenCalledWith('1')
  })

  it('should call onDelete when delete button is clicked', () => {
    render(
      <TaskItem
        task={mockTask}
        onToggle={mockOnToggle}
        onDelete={mockOnDelete}
      />
    )

    const deleteButton = screen.getByText('Delete')
    fireEvent.click(deleteButton)

    expect(mockOnDelete).toHaveBeenCalledWith('1')
  })

  it('should apply line-through style when task is completed', () => {
    const completedTask = { ...mockTask, completed: true }
    render(
      <TaskItem
        task={completedTask}
        onToggle={mockOnToggle}
        onDelete={mockOnDelete}
      />
    )

    const title = screen.getByText('Test Task')
    expect(title).toHaveClass('line-through')
  })
})
```

### Hook Testing
```tsx
import { renderHook, act } from '@testing-library/react'
import { vi } from 'vitest'
import { useTasks } from '@/hooks/useTasks'
import { taskService } from '@/services/taskService'

vi.mock('@/services/taskService')

describe('useTasks', () => {
  const mockTasks = [
    {
      id: '1',
      title: 'Task 1',
      completed: false,
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString(),
    },
  ]

  beforeEach(() => {
    vi.clearAllMocks()
  })

  it('should fetch tasks on mount', async () => {
    vi.mocked(taskService.getTasks).mockResolvedValue(mockTasks)

    const { result } = renderHook(() => useTasks())

    expect(result.current.loading).toBe(true)

    await act(async () => {
      await new Promise(resolve => setTimeout(resolve, 0))
    })

    expect(result.current.loading).toBe(false)
    expect(result.current.tasks).toEqual(mockTasks)
    expect(taskService.getTasks).toHaveBeenCalledTimes(1)
  })

  it('should add new task', async () => {
    const newTask = {
      id: '2',
      title: 'New Task',
      completed: false,
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString(),
    }

    vi.mocked(taskService.getTasks).mockResolvedValue(mockTasks)
    vi.mocked(taskService.createTask).mockResolvedValue(newTask)

    const { result } = renderHook(() => useTasks())

    await act(async () => {
      await new Promise(resolve => setTimeout(resolve, 0))
    })

    await act(async () => {
      await result.current.addTask('New Task')
    })

    expect(result.current.tasks).toHaveLength(2)
    expect(result.current.tasks[1]).toEqual(newTask)
  })
})
```

## Styling Guidelines

### Tailwind CSS Best Practices
```tsx
// Use semantic utility classes
const Button: React.FC<ButtonProps> = ({ variant = 'primary', size = 'md', children, ...props }) => {
  const baseClasses = 'inline-flex items-center justify-center rounded-md font-medium transition-colors focus:outline-none focus:ring-2 focus:ring-offset-2'
  
  const variantClasses = {
    primary: 'bg-primary-600 text-white hover:bg-primary-700 focus:ring-primary-500',
    secondary: 'bg-gray-200 text-gray-900 hover:bg-gray-300 focus:ring-gray-500',
    danger: 'bg-red-600 text-white hover:bg-red-700 focus:ring-red-500'
  }
  
  const sizeClasses = {
    sm: 'px-3 py-1.5 text-sm',
    md: 'px-4 py-2 text-base',
    lg: 'px-6 py-3 text-lg'
  }
  
  const classes = clsx(
    baseClasses,
    variantClasses[variant],
    sizeClasses[size]
  )
  
  return (
    <button className={classes} {...props}>
      {children}
    </button>
  )
}
```

## Performance Optimization

### Code Splitting with React.lazy
```tsx
import { Suspense, lazy } from 'react'

const TaskList = lazy(() => import('@/features/tasks/components/TaskList'))
const TaskForm = lazy(() => import('@/features/tasks/components/TaskForm'))

const TasksPage: React.FC = () => {
  return (
    <div className="container mx-auto px-4 py-8">
      <Suspense fallback={<div>Loading tasks...</div>}>
        <TaskList />
      </Suspense>
      <Suspense fallback={<div>Loading form...</div>}>
        <TaskForm />
      </Suspense>
    </div>
  )
}
```

### Memoization
```tsx
import { memo, useMemo, useCallback } from 'react'

export const TaskList = memo<TaskListProps>(({ tasks, onToggle, onDelete }) => {
  const completedTasks = useMemo(() => 
    tasks.filter(task => task.completed), 
    [tasks]
  )
  
  const handleToggle = useCallback((id: string) => {
    onToggle(id)
  }, [onToggle])
  
  const handleDelete = useCallback((id: string) => {
    onDelete(id)
  }, [onDelete])
  
  return (
    <div className="space-y-4">
      {tasks.map(task => (
        <TaskItem
          key={task.id}
          task={task}
          onToggle={handleToggle}
          onDelete={handleDelete}
        />
      ))}
      <div className="text-sm text-gray-500">
        Completed: {completedTasks.length} / {tasks.length}
      </div>
    </div>
  )
})
```

## Accessibility

### ARIA Labels and Semantic HTML
```tsx
const TaskForm: React.FC = () => {
  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')
  
  return (
    <form
      onSubmit={handleSubmit}
      role="form"
      aria-labelledby="task-form-title"
    >
      <h2 id="task-form-title" className="text-xl font-semibold mb-4">
        Add New Task
      </h2>
      
      <div className="mb-4">
        <label
          htmlFor="task-title"
          className="block text-sm font-medium text-gray-700 mb-2"
        >
          Task Title *
        </label>
        <input
          id="task-title"
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
          aria-describedby="task-title-error"
          className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-primary-500"
        />
      </div>
      
      <button
        type="submit"
        disabled={!title.trim()}
        aria-label="Create new task"
        className="w-full bg-primary-600 text-white py-2 px-4 rounded-md hover:bg-primary-700 disabled:opacity-50 disabled:cursor-not-allowed"
      >
        Add Task
      </button>
    </form>
  )
}
```

## Error Boundaries

### Error Boundary Component
```tsx
import React, { Component, ReactNode } from 'react'

interface Props {
  children: ReactNode
  fallback?: ReactNode
}

interface State {
  hasError: boolean
  error?: Error
}

export class ErrorBoundary extends Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = { hasError: false }
  }

  static getDerivedStateFromError(error: Error): State {
    return { hasError: true, error }
  }

  componentDidCatch(error: Error, errorInfo: React.ErrorInfo) {
    console.error('Error caught by boundary:', error, errorInfo)
    // Log to error reporting service
  }

  render() {
    if (this.state.hasError) {
      return (
        this.props.fallback || (
          <div className="p-4 border border-red-300 rounded-md bg-red-50">
            <h2 className="text-lg font-semibold text-red-800 mb-2">
              Something went wrong
            </h2>
            <p className="text-red-600">
              {this.state.error?.message || 'An unexpected error occurred'}
            </p>
            <button
              onClick={() => this.setState({ hasError: false })}
              className="mt-4 px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700"
            >
              Try Again
            </button>
          </div>
        )
      )
    }

    return this.props.children
  }
}
```

## Build and Deployment

### Environment Variables
```bash
# .env.local
VITE_API_BASE_URL=http://localhost:8080/api
VITE_APP_NAME=TodoList App
VITE_VERSION=$npm_package_version
```

### Build Configuration
```json
{
  "scripts": {
    "dev": "vite",
    "build": "tsc && vite build",
    "preview": "vite preview",
    "test": "vitest",
    "test:ui": "vitest --ui",
    "test:coverage": "vitest --coverage",
    "lint": "eslint . --ext ts,tsx --report-unused-disable-directives --max-warnings 0",
    "lint:fix": "eslint . --ext ts,tsx --fix"
  }
}
```

### Docker Configuration
```dockerfile
# Multi-stage build
FROM node:18-alpine AS builder

WORKDIR /app
COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=builder /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```
