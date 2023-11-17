package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.SimCard;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SimCardRestController {
    private final SimCardHandler simCardHandler;

    public SimCardRestController(SimCardHandler simCardHandler) {
        this.simCardHandler = simCardHandler;
    }

    @PostMapping(value = "/activate")
    public void handleActivationRequest(@RequestBody SimCard simCard) {
        var actuationResult = simCardHandler.actuate(simCard);
        System.out.println(actuationResult.getStatus());
    }
}
