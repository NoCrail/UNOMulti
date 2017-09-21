package net.home.nocrail.unomulti;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Toast;

import java.util.ArrayList;

public class Serv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv);
        ArrayList cards = new ArrayList();
        for (int i = 1; i<=4; i++){
            cards.add(i*10);                    //ВВодим нули
        };

        for (int i = 1; i<=4; i++){
            for(int k = 1; k<=9; k++){
                cards.add(i*10+k);              //ВВодим игровые карты
                cards.add(i*10+k);
            }
        };
        for (int i = 1; i<=4; i++){
            for (int k = 10; k<=12; k++){
                cards.add(i*100+k);
                cards.add(i*100+k);                 //ВВодим необычные карты
            }
        };
        for (int i =1; i<=2; i++){
            cards.add(50+i);
            cards.add(50+i);
            cards.add(50+i);                        //ВВодим дикие
            cards.add(50+i);
        };
        Toast.makeText(this, String.valueOf(cards.size()), Toast.LENGTH_SHORT).show();
    }

}
