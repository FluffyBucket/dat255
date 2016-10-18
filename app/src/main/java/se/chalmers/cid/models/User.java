package se.chalmers.cid.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

    private String id;
    private boolean mentor;

    private String name;
    private String gender;
    private String age;
    private String biography;

    private Map<String, String> contactWays;
    private Map<String, Boolean> interests;
    private Map<String, Boolean> languages;

    public User() {
        id = null;
        mentor = false;
        name = null;
        gender = null;
        age = null;
        biography = null;
        contactWays = new HashMap<>();
        interests = new HashMap<>();
        languages = new HashMap<>();
    }

    public User(String id,
                boolean mentor,
                String name, String gender,
                String age,
                String biography,
                HashMap<String, String> contactWays,
                HashMap<String, Boolean> interests,
                HashMap<String, Boolean> languages) {
        this.id = id;
        this.mentor = mentor;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.biography = biography;
        this.contactWays = contactWays;
        this.interests = interests;
        this.languages = languages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMentor() {
        return mentor;
    }

    public void setMentor(boolean mentor) {
        this.mentor = mentor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Map<String, String> getContactWays() {
        return contactWays;
    }

    public void setContactWays(Map<String, String> contactWays) {
        this.contactWays = contactWays;
    }

    public Map<String, Boolean> getInterests() {
        return interests;
    }

    public void setInterests(Map<String, Boolean> interests) {
        this.interests = interests;
    }

    public Map<String, Boolean> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, Boolean> languages) {
        this.languages = languages;
    }

}
