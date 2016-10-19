package se.chalmers.cid.models;

public class Attribute {

	private String name;
	private int image;

	Attribute(String name, int image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public int getImage() {
		return image;
	}

}
