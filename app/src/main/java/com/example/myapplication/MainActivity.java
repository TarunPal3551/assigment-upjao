package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String name;
    int sNo = 0;
    Date time;
    String location;
    Bitmap imageFile;
    SharedPreferences.Editor editor;
    TextView textViewName, textViewTime, textViewLocation, textViewSno;
    ImageView imageView;
    Button showDataButton, viewDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        imageView = (ImageView) findViewById(R.id.imageView);
        textViewName = findViewById(R.id.textViewName);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewTime = findViewById(R.id.textViewTime);
        textViewSno = findViewById(R.id.textViewSno);
        showDataButton = findViewById(R.id.showDataButton);
        viewDataButton = findViewById(R.id.viewData);
        showDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "Tarun";
                time = Calendar.getInstance().getTime();
                location = "Gurgaon";
                sNo++;
                saveData();

            }
        });
        viewDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewData();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    void saveData() {
        editor.putString("name", name);
        editor.putInt("sno", sNo);
        editor.putString("time", time.toString());
        editor.putString("address", location);
        editor.apply();
//        editor.putString("imagePath", imageFile.toString());
    }

    void viewData() {
        textViewTime.setText("Time - " + sharedPreferences.getString("time", ""));
        textViewName.setText("Name - " + sharedPreferences.getString("name", ""));
        textViewSno.setText("Sno - " + String.valueOf(sharedPreferences.getInt("sno", 0)));
        textViewLocation.setText("Address - " + sharedPreferences.getString("address", ""));



    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}