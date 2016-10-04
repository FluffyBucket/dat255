package se.chalmers.cid.models;

/**
 * Created by MicroBucket on 2016-10-04.
 */
public class Interest {

    private String name;
    private int id;
    private int image;

    public Interest(String name, int id, int image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
