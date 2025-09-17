using Microsoft.AspNetCore.Mvc;
using TodoApp.Dtos;

namespace TodoApp.Controllers
{
    [ApiController]
    [Route("api/hello-world")]
    [Produces("application/json")]
    public class HelloWorldController : ControllerBase
    {
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
}