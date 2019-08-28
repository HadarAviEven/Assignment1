package com.hadar.assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String keyForName = "KEY_FOR_NAME";
    public static final String keyForCalories = "KEY_FOR_CALORIES";
    public static final String keyForImg = "KEY_FOR_IMG";

    List<HashMap<String, String>> fullDictionary = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Fruit> myFruitList = new ArrayList<>();
        myFruitList.add(new Fruit("Orange", 47, R.drawable.orange));
        myFruitList.add(new Fruit("Banana", 89, R.drawable.banana));
        myFruitList.add(new Fruit("Apple", 52, R.drawable.apple));
        myFruitList.add(new Fruit("Mango", 60, R.drawable.mango));
        myFruitList.add(new Fruit("Lemon", 29, R.drawable.lemon));
        myFruitList.add(new Fruit("Pear", 57, R.drawable.pear));
        myFruitList.add(new Fruit("Strawberry", 33, R.drawable.strawberry));
        myFruitList.add(new Fruit("Peach", 39, R.drawable.peach));



        for(Fruit oneFruit : myFruitList)
        {
            HashMap<String, String> dic = new HashMap<>();
            dic.put(keyForName, oneFruit.getName());
            dic.put(keyForCalories, String.valueOf(oneFruit.getCalories()));
            dic.put(keyForImg, String.valueOf(oneFruit.getImageID()));
            fullDictionary.add(dic);
        }
        String[] from = {keyForImg, keyForName, keyForCalories};
        int[] to = {R.id.fruit_img, R.id.fruit_name, R.id.fruit_calories};

        ListView lv = findViewById(R.id.fruitList);

        final SimpleAdapter simpleAdapter = new SimpleAdapter(
                getBaseContext(), fullDictionary, R.layout.oneitem_layout, from, to);
        lv.setAdapter(simpleAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemPosition, long l) {
//                Toast.makeText(MainActivity.this, "short click", Toast.LENGTH_SHORT).show();

                HashMap<String, String> hashMap = fullDictionary.get(itemPosition);

                Intent intent = new Intent(getBaseContext(), InformationActivity.class);
                intent.putExtra(keyForName, hashMap.get(keyForName));
                intent.putExtra(keyForCalories, Integer.valueOf(hashMap.get(keyForCalories)));
                intent.putExtra(keyForImg, Integer.valueOf(hashMap.get(keyForImg)));
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int itemPosition, long l) {
//                Toast.makeText(MainActivity.this, "long click", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete '" + myFruitList.get(itemPosition).getName() + "'")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                fullDictionary.remove(itemPosition);
                                simpleAdapter.notifyDataSetChanged();

//                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(getApplicationContext(), "Ok, forget about it", Toast.LENGTH_LONG).show();
                                dialogInterface.dismiss();
                            }
                        }).show();

                return true;
            }
        });
    }
}

