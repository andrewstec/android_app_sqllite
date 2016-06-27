package com.example.a00584083.db_exercise_03_11_2016;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int ENTER_DATA_REQUEST_CODE = 1;
    private ContactCursorAdapter customAdapter;
    private ContactDatabaseHelper databaseHelper;
    private ListView listView;
    private static final String TAG = MainActivity.class.getSimpleName();

    public void onClickDelete(View view) {
        int result;
        result = databaseHelper.deleteRow(view.getTag().toString());
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        if (result == 1) {
            Toast.makeText(this, "Successfully deleted item", Toast.LENGTH_SHORT);
        }
        else {
            Toast.makeText(this, "Could not delete item", Toast.LENGTH_SHORT);
        }
    }

    public void onClickUpdate(View view) {
        Intent intent = new Intent(getBaseContext(), UpdateActivity.class);
        intent.putExtra("id", view.getTag().toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new ContactDatabaseHelper(this);

        listView = (ListView) findViewById(R.id.list_data);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "clicked on item: " + position);
            }
        });

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                customAdapter = new ContactCursorAdapter(MainActivity.this, databaseHelper.getAllData());
                listView.setAdapter(customAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_add_contact) {
            startActivityForResult(new Intent(this, AddActivity.class), ENTER_DATA_REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ENTER_DATA_REQUEST_CODE && resultCode == RESULT_OK) {

            databaseHelper.insertData(data.getExtras().getString("tag_name"), data.getExtras().getString("tag_phone_number"),
                    data.getExtras().getString("tag_email"), data.getExtras().getInt("tag_street_address"),
                    data.getExtras().getString("tag_city"), data.getExtras().getString("tag_province"),
                    data.getExtras().getString("tag_postal_code"));


            //  public void insertData (String name, String phoneNumber, String email, int streetAddress,
            // String city, String province, String postalCode) {


            System.out.println(data.getExtras().getString("tag_name")  +  data.getExtras().getString("tag_email") +
                    data.getExtras().getString("tag_street_address") +  data.getExtras().getString("tag_city") +
                    data.getExtras().getString("tag_province") + data.getExtras().getString("tag_postal_code")  );
            customAdapter = new ContactCursorAdapter(this, null);
            customAdapter.changeCursor(databaseHelper.getAllData());
            startActivity(new Intent(getBaseContext(), MainActivity.class));
        }
        System.out.println("testtesttest");
    }
}
