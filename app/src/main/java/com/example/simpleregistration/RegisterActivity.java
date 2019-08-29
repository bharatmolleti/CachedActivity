package com.example.simpleregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText emergencyContact = (EditText) findViewById(R.id.emergencyContact);
        emergencyContact.setText(getPreference(RegistrationPreferenceNames.REGISTER_EMERGENCY_CONTACT));

        final EditText address = (EditText) findViewById(R.id.postalAddress);
        address.setText(getPreference(RegistrationPreferenceNames.REGISTER_ADDRESS));

        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save the emergency contact and adress details to shared preferences
                String emergencyContactNo = emergencyContact.getText().toString();
                savePreference(RegistrationPreferenceNames.REGISTER_EMERGENCY_CONTACT, emergencyContactNo);

                String addressOfPerson = address.getText().toString();
                savePreference(RegistrationPreferenceNames.REGISTER_ADDRESS, addressOfPerson);

                Toast.makeText(getApplicationContext(), "Your Preferences have been Updated!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void savePreference(String key, String value) {
        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private String getPreference(String key) {
        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getApplicationContext());
        return preferences.getString(key, "");
    }
}
