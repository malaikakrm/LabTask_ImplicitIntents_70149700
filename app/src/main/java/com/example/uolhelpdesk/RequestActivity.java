package com.example.uolhelpdesk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class RequestActivity extends AppCompatActivity {

    TextView tvGreeting;
    EditText etPhone, etLink;
    Button btnCallNumber, btnOpenLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        // Connect Java variables to XML views
        tvGreeting = findViewById(R.id.tvGreeting);
        etPhone = findViewById(R.id.etPhone);
        etLink = findViewById(R.id.etLink);
        btnCallNumber = findViewById(R.id.btnCallNumber);
        btnOpenLink = findViewById(R.id.btnOpenLink);

        // Receive the name from MainActivity
        String name = getIntent().getStringExtra(
                MainActivity.EXTRA_NAME
        );

        if (name == null || name.trim().isEmpty()) {
            name = "Student";
        }

        tvGreeting.setText(
                "Hello, " + name + "! How can we help you today?"
        );

        // Validate the phone number and open the dialer
        btnCallNumber.setOnClickListener(v -> {
            String number = etPhone.getText().toString().trim();

            if (!number.matches("^03\\d{9}$")) {
                etPhone.setError(
                        "Enter 11 digits starting with 03"
                );
                etPhone.requestFocus();
                return;
            }

            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:" + number)
            );

            MainActivity.openIntent(this, intent);
        });

        // Validate the link and open the browser
        btnOpenLink.setOnClickListener(v -> {
            String link = etLink.getText().toString().trim();

            if (link.isEmpty()) {
                etLink.setError("Please enter a link");
                etLink.requestFocus();
                return;
            }

            String lowerLink = link.toLowerCase(Locale.ROOT);

            if (!lowerLink.startsWith("http://")
                    && !lowerLink.startsWith("https://")) {
                link = "https://" + link;
            }

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(link)
            );

            MainActivity.openIntent(this, intent);
        });
    }
}
