package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.edittext_uri);
        mLocationEditText = findViewById(R.id.edittext_loc);
        mShareEditText = findViewById(R.id.edittext_share);
    }

    public void openWebsite(View view) {
        //get the text
        String url = mWebsiteEditText.getText().toString();

        //parse uri and create intent
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);


        //find and activity to handle the intent and start activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }



    public void openLocation(View view) {
        //always get the content
        String loc = mLocationEditText.getText().toString();

        //This is a really specific element, at the end, another uri
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        //same action
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        //same evaluation
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {
        String share = mShareEditText.getText().toString();
        String mimeType = "text/plain";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(mimeType);
        intent.putExtra(Intent.EXTRA_TEXT, share);

        Intent chooserIntent = Intent.createChooser(intent, "Share this text with:");

        if (chooserIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooserIntent);
        } else {
            Log.d("Social sharing error", "Cannot share in social");
        }
    }

}