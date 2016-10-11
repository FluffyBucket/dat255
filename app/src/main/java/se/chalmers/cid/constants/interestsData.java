package se.chalmers.cid.constants;

import android.support.v4.util.ArrayMap;

import se.chalmers.cid.R;
import se.chalmers.cid.models.Interest;

/**
 * Created by Fredrik on 2016-10-04.
 */
public class interestsData {

    private static final ArrayMap<Integer,Interest> data = generate();

    private static ArrayMap<Integer,Interest> generate(){
        ArrayMap<Integer,Interest> list = new ArrayMap<>();
        int i = 0;


        list.put(i++,new Interest("Basketball",i,R.drawable.ic_interests_basketball));
        list.put(i++,new Interest("Soccer",i,R.drawable.ic_interests_fotboll));
        list.put(i++,new Interest("Music",i,R.drawable.ic_interests_music));
        list.put(i++,new Interest("Movie",i,R.drawable.ic_interests_movie));
        list.put(i++,new Interest("Videogames",i,R.drawable.ic_interests_videogames));
        list.put(i++,new Interest("Reading",i,R.drawable.ic_interests_reading));


        return list;
    }

    public static ArrayMap<Integer,Interest> getData(){
        return data;
    }
}
