package de.hsmannheim.bilderrahmen.token;

/**
 * DTO for token. The object contains the generated id.
 */
public class Token {

	private String id;

	public Token(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
