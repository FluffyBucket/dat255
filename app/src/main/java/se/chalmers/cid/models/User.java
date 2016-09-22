package se.chalmers.cid.models;

/**
 * Created by valentin & mårlind on 22/09/2016.
 */

public class User {
    private String name;
    private String age;
    private String prefContactWay;
    private String biography;

    public  User(){
        this.name = "";
        this.age = "";
        this.prefContactWay = "";
        this.biography = "";
    }

    public User(String name, String age, String prefContactWay, String biography) {
        this.name = name;
        this.age = age;
        this.prefContactWay = prefContactWay;
        this.biography = biography;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPrefContactWay(String prefContactWay) {
        this.prefContactWay = prefContactWay;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPrefContactWay() {
        return prefContactWay;
    }

    public String getBiography() {
        return biography;
    }
}
