package se.chalmers.cid.constants;

import java.util.ArrayList;

import se.chalmers.cid.R;
import se.chalmers.cid.models.Interest;

/**
 * Created by Fredrik on 2016-10-04.
 */
public class interestsData {

    private static final ArrayList<Interest> data = generate();

    private static ArrayList<Interest> generate(){
        ArrayList<Interest> list = new ArrayList<Interest>();

        for (int i = 0;i<10;i++){
            Interest tmp = new Interest("Interest "+i,i, R.drawable.ic_phone);
            list.add(tmp);
        }

        return list;
    }

    public static ArrayList<Interest> getData(){
        return data;
    }
}
