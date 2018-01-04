package com.example.asus.bookstore_team8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button buttonSearch = (Button) findViewById(R.id.buttonSearch);
        final EditText editText = (EditText) findViewById(R.id.textSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SearchActivity.this, ListActivity.class);
                i.putExtra("books", editText.getText());
                startActivity(i);
            }
        });

    }
}
