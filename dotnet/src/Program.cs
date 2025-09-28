using Microsoft.EntityFrameworkCore;
using TodoApp.Domains.Repositories;
using TodoApp.Domains.Services;

var builder = WebApplication.CreateBuilder(args);

// Configure CORS
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowAngularApp", policy =>
    {
        policy.WithOrigins("http://localhost:4200")
              .AllowAnyHeader()
              .AllowAnyMethod();
    });
});

// Add services to the container.
builder.Services.AddControllers()
    .AddJsonOptions(options =>
    {
        options.JsonSerializerOptions.Converters.Add(new System.Text.Json.Serialization.JsonStringEnumConverter());
    });
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// In memory database setup
builder.Services.AddDbContext<TodoAppDbContext>(options =>
    options.UseInMemoryDatabase("TodoAppDb"));

builder.Services.AddScoped<ITaskService, TaskService>();

var app = builder.Build();
app.UseSwagger();
app.UseSwaggerUI();

// Only use HTTPS redirection in production
if (!app.Environment.IsDevelopment())
{
    app.UseHttpsRedirection();
}

// Use CORS middleware - must be before app.UseRouting() and app.MapControllers()
app.UseCors("AllowAngularApp");

// Map controllers
app.MapControllers();

app.Run();
