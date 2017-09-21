package net.home.nocrail.unomulti;

/**
 * Created by nocrail on 20.09.17.
 */

public class Cards {
    Integer cardname;
    Integer color;
    Integer number;

    public void cn(int cardname){
        if (cardname/100<1){
            color= cardname/10;
            number = cardname%10;
        }
        if (cardname/100>=1){
            color = cardname / 100;
            number = cardname % 100;
        }
    }

}
