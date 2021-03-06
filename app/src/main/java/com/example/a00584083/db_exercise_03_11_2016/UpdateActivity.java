package com.example.a00584083.db_exercise_03_11_2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by a00584083 on 3/11/2016.
 */
public class UpdateActivity extends AppCompatActivity {

    EditText editName, editPhoneNumber, editEmail, editStreetAddress, editCity, editProvince, editPostalCode;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Bundle theId = getIntent().getExtras();
        if (theId != null) {
            id = theId.getString("id");
        }

        editName = (EditText) findViewById(R.id.name_value);
        editPhoneNumber = (EditText) findViewById(R.id.phone_number_value);
        editEmail = (EditText) findViewById(R.id.email_value);
        editStreetAddress = (EditText) findViewById(R.id.street_address_value);
        editCity = (EditText) findViewById(R.id.city_value);
        editProvince = (EditText) findViewById(R.id.province_value);
        editPostalCode = (EditText) findViewById(R.id.postal_code_value);
    }

    public void onClickUpdateContact(View view) {
        String name = editName.getText().toString();
        String phoneNumberText = editPhoneNumber.getText().toString();
        String emailText = editEmail.getText().toString();
        String streetAddressText = editStreetAddress.getText().toString();
        String cityText = editCity.getText().toString();
        String provinceText = editProvince.getText().toString();
        String postalCodeText = editPostalCode.getText().toString();

        if (phoneNumberText.length() != 0 && emailText.length() != 0 && streetAddressText.length() != 0
                && cityText.length() != 0 && provinceText.length() != 0 && postalCodeText.length() != 0) {
            ContactDatabaseHelper databaseHelper = new ContactDatabaseHelper(this);
            databaseHelper.updateRow(id, name, phoneNumberText, emailText, Integer.parseInt(streetAddressText), cityText, provinceText, postalCodeText);

            Intent newIntent = new Intent(getBaseContext(), MainActivity.class);;
            startActivity(newIntent);
        }
        else {
            Toast.makeText(this, "Entries cannot be empty", Toast.LENGTH_SHORT);
        }
    }
}
