package com.example.student1.apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Permissions extends AppCompatActivity {

    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.apps_list);

        // Контейнер для разрешений
        List<String> perms = new ArrayList<>();

        // Получим Intent с которым бала запущена Activity
        Intent intent = getIntent();
        // Проверим, есть ли в Intent что-либо с нужным ключом
        if(intent.hasExtra(MainActivity.PERMISSIONS))
        {

            // Получим массив строк (разрешений) из Intent

            String[] permissions = intent.getStringArrayExtra(MainActivity.PERMISSIONS);

            // Если не нулевой, добавим все что в нем есть
            // в контейнер разрешений

            if(permissions!=null){
                for(String s:permissions){
                    perms.add(s);
                }
            }




        }

        // Стандартный адаптер
        //
        //
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,perms);



        list.setAdapter(adapter);
    }
}
