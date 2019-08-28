package com.hadar.assignment1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    private ImageView fruitImageView;
    private TextView fruitNameTextView;
    private TextView fruitCaloriesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        fruitImageView = findViewById(R.id.image_view);
        fruitNameTextView = findViewById(R.id.name_text_view);
        fruitCaloriesTextView = findViewById(R.id.calories_text_view);

        initViews();
    }

    private void initViews() {
        fruitImageView.setImageResource(getImgId());
        fruitNameTextView.setText(getName() != null ? getName() : "");
        fruitCaloriesTextView.setText(String.valueOf(getCalories()));
    }

    @Nullable
    private String getName() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getStringExtra(MainActivity.keyForName);
        }
        return null;
    }

    private int getImgId() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getIntExtra(MainActivity.keyForImg, 0);
        }
        return 0;
    }

    private int getCalories() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getIntExtra(MainActivity.keyForCalories, 0);
        }
        return 0;
    }
}
