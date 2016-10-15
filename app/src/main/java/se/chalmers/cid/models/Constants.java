package se.chalmers.cid.models;

import android.util.ArrayMap;

import se.chalmers.cid.R;

public class Constants {

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
			new Attribute("Reading", R.drawable.ic_interests_reading)
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
			new Attribute("Spanish", R.drawable.ic_language_spanish)
		};

		ArrayMap<String, Attribute> languageMap = new ArrayMap<>();

		for (Attribute language : languages) {
			languageMap.put(language.getName(), language);
		}

		return languageMap;
	}

}
