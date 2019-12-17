package com.ey.capturedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddItem extends AppCompatActivity implements View.OnClickListener {

    private Button addBtn;
    private EditText commodity,commodityCost;
    private Spinner currencySelector;
    private String[] currency = new String[] {"USD","RTGS"};
    private ArrayAdapter arrayAdapter;

    public DBManager dbManager = new DBManager(this);
    public DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        db = new DatabaseHelper(this);

        setTitle("Add Expenses");

        addBtn = findViewById(R.id.addItemBtn);
        commodity = findViewById(R.id.itemName);
        commodityCost = findViewById(R.id.itemCost);
        currencySelector = findViewById(R.id.currencySpinner);

        // defining my arrayAdapter
        arrayAdapter = new ArrayAdapter<>(getApplication(),R.layout.spinner_item_new,currency);
        currencySelector.setAdapter(arrayAdapter);

       addBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String itemName = commodity.getText().toString();
        int cost = Integer.parseInt(commodityCost.getText().toString());
        String currency = currencySelector.getSelectedItem().toString();

        Log.i("Values",itemName + ", " + cost + ", " + currency);

        DBManager dbManager = new DBManager(this);
        boolean result = dbManager.insert(itemName,cost,currency);


        Toast.makeText(getApplicationContext(),String.valueOf(result),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

}
