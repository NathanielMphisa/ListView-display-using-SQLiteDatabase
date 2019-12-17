package com.ey.capturedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Modify_record extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private DBManager dbManager;

    TextView itemName, itemCost;
    RadioGroup currency;
    Button delete, update;
    String usd = "USD", rtgs = "RTGS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_record);

        setTitle("Modify Record");

        // Reading Intents
        Intent receivables = getIntent();
        String productName = receivables.getStringExtra("productName");
        String productCost = receivables.getStringExtra("productCost");
        String productCurrency = receivables.getStringExtra("productCurrency");
        String productID = receivables.getStringExtra("identity");

        // Initialising Views

        itemName = findViewById(R.id.productName);
        itemCost = findViewById(R.id.productCost);
        currency = findViewById(R.id.radio_currency);
        delete = findViewById(R.id.btn_delete);
        update = findViewById(R.id.btn_update);


       itemName.setText(productName);
       itemCost.setText(productCost);

     //  boolean result = usd.equals(productCurrency);

    //    Log.i("modify","The following are the values " + productName + " " + productCost + " " + productCurrency + " " + productID + " " + String.valueOf(result) );



        if(usd.equals(productCurrency)){

                currency.check(currency.getChildAt(1).getId());

          }
          else {
                currency.check(currency.getChildAt(0).getId());
              }



    }


}
