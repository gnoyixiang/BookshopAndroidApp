package com.example.asus.bookstore_team8;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class MainActivity extends Activity
        implements AdapterView.OnItemClickListener {

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       // StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        setContentView(R.layout.activity_main);
        final ListView lv = (ListView) findViewById(R.id.listView1);
        lv.setOnItemClickListener(this);
        new AsyncTask<Void, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(Void... params) {
                return Book.list();
            }
            @Override
            protected void onPostExecute(List<Book> result) {
                lv.setAdapter(new SimpleAdapter
                        (MainActivity.this, result, R.layout.row, new String[]{"Title","Author"},
                                new int[]{ R.id.text1, R.id.text2}));
            }
        }.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        Book s = (Book) av.getAdapter().getItem(position);
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("bid", s.get("BookID"));
        startActivity(intent);
    }
}
