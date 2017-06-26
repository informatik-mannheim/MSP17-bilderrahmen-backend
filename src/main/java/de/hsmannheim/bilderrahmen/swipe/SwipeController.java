package de.hsmannheim.bilderrahmen.swipe;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


/**
 * Websocket endpoint for synchronizing swipes.
 * Message path contains a placeholder for the family token.
 */
@Controller
public class SwipeController {

    @MessageMapping("/swipe/{token}")
    @SendTo("/queue/swipe/{token}")
    public Swipe send(@DestinationVariable String token, Swipe swipe) throws Exception {
        return swipe;
    }
}
