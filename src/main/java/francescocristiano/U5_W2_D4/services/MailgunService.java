package francescocristiano.U5_W2_D4.services;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailgunService {

    @Value("${mailgun.api.key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;

    @Value("${mailgun.from.email}")
    private String fromEmail;

    @Value("${mailgun.from.name}")
    private String fromName;

    public JsonNode sendSimpleMessage(String to, String subject, String text) throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domain + "/messages")
                .basicAuth("api", apiKey)
                .queryString("from", fromName + " <" + fromEmail + ">")
                .queryString("to", to)
                .queryString("subject", subject)
                .queryString("text", text)
                .asJson();
        return request.getBody();
    }
}
