package deniscbrex.dev.randomtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Random randomGenerator = new Random();

       // randomGenerator.nextInt(10);

        //double myRandom = randomGenerator.nextGaussian();


        ArrayList<Integer> myList;

        myList = new ArrayList<Integer>();

        myList.add(Integer.parseInt("6"));
        myList.add(Integer.parseInt("10"));
        myList.add(1, Integer.parseInt("23"));

        ArrayList<String> names = new ArrayList<String>();

        names.add("holi");
        names.add("Denis");
        names.add(1,"Jijiji");

         names.get(2);

        //pregunta si esta vacia
        if(names.isEmpty()){

        }else {

        }

        //Cuantos objetos tiene
        int numItems = names.size();

        //Saber donde meti un objeto

        int position = names.indexOf("Denis");

        for(String s : names){
            Log.i("Personaje", s);
        }
    }
}
