package com.example.shopping;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        final DataBase dataBase = new DataBase(context);

        dataBase.insertDataIntoElec("Кумыс", "2000");
        dataBase.insertDataIntoElec("Молоко", "12000");
        dataBase.insertDataIntoElec("Шубат", "25000");
        dataBase.insertDataIntoElec("Кефир", "56000");
        dataBase.insertDataIntoElec("Айран", "14000");
        dataBase.insertDataIntoElec("Саумал", "1000");


        dataBase.insertDataIntoFurn("Щука", "450");
        dataBase.insertDataIntoFurn("Селедь", "2000");
        dataBase.insertDataIntoFurn("Морские гребешки", "5000");
        dataBase.insertDataIntoFurn("Медуза", "25600");
        dataBase.insertDataIntoFurn("Креветки", "1250");
        dataBase.insertDataIntoFurn("Семга", "1500");
        dataBase.insertDataIntoFurn("Форель", "1600");
        dataBase.insertDataIntoFurn("Окунь", "25000");

        dataBase.insertDataIntoStat("Конина", "20000");
        dataBase.insertDataIntoStat("Баранина", "10000");
        dataBase.insertDataIntoStat("Свинина", "15000");
        dataBase.insertDataIntoStat("Говядина", "20000");
        dataBase.insertDataIntoStat("Кролик", "5000");


        dataBase.insertDataIntoLogin("ankesh", "12345678");
        dataBase.insertDataIntoLogin("chillmarrore", "sabmoahmayahai");

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase data = dataBase.getReadableDatabase();
                data.beginTransaction();
                String query = "SELECT username,password FROM " + DataBase.login;
                Cursor cursor = data.rawQuery(query, null);
                EditText user = findViewById(R.id.username);
                String username = String.valueOf(user.getText());
                EditText pass = findViewById(R.id.password);
                String password = String.valueOf(pass.getText());
                Toast toast;
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        if (username.equals(cursor.getString(cursor.getColumnIndex("username"))) && password.equals(cursor.getString(cursor.getColumnIndex("password")))) {
                            toast = Toast.makeText(getApplicationContext(),
                                    "Successfully logged in",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            Intent soppingPage = new Intent(MainActivity.this, ShoppingMenu.class);
                            startActivity(soppingPage);
                            break;
                        } else {
                            toast = Toast.makeText(getApplicationContext(),
                                    "Failed Retry",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                    }
                    data.setTransactionSuccessful();
                    data.endTransaction();;
                    data.close();
                }
            }
        });
    }
}
