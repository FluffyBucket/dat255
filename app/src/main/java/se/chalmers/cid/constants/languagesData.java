package se.chalmers.cid.constants;



import java.util.ArrayList;


import se.chalmers.cid.R;
import se.chalmers.cid.models.Language;

/**
 * Created by MicroBucket on 2016-10-04.
 */
public class languagesData {

    public static final ArrayList<Language> data = generate();

    private static ArrayList<Language> generate(){

        ArrayList<Language> list = new ArrayList<Language>();

        for(int i = 0;i<10;i++)
        {
            Language tmp = new Language("Lang " +1,i, R.drawable.ic_apache);
            list.add(tmp);
        }


        return list;
    }

}
