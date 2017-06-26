package de.hsmannheim.bilderrahmen.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoint for creating tokens.
 */
@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private TokenGenerator tokenGenerator;

	@RequestMapping(method = RequestMethod.POST)
	public Token createToken(@RequestBody String googleAccount) {
		return tokenGenerator.generateTokenForGoogleAccount(googleAccount);
	}

}
