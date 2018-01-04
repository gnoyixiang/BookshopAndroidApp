package com.example.asus.bookstore_team8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.net.UnknownHostException;
import java.util.List;

public class GridActivity extends AppCompatActivity{

    List<Book> result;
    private static final int REQ_CODE_DETAILS = 1234;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        new AsyncTask<Void, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(Void... params) {
                result = Book.list();
                return result;
            }
            @Override
            protected void onPostExecute(List<Book> result) {
                    loadView(result);
            }
        }.execute();


    }

    private void loadView(List<Book> result){
        final BookAdapter bookAdapter = new BookAdapter(GridActivity.this, result);
        final GridView gridView = (GridView)findViewById(R.id.gridview);

        gridView.setAdapter(bookAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Book book = (Book) parent.getAdapter().getItem(position);
                Intent intent = new Intent(GridActivity.this, BookDetailActivity.class);
                intent.putExtra("bid", book.get("BookID"));
                startActivityForResult(intent, REQ_CODE_DETAILS);
            }
        });

        SearchView search = (SearchView) findViewById(R.id.search) ;
        search.setQuery("", false);
        search.clearFocus();
        //                search.setActivated(true);
//                search.onActionViewExpanded();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bookAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_DETAILS) {
            String title = data.getStringExtra("title");
            if(!title.equals("")){
                Toast.makeText(this, "Updated " + title, Toast.LENGTH_SHORT).show();
            }
        }
        loadView(result);
    }
}
