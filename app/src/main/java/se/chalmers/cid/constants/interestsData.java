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

        for (int i = 0;i<10;i++){
            Interest tmp = new Interest("Interest "+i,i, R.drawable.ic_phone);
            list.put(i,tmp);
        }

        return list;
    }

    public static ArrayMap<Integer,Interest> getData(){
        return data;
    }
}
