package com.example.asus.bookstore_team8;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        String review = getIntent().getStringExtra("review");
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(review);

    }
}
