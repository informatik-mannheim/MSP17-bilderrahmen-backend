package de.hsmannheim.bilderrahmen.token;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * This class generates unique tokens using a google email.
 */
@Service
public class TokenGenerator {

    public Token generateTokenForGoogleAccount(String googleAccount) {
        String randomString = UUID.randomUUID() + googleAccount;
        return new Token(DigestUtils.sha256Hex(randomString));
    }

}
