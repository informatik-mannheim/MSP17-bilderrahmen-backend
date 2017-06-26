package de.hsmannheim.bilderrahmen.filesync;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Websocket endpoint for synchronizing uploads and deletions from Google Drive.
 * Message path contains a placeholder for the family token.
 */
@Controller
public class FileSyncController {

    @MessageMapping("/filesync/{token}")
    @SendTo("/queue/filesync/{token}")
    public FileChanges send(FileChanges fileChanges) throws Exception {
        return fileChanges;
    }

}
