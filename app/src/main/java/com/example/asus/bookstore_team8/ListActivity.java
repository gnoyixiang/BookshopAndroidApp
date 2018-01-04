package com.example.asus.bookstore_team8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class ListActivity extends android.app.ListActivity implements AdapterView.OnItemClickListener {

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent i = getIntent();
        i.getStringExtra("search");

        final ListView listView = findViewById(android.R.id.list);
        listView.setOnItemClickListener(this);

        new AsyncTask<Void, Void, List<Book>>(){

            @Override
            protected List<Book> doInBackground(Void... voids) {
                return Book.list();
            }

            @Override
            protected  void onPostExecute(List<Book> result){
                listView.setAdapter(new SimpleAdapter(ListActivity.this, result, R.layout.row,  new String[]{"Title","Author"},
                        new int[]{ R.id.text1, R.id.text2}));
            }
        }.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        Book s = (Book) av.getAdapter().getItem(position);
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra("review", s.get("Title"));
        startActivity(intent);
    }
}
