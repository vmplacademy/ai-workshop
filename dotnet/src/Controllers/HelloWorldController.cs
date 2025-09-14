using Microsoft.AspNetCore.Mvc;

namespace TodoApp.Controllers;

[ApiController]
[Route("api/hello-world")]
[Produces("application/json")]
public class HelloWorldController : ControllerBase
{
    [HttpGet]
    [ProducesResponseType(StatusCodes.Status200OK)]
    public IActionResult Get()
    {
        var response = new
        {
            Message = "Hello, World from .NET 9 API!",
            Timestamp = DateTime.UtcNow,
            Version = "1.0.0",
            Controller = "HelloWorldController",
            Environment = Environment.GetEnvironmentVariable("ASPNETCORE_ENVIRONMENT") ?? "Production"
        };

        return Ok(response);
    }
}