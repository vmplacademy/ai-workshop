using System.IO;
using System.Reflection;
using Microsoft.EntityFrameworkCore;
using TodoApp.Domains.Models;
using TodoApp.Domains.Services;

var builder = WebApplication.CreateBuilder(args);


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

// Map controllers
app.MapControllers();

app.Run();
