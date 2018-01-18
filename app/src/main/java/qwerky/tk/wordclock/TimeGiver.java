package qwerky.tk.wordclock;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Calendar;
/**
 * Created by Family on 16/01/2018.
 */
@TargetApi(Build.VERSION_CODES.O)

public class TimeGiver {


    String[] MINUTEWORDS={"bang on","just gone","about","nearly",""};
    String[] MINUTETIMEWORDS={"","five past","ten past","a quarter past","twenty past",
            "twenty-five past",
            "half","twenty-five to",
            "twenty to","a quarter to","ten to","five to",""};
    String[] HOURWORDS={"midnight","one","two","three","four","five","six","seven",
            "eight","nine","ten","eleven","noon",
            "one","two","three","four","five","six",
            "seven","eight","nine","ten","eleven","midnight"};

    public String wordTimeMaker(){
        Calendar c = Calendar.getInstance();
        System.out.println(c.toString());

        String wordTime="";
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int mins=c.get(Calendar.MINUTE);

        int handPos=Math.round(mins/5);
        //var handPos=11;
        int handMod=mins%5;

        if  (handPos>6){hour++;}
        int i=1;
        wordTime=MINUTEWORDS[handMod]+" "+MINUTETIMEWORDS[handPos]+" "+
                HOURWORDS[hour]+c.get(Calendar.SECOND) ;

        return wordTime;

    }
}
