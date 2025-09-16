namespace TodoApp.Dtos
{
    public record HelloWorldResponse(
        string Message,
        DateTime Timestamp,
        string Version,
        string Controller,
        string Environment
    );
}