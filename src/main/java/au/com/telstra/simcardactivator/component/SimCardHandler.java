package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimCardHandler {
    private final String url = "http://localhost:8444/actuate";;
    private final RestTemplate template;

    public SimCardHandler(RestTemplateBuilder builder) {
        this.template = builder.build();
    }

    public ActuationResult actuate(SimCard simCard) {
        return template.postForObject(url, simCard, ActuationResult.class);
    }
}
