namespace TodoApp.Dtos
{
    /// <summary>
    /// Response DTO for the HelloWorld endpoint.
    /// </summary>
    /// <param name="Message">Response message text.</param>
    /// <param name="Timestamp">The time the response was created.</param>
    /// <param name="Version">Application version string.</param>
    /// <param name="Controller">Name of the controller that produced the response.</param>
    /// <param name="Environment">Runtime environment name.</param>
    public record HelloWorldResponse(
        string Message,
        DateTime Timestamp,
        string Version,
        string Controller,
        string Environment
    );
}