using System;

namespace TodoApp.Legacy;

/// <summary>
/// Provides legacy notification functionality for sending messages via SMS, Email, and Push.
/// This class is a straightforward, backwards-compatible implementation intended for simple scenarios.
/// </summary>
public class LegacyNotificationService
{
	/// <summary>
	/// Sends a notification using the specified channel (SMS, EMAIL, or PUSH).
	/// </summary>
	/// <param name="channel">Notification channel name: "SMS", "EMAIL", or "PUSH" (case-insensitive).</param>
	/// <param name="recipient">Recipient identifier: phone number for SMS, email address for Email, or device id for Push.</param>
	/// <param name="message">Message content to send; null values are treated as empty string.</param>
	/// <exception cref="ArgumentException">Thrown when <paramref name="channel"/> or <paramref name="recipient"/> is null, empty, or whitespace, or when an unknown channel is provided.</exception>
	// Simple, junior-style method that sends a notification based on a channel string.
	// Channel values expected: "SMS", "EMAIL", "PUSH" (case-insensitive).
	public void SendNotification(string channel, string recipient, string message)
	{
		if (string.IsNullOrWhiteSpace(channel))
		{
			throw new ArgumentException("channel is required", nameof(channel));
		}

		if (string.IsNullOrWhiteSpace(recipient))
		{
			throw new ArgumentException("recipient is required", nameof(recipient));
		}

		if (message is null)
		{
			message = string.Empty;
		}

		var ch = channel.Trim().ToUpperInvariant();

		// Junior-style branching: no patterns, just straightforward if/else.
		if (ch == "SMS")
		{
			var ok = SendSms(recipient, message);
			if (!ok)
			{
				Console.WriteLine("SMS sending failed for recipient: " + recipient);
			}
		}
		else if (ch == "EMAIL")
		{
			var ok = SendEmail(recipient, message);
			if (!ok)
			{
				Console.WriteLine("Email sending failed for recipient: " + recipient);
			}
		}
		else if (ch == "PUSH")
		{
			var ok = SendPush(recipient, message);
			if (!ok)
			{
				Console.WriteLine("Push sending failed for recipient: " + recipient);
			}
		}
		else
		{
			// Junior developer style: throw for unknown value.
			throw new ArgumentException("Unknown notification channel: " + channel, nameof(channel));
		}
	}

	// Simulate SMS send
	private bool SendSms(string phoneNumber, string text)
	{
		// Very simple simulation - in real world you'd call an SMS provider API.
		try
		{
			Console.WriteLine($"[SMS] To: {phoneNumber} Message: {text}");
			return true;
		}
		catch
		{
			return false;
		}
	}

	// Simulate Email send
	private bool SendEmail(string email, string body)
	{
		try
		{
			Console.WriteLine($"[EMAIL] To: {email} Body: {body}");
			return true;
		}
		catch
		{
			return false;
		}
	}

	// Simulate Push notification send
	private bool SendPush(string deviceId, string payload)
	{
		try
		{
			Console.WriteLine($"[PUSH] To device: {deviceId} Payload: {payload}");
			return true;
		}
		catch
		{
			return false;
		}
	}
}
