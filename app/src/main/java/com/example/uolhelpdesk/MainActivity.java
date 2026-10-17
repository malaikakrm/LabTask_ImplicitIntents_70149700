package com.example.uolhelpdesk;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME =
            "com.example.uolhelpdesk.EXTRA_NAME";

    EditText etName;
    Button btnWebsite, btnCall, btnMap, btnRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect Java variables to XML views
        etName = findViewById(R.id.etName);
        btnWebsite = findViewById(R.id.btnWebsite);
        btnCall = findViewById(R.id.btnCall);
        btnMap = findViewById(R.id.btnMap);
        btnRequest = findViewById(R.id.btnRequest);

        // Button 1: Open the university website
        btnWebsite.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.uol.edu.pk")
            );

            openIntent(this, intent);
        });

        // Button 2: Open the phone dialer
        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:04212345678")
            );

            openIntent(this, intent);
        });

        // Button 3: Open the university location in Maps
        btnMap.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=University+of+Lahore")
            );

            openIntent(this, intent);
        });

        // Button 4: Validate the name and open RequestActivity
        btnRequest.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("Please enter your name");
                etName.requestFocus();
                return;
            }

            Intent intent = new Intent(
                    MainActivity.this,
                    RequestActivity.class
            );

            intent.putExtra(EXTRA_NAME, name);
            startActivity(intent);
        });
    }

    // Reusable helper method for implicit Intents
    public static void openIntent(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(
                    context,
                    "No app found for this action",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
