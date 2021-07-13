package CI;

import java.io.IOException;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;

import Framework.Listener;

public class SlackPublisher {

	public static void main(String[] args) throws IOException {

		Slack slack = Slack.getInstance();

		System.out.println(Listener.failureResults);
		// String webhookUrl = System.getenv(
		// "https://hooks.slack.com/services/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");
		// // https://hooks.slack.com/services/T1234567/AAAAAAAA/ZZZZZZ
		Payload payload = Payload.builder().text("Hello,<@U01KBMACL2F>!").build();

		WebhookResponse response = slack.send(
				"https://hooks.slack.com/services/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",
				payload);
		System.out.println(response);

	}

}
