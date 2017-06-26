package de.hsmannheim.bilderrahmen.swipe;

/**
 * DTO for synchronizing swipes. The object contains the id of the picture.
 */
public class Swipe {

	private String id;

	public Swipe buildSwipe(String swipe){
		this.id = swipe;
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
