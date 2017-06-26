package de.hsmannheim.bilderrahmen.masterselection;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Websocket endpoint for synchronizing master requests.
 * Message path contains a placeholder for the family token.
 */
@Controller
public class MasterSelectionController {

	@MessageMapping("/master-selection/{token}")
	@SendTo("/queue/master-selection/{token}")
	public MasterSelection send(MasterSelection masterSelection) throws Exception {
		return masterSelection;
	}
}
