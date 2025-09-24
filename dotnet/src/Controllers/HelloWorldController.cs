using Microsoft.AspNetCore.Mvc;
using TodoApp.Dtos;

namespace TodoApp.Controllers;

/// <summary>
/// API controller that returns a hello world response and environment info.
/// </summary>
[ApiController]
[Route("api/hello-world")]
[Produces("application/json")]
public class HelloWorldController : ControllerBase
{
    /// <summary>
    /// Returns a HelloWorldResponse containing a greeting, current UTC time, version, controller name, and environment.
    /// </summary>
    /// <returns>An OkObjectResult with a HelloWorldResponse payload.</returns>
    [HttpGet]
    [ProducesResponseType(StatusCodes.Status200OK)]
    public IActionResult Get()
    {
        var response = new HelloWorldResponse(
            "Hello, World from .NET 9 API!",
            DateTime.UtcNow,
            "1.0.0",
            nameof(HelloWorldController),
            Environment.GetEnvironmentVariable("ASPNETCORE_ENVIRONMENT") ?? "Production"
        );

        return Ok(response);
    }
}