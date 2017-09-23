package net.home.nocrail.unomulti;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Serv extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference fire = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user ;
    //Integer CPlayer = 1;
    Integer Card =90;
    Integer numb = 0;
    Integer ccard = 0;

    ArrayList cards = new ArrayList();
    Random random = new Random();
    List<Integer> hand = new ArrayList<>();






    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv);

        Button right = (Button)findViewById(R.id.RightBtn);
        Button left = (Button)findViewById(R.id.LeftBtn);
        final TextView card = (TextView)findViewById(R.id.card);
        final TextView PlayerGo = (TextView)findViewById(R.id.PlayerGo);
        Button Send = (Button)findViewById(R.id.SendBtn);



        mAuth = FirebaseAuth.getInstance();


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

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fire.child("CardNew").setValue(0);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numb>=hand.size()){
                    numb=0;
                    Toast.makeText(Serv.this, "заново", Toast.LENGTH_SHORT).show();
                }
                ccard = hand.get(numb);
                String [] ga = Cards.cn(hand.get(numb));
                card.setText(ga[0]+" "+ga[1]);
                numb++;


            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fire.child("Card").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Integer karta = Integer.parseInt(dataSnapshot.getValue().toString());
                        String [] ga = Cards.cn(karta);
                        String [] ga2 = Cards.cn(ccard);
                        //if ((ga[1]==ga2[1])||(ga[0]==ga2[0])||(ga2[0]=="WILD")){
                            fire.child("Card").setValue(ccard);
                            hand.remove(ccard);
                            //numb--;
                        String [] gad = Cards.cn(hand.get(numb));
                        card.setText(gad[0]+" "+gad[1]);
                        numb = hand.size();
                        if (numb<=0){ Toast.makeText(Serv.this, "UNO", Toast.LENGTH_SHORT).show();
                        /*for (int i = 1; i<=7; i++) {
                            Integer u = random.nextInt(cards.size());
                            hand.add((Integer) cards.get(u));
                            cards.remove(cards.get(u));
                            card.setText(hand.get(i-1).toString()); //TODO Пофиксить краш последнюю карту
                        }*/}
                        //}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Serv.this, "Шеф, все пропало2", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = mAuth.getCurrentUser();
                            Toast.makeText(Serv.this, "Connected", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Serv.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




       fire.child("CardNew").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String h = dataSnapshot.toString();
                //CardSlot = h.split("=")[h.split("=").length-2].split(" ")[1];
                Card = Integer.parseInt(h.split("value = ")[1].split(" ")[0]);
                //Toast.makeText(Serv.this, Card.toString(), Toast.LENGTH_SHORT).show();
                int i =random.nextInt(cards.size());
                if (Card==0){
                    fire.child("CardNew").setValue(cards.get(i));
                    cards.remove(i);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Serv.this, "Шеф, все пропало1", Toast.LENGTH_SHORT).show();
            }
        });

        for (int i = 1; i<=7; i++) {
            Integer u = random.nextInt(cards.size());
            hand.add((Integer) cards.get(u));
            cards.remove(cards.get(u));
            card.setText(hand.get(i-1).toString());
        }
        Integer u = random.nextInt(cards.size());
        fire.child("Card").setValue(cards.get(u));
        cards.remove(cards.get(u));
        fire.child("ConnectPlayer").setValue(2);


        //Toast.makeText(this, "Конец кода", Toast.LENGTH_SHORT).show();


    }


}

