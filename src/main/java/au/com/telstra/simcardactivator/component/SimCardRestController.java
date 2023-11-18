package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.SimCard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class SimCardRestController {
    private final SimCardHandler simCardHandler;
    private final DatabaseConduit databaseConduit;

    public SimCardRestController(SimCardHandler simCardHandler, DatabaseConduit databaseConduit) {
        this.simCardHandler = simCardHandler;
        this.databaseConduit = databaseConduit;
    }

    @PostMapping(value = "/activate")
    public void handleActivationRequest(@RequestBody SimCard simCard) {
        var actuationResult = simCardHandler.actuate(simCard);
        databaseConduit.save(simCard, actuationResult);
    }

    @GetMapping(value = "/query")
    public SimCard handleActivationRequest(@RequestParam Long simCardId) {
        return databaseConduit.querySimCard(simCardId);
    }
}
