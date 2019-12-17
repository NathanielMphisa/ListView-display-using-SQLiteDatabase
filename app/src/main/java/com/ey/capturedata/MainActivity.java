package com.ey.capturedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText expenseName, expenseCost;
    private ListView listView;
    private Button btnAdd;
    private String[][] itemsArray;
    private ArrayAdapter arrayAdapter;
    private DBManager dbManager;
    private SimpleCursorAdapter simpleCursorAdapter;

    //Setting variables

    public String[] from = new String[] {DatabaseHelper.ID, DatabaseHelper.ITEM_NAME, DatabaseHelper.ITEM_CURRENCY,DatabaseHelper.ITEM_COST};
    public int[] to = new int[] {R.id.id,R.id.name,R.id.currency,R.id.cost};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbManager = new DBManager(this);
        dbManager.open();


        Cursor cursor = dbManager.fetch();
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.whenEmpty));


        simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.view_record,cursor,from,to,0);
        simpleCursorAdapter.notifyDataSetChanged();
        listView.setAdapter(simpleCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //R.id.id,R.id.name,R.id.currency,R.id.cost

                TextView idItem = view.findViewById(R.id.id);
                TextView itemName = view.findViewById(R.id.name);
                TextView itemCurrency = view.findViewById(R.id.currency);
                TextView itemCost = view.findViewById(R.id.cost);

                String identity = idItem.getText().toString();
                String productName = itemName.getText().toString();
                String productCurrency = itemCurrency.getText().toString();
                String productCost = itemCost.getText().toString();

                Intent intent = new Intent(getApplicationContext(), Modify_record.class);
                intent.putExtra("identity",identity);
                intent.putExtra("productName",productName);
                intent.putExtra("productCost",productCost);
                intent.putExtra("productCurrency",productCurrency);

                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record){

            Intent intent = new Intent(getApplicationContext(),AddItem.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
