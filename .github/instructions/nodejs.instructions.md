---
description: 'Node.js development guidelines and best practices for backend API development'
applyTo: '**/package.json, **/*.js, **/*.ts, **/server.js, **/app.js'
---

# Node.js Development Guidelines

## Technology Stack Requirements
- **Node.js Version**: Node.js 20 LTS
- **Language**: TypeScript (preferred) or JavaScript
- **Framework**: Express.js 4+
- **Database**: PostgreSQL with pg driver
- **Testing**: Jest with Supertest
- **Process Management**: PM2 (production)

## Project Structure
Standard Node.js project with MVC pattern:
```
src/
├── controllers/         # Route handlers and request/response logic
├── services/           # Business logic layer
├── repositories/       # Data access layer
├── models/            # Data models and types
├── middleware/        # Express middleware functions
├── routes/            # Route definitions
├── config/            # Configuration files
├── utils/             # Utility functions
├── types/             # TypeScript type definitions
├── app.js             # Express app configuration
└── server.js          # Server entry point
tests/
├── unit/              # Unit tests
├── integration/       # Integration tests
├── fixtures/          # Test data and fixtures
└── setup.js           # Test configuration
docs/
└── api.md             # API documentation
```

## Essential Dependencies

### Core Dependencies
```json
{
  "dependencies": {
    "express": "^4.18.2",
    "pg": "^8.11.3",
    "cors": "^2.8.5",
    "helmet": "^7.0.0",
    "compression": "^1.7.4",
    "morgan": "^1.10.0",
    "joi": "^17.10.0",
    "bcrypt": "^5.1.1",
    "jsonwebtoken": "^9.0.2",
    "dotenv": "^16.3.1",
    "winston": "^3.10.0"
  },
  "devDependencies": {
    "@types/node": "^20.5.0",
    "@types/express": "^4.17.17",
    "@types/pg": "^8.10.2",
    "@types/cors": "^2.8.13",
    "@types/bcrypt": "^5.0.0",
    "@types/jsonwebtoken": "^9.0.2",
    "@types/jest": "^29.5.4",
    "@types/supertest": "^2.0.12",
    "typescript": "^5.1.6",
    "ts-node": "^10.9.1",
    "nodemon": "^3.0.1",
    "jest": "^29.6.2",
    "supertest": "^6.3.3",
    "eslint": "^8.47.0",
    "@typescript-eslint/eslint-plugin": "^6.4.0",
    "@typescript-eslint/parser": "^6.4.0"
  }
}
```

## Configuration

### TypeScript Configuration (tsconfig.json)
```json
{
  "compilerOptions": {
    "target": "ES2022",
    "module": "commonjs",
    "lib": ["ES2022"],
    "outDir": "./dist",
    "rootDir": "./src",
    "strict": true,
    "esModuleInterop": true,
    "skipLibCheck": true,
    "forceConsistentCasingInFileNames": true,
    "resolveJsonModule": true,
    "declaration": true,
    "declarationMap": true,
    "sourceMap": true,
    "removeComments": true,
    "noImplicitAny": true,
    "strictNullChecks": true,
    "strictFunctionTypes": true,
    "noImplicitThis": true,
    "noImplicitReturns": true,
    "noFallthroughCasesInSwitch": true,
    "moduleResolution": "node",
    "baseUrl": "./",
    "paths": {
      "@/*": ["src/*"]
    }
  },
  "include": ["src/**/*"],
  "exclude": ["node_modules", "dist", "tests"]
}
```

### Environment Configuration
```bash
# .env
NODE_ENV=development
PORT=3000
DB_HOST=localhost
DB_PORT=5432
DB_NAME=todolist
DB_USER=todolist
DB_PASSWORD=todolist
JWT_SECRET=your-super-secret-key-here
JWT_EXPIRES_IN=24h
LOG_LEVEL=debug
```

### Database Configuration
```typescript
// src/config/database.ts
import { Pool } from 'pg'

const pool = new Pool({
  host: process.env.DB_HOST || 'localhost',
  port: parseInt(process.env.DB_PORT || '5432'),
  database: process.env.DB_NAME || 'todolist',
  user: process.env.DB_USER || 'todolist',
  password: process.env.DB_PASSWORD || 'todolist',
  max: 20,
  idleTimeoutMillis: 30000,
  connectionTimeoutMillis: 2000,
})

export default pool
```

## Application Architecture

### Express App Configuration
```typescript
// src/app.ts
import express from 'express'
import cors from 'cors'
import helmet from 'helmet'
import compression from 'compression'
import morgan from 'morgan'
import { errorHandler } from './middleware/errorHandler'
import { notFound } from './middleware/notFound'
import taskRoutes from './routes/taskRoutes'
import healthRoutes from './routes/healthRoutes'

const app = express()

// Security middleware
app.use(helmet())
app.use(cors({
  origin: process.env.CORS_ORIGIN || 'http://localhost:3000',
  credentials: true
}))

// General middleware
app.use(compression())
app.use(morgan('combined'))
app.use(express.json({ limit: '10mb' }))
app.use(express.urlencoded({ extended: true }))

// Routes
app.use('/api/health', healthRoutes)
app.use('/api/tasks', taskRoutes)

// Error handling
app.use(notFound)
app.use(errorHandler)

export default app
```

### Server Entry Point
```typescript
// src/server.ts
import app from './app'
import { logger } from './utils/logger'

const PORT = process.env.PORT || 3000

const server = app.listen(PORT, () => {
  logger.info(`Server running on port ${PORT}`)
  logger.info(`Environment: ${process.env.NODE_ENV}`)
})

// Graceful shutdown
process.on('SIGTERM', () => {
  logger.info('SIGTERM received, shutting down gracefully')
  server.close(() => {
    logger.info('Process terminated')
    process.exit(0)
  })
})

process.on('SIGINT', () => {
  logger.info('SIGINT received, shutting down gracefully')
  server.close(() => {
    logger.info('Process terminated')
    process.exit(0)
  })
})

export default server
```

## Data Layer

### Repository Pattern
```typescript
// src/repositories/taskRepository.ts
import pool from '../config/database'
import { Task, CreateTaskData, UpdateTaskData } from '../types/task'

export class TaskRepository {
  async findAll(): Promise<Task[]> {
    const query = `
      SELECT id, title, description, status, created_at, updated_at
      FROM tasks
      ORDER BY created_at DESC
    `
    const result = await pool.query(query)
    return result.rows
  }

  async findById(id: string): Promise<Task | null> {
    const query = `
      SELECT id, title, description, status, created_at, updated_at
      FROM tasks
      WHERE id = $1
    `
    const result = await pool.query(query, [id])
    return result.rows[0] || null
  }

  async create(data: CreateTaskData): Promise<Task> {
    const query = `
      INSERT INTO tasks (title, description, status)
      VALUES ($1, $2, $3)
      RETURNING id, title, description, status, created_at, updated_at
    `
    const values = [data.title, data.description, data.status || 'PENDING']
    const result = await pool.query(query, values)
    return result.rows[0]
  }

  async update(id: string, data: UpdateTaskData): Promise<Task | null> {
    const fields = []
    const values = []
    let paramCount = 1

    if (data.title !== undefined) {
      fields.push(`title = $${paramCount++}`)
      values.push(data.title)
    }
    if (data.description !== undefined) {
      fields.push(`description = $${paramCount++}`)
      values.push(data.description)
    }
    if (data.status !== undefined) {
      fields.push(`status = $${paramCount++}`)
      values.push(data.status)
    }

    if (fields.length === 0) {
      return this.findById(id)
    }

    fields.push(`updated_at = CURRENT_TIMESTAMP`)
    values.push(id)

    const query = `
      UPDATE tasks
      SET ${fields.join(', ')}
      WHERE id = $${paramCount}
      RETURNING id, title, description, status, created_at, updated_at
    `

    const result = await pool.query(query, values)
    return result.rows[0] || null
  }

  async delete(id: string): Promise<boolean> {
    const query = 'DELETE FROM tasks WHERE id = $1'
    const result = await pool.query(query, [id])
    return result.rowCount > 0
  }
}
```

### Service Layer
```typescript
// src/services/taskService.ts
import { TaskRepository } from '../repositories/taskRepository'
import { Task, CreateTaskData, UpdateTaskData } from '../types/task'
import { NotFoundError, ValidationError } from '../utils/errors'

export class TaskService {
  private taskRepository: TaskRepository

  constructor() {
    this.taskRepository = new TaskRepository()
  }

  async getAllTasks(): Promise<Task[]> {
    return this.taskRepository.findAll()
  }

  async getTaskById(id: string): Promise<Task> {
    if (!id || !id.trim()) {
      throw new ValidationError('Task ID is required')
    }

    const task = await this.taskRepository.findById(id)
    if (!task) {
      throw new NotFoundError('Task not found')
    }

    return task
  }

  async createTask(data: CreateTaskData): Promise<Task> {
    this.validateTaskData(data)
    return this.taskRepository.create(data)
  }

  async updateTask(id: string, data: UpdateTaskData): Promise<Task> {
    if (!id || !id.trim()) {
      throw new ValidationError('Task ID is required')
    }

    this.validateTaskData(data, false)
    
    const updatedTask = await this.taskRepository.update(id, data)
    if (!updatedTask) {
      throw new NotFoundError('Task not found')
    }

    return updatedTask
  }

  async deleteTask(id: string): Promise<void> {
    if (!id || !id.trim()) {
      throw new ValidationError('Task ID is required')
    }

    const deleted = await this.taskRepository.delete(id)
    if (!deleted) {
      throw new NotFoundError('Task not found')
    }
  }

  private validateTaskData(data: Partial<CreateTaskData>, requireTitle = true): void {
    if (requireTitle && (!data.title || !data.title.trim())) {
      throw new ValidationError('Task title is required')
    }

    if (data.title && data.title.length > 255) {
      throw new ValidationError('Task title must be less than 255 characters')
    }

    if (data.description && data.description.length > 1000) {
      throw new ValidationError('Task description must be less than 1000 characters')
    }

    if (data.status && !['PENDING', 'IN_PROGRESS', 'COMPLETED'].includes(data.status)) {
      throw new ValidationError('Invalid task status')
    }
  }
}
```

### Controller Layer
```typescript
// src/controllers/taskController.ts
import { Request, Response, NextFunction } from 'express'
import { TaskService } from '../services/taskService'
import { logger } from '../utils/logger'
import { asyncHandler } from '../utils/asyncHandler'

export class TaskController {
  private taskService: TaskService

  constructor() {
    this.taskService = new TaskService()
  }

  getAllTasks = asyncHandler(async (req: Request, res: Response) => {
    logger.info('Getting all tasks')
    const tasks = await this.taskService.getAllTasks()
    res.json(tasks)
  })

  getTaskById = asyncHandler(async (req: Request, res: Response) => {
    const { id } = req.params
    logger.info(`Getting task by ID: ${id}`)
    const task = await this.taskService.getTaskById(id)
    res.json(task)
  })

  createTask = asyncHandler(async (req: Request, res: Response) => {
    logger.info('Creating new task')
    const task = await this.taskService.createTask(req.body)
    res.status(201).json(task)
  })

  updateTask = asyncHandler(async (req: Request, res: Response) => {
    const { id } = req.params
    logger.info(`Updating task: ${id}`)
    const task = await this.taskService.updateTask(id, req.body)
    res.json(task)
  })

  deleteTask = asyncHandler(async (req: Request, res: Response) => {
    const { id } = req.params
    logger.info(`Deleting task: ${id}`)
    await this.taskService.deleteTask(id)
    res.status(204).send()
  })
}
```

## Middleware

### Error Handling Middleware
```typescript
// src/middleware/errorHandler.ts
import { Request, Response, NextFunction } from 'express'
import { logger } from '../utils/logger'
import { ValidationError, NotFoundError } from '../utils/errors'

export const errorHandler = (
  error: Error,
  req: Request,
  res: Response,
  next: NextFunction
): void => {
  logger.error('Error occurred:', {
    message: error.message,
    stack: error.stack,
    url: req.url,
    method: req.method
  })

  if (error instanceof ValidationError) {
    res.status(400).json({
      error: 'Validation Error',
      message: error.message,
      timestamp: new Date().toISOString()
    })
    return
  }

  if (error instanceof NotFoundError) {
    res.status(404).json({
      error: 'Not Found',
      message: error.message,
      timestamp: new Date().toISOString()
    })
    return
  }

  // Database errors
  if (error.message.includes('duplicate key')) {
    res.status(409).json({
      error: 'Conflict',
      message: 'Resource already exists',
      timestamp: new Date().toISOString()
    })
    return
  }

  // Default server error
  res.status(500).json({
    error: 'Internal Server Error',
    message: process.env.NODE_ENV === 'production' 
      ? 'Something went wrong' 
      : error.message,
    timestamp: new Date().toISOString()
  })
}

export const notFound = (req: Request, res: Response, next: NextFunction): void => {
  res.status(404).json({
    error: 'Not Found',
    message: `Route ${req.method} ${req.url} not found`,
    timestamp: new Date().toISOString()
  })
}
```

### Validation Middleware
```typescript
// src/middleware/validation.ts
import { Request, Response, NextFunction } from 'express'
import Joi from 'joi'

export const validate = (schema: Joi.ObjectSchema) => {
  return (req: Request, res: Response, next: NextFunction): void => {
    const { error } = schema.validate(req.body, { abortEarly: false })
    
    if (error) {
      const errorMessages = error.details.map(detail => detail.message)
      res.status(400).json({
        error: 'Validation Error',
        messages: errorMessages,
        timestamp: new Date().toISOString()
      })
      return
    }
    
    next()
  }
}

// Validation schemas
export const createTaskSchema = Joi.object({
  title: Joi.string().required().max(255).trim(),
  description: Joi.string().optional().max(1000).trim(),
  status: Joi.string().valid('PENDING', 'IN_PROGRESS', 'COMPLETED').optional()
})

export const updateTaskSchema = Joi.object({
  title: Joi.string().optional().max(255).trim(),
  description: Joi.string().optional().max(1000).trim(),
  status: Joi.string().valid('PENDING', 'IN_PROGRESS', 'COMPLETED').optional()
}).min(1)
```

## Routing

### Route Definition
```typescript
// src/routes/taskRoutes.ts
import { Router } from 'express'
import { TaskController } from '../controllers/taskController'
import { validate, createTaskSchema, updateTaskSchema } from '../middleware/validation'

const router = Router()
const taskController = new TaskController()

/**
 * @route   GET /api/tasks
 * @desc    Get all tasks
 * @access  Public
 */
router.get('/', taskController.getAllTasks)

/**
 * @route   GET /api/tasks/:id
 * @desc    Get task by ID
 * @access  Public
 */
router.get('/:id', taskController.getTaskById)

/**
 * @route   POST /api/tasks
 * @desc    Create new task
 * @access  Public
 */
router.post('/', validate(createTaskSchema), taskController.createTask)

/**
 * @route   PUT /api/tasks/:id
 * @desc    Update task
 * @access  Public
 */
router.put('/:id', validate(updateTaskSchema), taskController.updateTask)

/**
 * @route   DELETE /api/tasks/:id
 * @desc    Delete task
 * @access  Public
 */
router.delete('/:id', taskController.deleteTask)

export default router
```

## Testing

### Unit Tests
```typescript
// tests/unit/services/taskService.test.ts
import { TaskService } from '../../../src/services/taskService'
import { TaskRepository } from '../../../src/repositories/taskRepository'
import { NotFoundError, ValidationError } from '../../../src/utils/errors'

// Mock the repository
jest.mock('../../../src/repositories/taskRepository')

describe('TaskService', () => {
  let taskService: TaskService
  let mockTaskRepository: jest.Mocked<TaskRepository>

  beforeEach(() => {
    taskService = new TaskService()
    mockTaskRepository = TaskRepository.prototype as jest.Mocked<TaskRepository>
    jest.clearAllMocks()
  })

  describe('getAllTasks', () => {
    it('should return all tasks', async () => {
      const mockTasks = [
        { id: '1', title: 'Task 1', description: 'Description 1', status: 'PENDING' }
      ]
      mockTaskRepository.findAll.mockResolvedValue(mockTasks)

      const result = await taskService.getAllTasks()

      expect(result).toEqual(mockTasks)
      expect(mockTaskRepository.findAll).toHaveBeenCalledTimes(1)
    })
  })

  describe('getTaskById', () => {
    it('should return task when found', async () => {
      const mockTask = { id: '1', title: 'Task 1', description: 'Description 1', status: 'PENDING' }
      mockTaskRepository.findById.mockResolvedValue(mockTask)

      const result = await taskService.getTaskById('1')

      expect(result).toEqual(mockTask)
      expect(mockTaskRepository.findById).toHaveBeenCalledWith('1')
    })

    it('should throw NotFoundError when task not found', async () => {
      mockTaskRepository.findById.mockResolvedValue(null)

      await expect(taskService.getTaskById('1')).rejects.toThrow(NotFoundError)
      expect(mockTaskRepository.findById).toHaveBeenCalledWith('1')
    })

    it('should throw ValidationError when id is empty', async () => {
      await expect(taskService.getTaskById('')).rejects.toThrow(ValidationError)
      expect(mockTaskRepository.findById).not.toHaveBeenCalled()
    })
  })

  describe('createTask', () => {
    it('should create task with valid data', async () => {
      const createData = { title: 'New Task', description: 'Description', status: 'PENDING' }
      const mockCreatedTask = { id: '1', ...createData, created_at: new Date(), updated_at: new Date() }
      mockTaskRepository.create.mockResolvedValue(mockCreatedTask)

      const result = await taskService.createTask(createData)

      expect(result).toEqual(mockCreatedTask)
      expect(mockTaskRepository.create).toHaveBeenCalledWith(createData)
    })

    it('should throw ValidationError when title is missing', async () => {
      const createData = { description: 'Description', status: 'PENDING' }

      await expect(taskService.createTask(createData as any)).rejects.toThrow(ValidationError)
      expect(mockTaskRepository.create).not.toHaveBeenCalled()
    })
  })
})
```

### Integration Tests
```typescript
// tests/integration/taskRoutes.test.ts
import request from 'supertest'
import app from '../../src/app'
import pool from '../../src/config/database'

describe('Task Routes', () => {
  beforeAll(async () => {
    // Setup test database
    await pool.query(`
      CREATE TABLE IF NOT EXISTS tasks (
        id SERIAL PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description TEXT,
        status VARCHAR(20) DEFAULT 'PENDING',
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
      )
    `)
  })

  afterAll(async () => {
    // Cleanup
    await pool.query('DROP TABLE IF EXISTS tasks')
    await pool.end()
  })

  beforeEach(async () => {
    // Clear data before each test
    await pool.query('DELETE FROM tasks')
  })

  describe('GET /api/tasks', () => {
    it('should return empty array when no tasks exist', async () => {
      const response = await request(app)
        .get('/api/tasks')
        .expect(200)

      expect(response.body).toEqual([])
    })

    it('should return all tasks when they exist', async () => {
      // Insert test data
      await pool.query(
        'INSERT INTO tasks (title, description, status) VALUES ($1, $2, $3)',
        ['Test Task', 'Test Description', 'PENDING']
      )

      const response = await request(app)
        .get('/api/tasks')
        .expect(200)

      expect(response.body).toHaveLength(1)
      expect(response.body[0]).toMatchObject({
        title: 'Test Task',
        description: 'Test Description',
        status: 'PENDING'
      })
    })
  })

  describe('POST /api/tasks', () => {
    it('should create new task with valid data', async () => {
      const taskData = {
        title: 'New Task',
        description: 'New Description',
        status: 'PENDING'
      }

      const response = await request(app)
        .post('/api/tasks')
        .send(taskData)
        .expect(201)

      expect(response.body).toMatchObject(taskData)
      expect(response.body.id).toBeDefined()
    })

    it('should return 400 when title is missing', async () => {
      const taskData = {
        description: 'Description without title'
      }

      await request(app)
        .post('/api/tasks')
        .send(taskData)
        .expect(400)
    })
  })
})
```

## Database Management

### Database Schema
```sql
-- migrations/001_create_tasks_table.sql
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_tasks_status ON tasks(status);
CREATE INDEX idx_tasks_created_at ON tasks(created_at DESC);

-- Create trigger to update updated_at column
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_tasks_updated_at BEFORE UPDATE ON tasks
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
```

## Logging

### Logger Configuration
```typescript
// src/utils/logger.ts
import winston from 'winston'

const logLevel = process.env.LOG_LEVEL || 'info'

export const logger = winston.createLogger({
  level: logLevel,
  format: winston.format.combine(
    winston.format.timestamp(),
    winston.format.errors({ stack: true }),
    winston.format.json()
  ),
  defaultMeta: { service: 'todolist-api' },
  transports: [
    new winston.transports.File({ 
      filename: 'logs/error.log', 
      level: 'error' 
    }),
    new winston.transports.File({ 
      filename: 'logs/combined.log' 
    })
  ]
})

if (process.env.NODE_ENV !== 'production') {
  logger.add(new winston.transports.Console({
    format: winston.format.combine(
      winston.format.colorize(),
      winston.format.simple()
    )
  }))
}
```

## Performance and Security

### Rate Limiting
```typescript
// src/middleware/rateLimiter.ts
import rateLimit from 'express-rate-limit'

export const createRateLimiter = (windowMs: number, max: number) => {
  return rateLimit({
    windowMs,
    max,
    message: {
      error: 'Too Many Requests',
      message: 'Too many requests from this IP, please try again later.',
      timestamp: new Date().toISOString()
    },
    standardHeaders: true,
    legacyHeaders: false
  })
}

// API rate limiter - 100 requests per 15 minutes
export const apiLimiter = createRateLimiter(15 * 60 * 1000, 100)

// Strict rate limiter for creation endpoints - 10 requests per 15 minutes
export const createLimiter = createRateLimiter(15 * 60 * 1000, 10)
```

### Input Sanitization
```typescript
// src/utils/sanitizer.ts
export const sanitizeString = (input: string): string => {
  if (typeof input !== 'string') return ''
  
  return input
    .trim()
    .replace(/[<>]/g, '') // Remove potential HTML tags
    .replace(/javascript:/gi, '') // Remove javascript: protocols
    .substring(0, 1000) // Limit length
}

export const sanitizeTaskData = (data: any): any => {
  return {
    ...data,
    title: data.title ? sanitizeString(data.title) : undefined,
    description: data.description ? sanitizeString(data.description) : undefined
  }
}
```

## Docker Configuration

### Dockerfile
```dockerfile
FROM node:20-alpine

WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm ci --only=production

# Copy source code
COPY . .

# Build TypeScript
RUN npm run build

# Create non-root user
RUN addgroup -g 1001 -S nodejs
RUN adduser -S nodejs -u 1001

# Change ownership of the app directory
RUN chown -R nodejs:nodejs /app
USER nodejs

# Expose port
EXPOSE 3000

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD node healthcheck.js

# Start the application
CMD ["node", "dist/server.js"]
```

### Docker Compose
```yaml
version: '3.8'

services:
  api:
    build: .
    ports:
      - "3000:3000"
    environment:
      - NODE_ENV=production
      - DB_HOST=postgres
      - DB_PORT=5432
      - DB_NAME=todolist
      - DB_USER=todolist
      - DB_PASSWORD=todolist
    depends_on:
      - postgres
    volumes:
      - ./logs:/app/logs

  postgres:
    image: postgres:15-alpine
    environment:
      - POSTGRES_DB=todolist
      - POSTGRES_USER=todolist
      - POSTGRES_PASSWORD=todolist
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./migrations:/docker-entrypoint-initdb.d

volumes:
  postgres_data:
```

## Deployment Scripts

### Package.json Scripts
```json
{
  "scripts": {
    "start": "node dist/server.js",
    "dev": "nodemon src/server.ts",
    "build": "tsc",
    "test": "jest",
    "test:watch": "jest --watch",
    "test:coverage": "jest --coverage",
    "lint": "eslint src/**/*.ts",
    "lint:fix": "eslint src/**/*.ts --fix",
    "db:migrate": "node scripts/migrate.js",
    "db:seed": "node scripts/seed.js"
  }
}
```
