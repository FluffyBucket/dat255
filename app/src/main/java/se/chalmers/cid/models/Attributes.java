package se.chalmers.cid.models;

import android.util.ArrayMap;

import se.chalmers.cid.R;

public class Attributes {

    public static final ArrayMap<String, Attribute> CONTACT_WAYS = generateContactWays();
    public static final ArrayMap<String, Attribute> INTERESTS = generateInterests();
    public static final ArrayMap<String, Attribute> LANGUAGES = generateLanguages();

    private static ArrayMap<String, Attribute> generateContactWays() {
        return new ArrayMap<>();
    }

    private static ArrayMap<String, Attribute> generateInterests() {
        Attribute[] interests = {
                new Attribute("Basketball", R.drawable.ic_interests_basketball),
                new Attribute("Soccer", R.drawable.ic_interests_fotboll),
                new Attribute("Music", R.drawable.ic_interests_music),
                new Attribute("Movie", R.drawable.ic_interests_movie),
                new Attribute("Videogames", R.drawable.ic_interests_videogames),
                new Attribute("Reading", R.drawable.ic_interests_reading),
                new Attribute("Baseball", R.drawable.ic_interests_baseball),
                new Attribute("Bowling", R.drawable.ic_interests_bowling),
                new Attribute("Chess", R.drawable.ic_interests_chess),
                new Attribute("Cooking", R.drawable.ic_interests_cooking),
                new Attribute("Golf", R.drawable.ic_interests_golf),
                new Attribute("Horse-riding", R.drawable.ic_interests_horse_riding),
                new Attribute("Karate", R.drawable.ic_interests_karate),
                new Attribute("Listening music", R.drawable.ic_interests_listening_music),
                new Attribute("Painting", R.drawable.ic_interests_painting),
                new Attribute("Playing piano", R.drawable.ic_interests_playing_piano),
                new Attribute("Playing guitar", R.drawable.ic_interests_playing_guitar),
                new Attribute("Pool", R.drawable.ic_interests_pool),
                new Attribute("Table tennis", R.drawable.ic_interests_table_tennis),
                new Attribute("Tennis", R.drawable.ic_interests_tennis_ball),
                new Attribute("Working out", R.drawable.ic_interests_working_out),
                new Attribute("Theater", R.drawable.ic_interests_theater)
        };

        ArrayMap<String, Attribute> interestMap = new ArrayMap<>();

        for (Attribute interest : interests) {
            interestMap.put(interest.getName(), interest);
        }

        return interestMap;
    }

    private static ArrayMap<String, Attribute> generateLanguages() {
        Attribute[] languages = {
                new Attribute("Swedish", R.drawable.ic_language_swedish),
                new Attribute("English", R.drawable.ic_language_english),
                new Attribute("Afghanistan", R.drawable.ic_language_afghanistan),
                new Attribute("Iran", R.drawable.ic_language_iran),
                new Attribute("Spanish", R.drawable.ic_language_spanish),
                new Attribute("Danish", R.drawable.ic_language_denmark),
                new Attribute("Norwegian", R.drawable.ic_language_norway),
                new Attribute("Icelandic", R.drawable.ic_language_iceland),
                new Attribute("Finnish", R.drawable.ic_language_finland),
                new Attribute("German", R.drawable.ic_language_german),
                new Attribute("French", R.drawable.ic_language_france),
                new Attribute("Syria", R.drawable.ic_language_syria),
                new Attribute("Somalia", R.drawable.ic_language_somalia),
                new Attribute("Eritrea", R.drawable.ic_language_eritrea),
                new Attribute("Iraq", R.drawable.ic_language_iraq)
        };

        ArrayMap<String, Attribute> languageMap = new ArrayMap<>();

        for (Attribute language : languages) {
            languageMap.put(language.getName(), language);
        }

        return languageMap;
    }

}
