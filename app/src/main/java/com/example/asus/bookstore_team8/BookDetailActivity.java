package com.example.asus.bookstore_team8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.net.UnknownHostException;
import java.util.List;

public class BookDetailActivity extends AppCompatActivity implements View.OnClickListener{
    final static int[] ids = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4, R.id.editText5, R.id.editText6, R.id.editText7, R.id.editText8};
   final static String[] keys = {"BookID", "Title", "CategoryName", "ISBN", "Author", "Stock", "Price", "Discount"};
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_detail);
       // StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        Intent i = getIntent();
        String eid = i.getStringExtra("bid");

        new AsyncTask<String, Void, Book>() {
            @Override
            protected Book doInBackground(String... params) {
                return Book.getEmp(params[0]);
            }
            @Override
            protected void onPostExecute(Book result) {
                show(result);
            }
        }.execute(eid);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(this);

    }

    void show(Book b) {

        for (int i = 0; i < keys.length; i++) {
            EditText e = (EditText) findViewById(ids[i]);
            e.setText(b.get(keys[i]));
        }

        ImageView img = (ImageView) findViewById(R.id.img);
        String imgName = "a" + b.get("ISBN");
        int imgId = this.getResources().getIdentifier(imgName,"drawable", this.getPackageName());
        img.setImageResource(imgId);


    }


        @SuppressLint("StaticFieldLeak")
        @Override
        public void onClick(View v) {
            final Book b = new Book();
            for (int i=0; i<ids.length; i++) {
                EditText t = (EditText) findViewById(ids[i]);
                b.put(keys[i], t.getText().toString());
            }
            new AsyncTask<Book, Void, Void>() {
                @Override
                protected Void doInBackground(Book... params) {
                    Book.updateCustomer(params[0]);
                    return null;
                }
                @Override
                protected void onPostExecute(Void result) {
                    Intent intent = new Intent();
                    intent.putExtra("title", b.get("Title"));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }.execute(b);
        }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("title", "");
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

}
