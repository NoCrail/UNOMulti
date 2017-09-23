package net.home.nocrail.unomulti;

import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nocrail on 20.09.17.
 */

public class Cards {
    Integer cardname;
    static Integer color;
    static Integer number;

    public static String[] cn(int cardname){
        if (cardname/100<1){
            color= cardname/10;
            number = cardname%10;
        }
        if (cardname/100>=1){
            color = cardname / 100;
            number = cardname % 100;
        }
        String colors = "";

        switch (color){
            case 1:colors = "RED"; break;
            case 2:colors = "YELLOW"; break;
            case 3:colors = "GREEN"; break;
            case 4:colors = "BLUE"; break;
            case 5:colors = "WILD"; break;

        }


        String[] myArray = {colors, number.toString()};
        return myArray;
    }

}
