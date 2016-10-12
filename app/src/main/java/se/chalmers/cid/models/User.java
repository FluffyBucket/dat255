package se.chalmers.cid.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by valentin & mårlind on 22/09/2016.
 */

public class User implements Serializable {


    private String id;
    private String name;
    private String age;
    private String prefContactWay;
    private String biography;
    private int role;
    private List<Integer> interests;
    private List<Integer> languages;



    public  User(){
        this.id = "";
        this.name = "";
        this.age = "";
        this.prefContactWay = "";
        this.biography = "";
        this.role = -1;
    }

    public User(String id, String name, String age, String prefContactWay, String biography, int role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.prefContactWay = prefContactWay;
        this.biography = biography;
        this.role = role;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public List<Integer> getInterests() {
        return interests;
    }

    public void setInterests(List<Integer> interests) {
        this.interests = interests;
    }

    public List<Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Integer> languages) {
        this.languages = languages;
    }
}
