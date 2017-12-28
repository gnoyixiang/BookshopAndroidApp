package com.example.asus.bookstore_team8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxong on 21/12/2017.
 */

public class BookAdapter extends BaseAdapter implements Filterable {

    private final Context mContext;
    private List<Book> books;

    private List<Book> filteredBooks;
    ValueFilter valueFilter;

    public BookAdapter(Context context, List<Book> books) {
        this.mContext = context;
        this.books = books;
        this.filteredBooks = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Book book = books.get(i);

        // 2
        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.book_info, null);
        }

        // 3
        final ImageView imageView = (ImageView)view.findViewById(R.id.bookImg);
        final TextView titleTextView = (TextView)view.findViewById(R.id.title);
        final TextView authorTextView = (TextView)view.findViewById(R.id.author);

        // 4
        String imgName = "a" + book.get("ISBN");
        int imgId = mContext.getResources().getIdentifier(imgName,"drawable", mContext.getPackageName());
        imageView.setImageResource(imgId);
        titleTextView.setText(book.get("Title"));
        authorTextView.setText(book.get("Author"));

        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<Book> filterList = new ArrayList<>();
                for (int i = 0; i < filteredBooks.size(); i++) {
                    if ((filteredBooks.get(i).get("Title").toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(filteredBooks.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = filteredBooks.size();
                results.values = filteredBooks;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            books = (List) results.values;
            notifyDataSetChanged();
        }
    }
}
