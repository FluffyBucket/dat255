package se.chalmers.cid.constants;



import android.support.v4.util.ArrayMap;

import java.util.ArrayList;


import se.chalmers.cid.R;

import se.chalmers.cid.models.Language;

/**
 * Created by Fredrik on 2016-10-04.
 */
public class languagesData {

    private static final ArrayMap<Integer,Language> data = generate();

    private static ArrayMap<Integer,Language> generate(){

        ArrayMap<Integer,Language> list = new ArrayMap<>();
        int i = 0;


        list.put(i++,new Language("Swedish",i,R.drawable.ic_language_swedish));
        list.put(i++,new Language("English",i,R.drawable.ic_language_english));
        list.put(i++,new Language("Afghanistan",i,R.drawable.ic_language_afghanistan));
        list.put(i++,new Language("Iran",i,R.drawable.ic_language_iran));
        list.put(i++,new Language("Spanish",i,R.drawable.ic_language_spanish));



        return list;
    }

    public static ArrayMap<Integer,Language> getData(){
        return data;
    }

}
