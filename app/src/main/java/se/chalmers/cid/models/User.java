package se.chalmers.cid.models;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {

    private String id;
    private boolean mentor = false;
    private String name;
    private String age;
    private String gender;

    private String biography;

    private String email;
    private String facebook;
    private String phone;

    private Map<String, Boolean> contactWays;
    private Map<String, Boolean> interests;
    private Map<String, Boolean> languages;

    public User() {
    }

    public User(String id, boolean mentor, String name, String age, String gender, String biography, String phone, String email, String facebook) {
        this.id = id;
        this.mentor = mentor;
        this.name = name;
        this.age = age;
        this.gender = gender;

        this.biography = biography;

        this.phone = phone;
        this.email = email;
        this.facebook = facebook;
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

    public Map<String, Boolean> getContactWays() {
        return contactWays;
    }

    public void setContactWays(Map<String, Boolean> contactWays) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

}
