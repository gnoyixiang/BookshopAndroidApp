package com.example.asus.bookstore_team8;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ASUS on 17/12/20.
 */

public class Book extends HashMap<String, String> {

    final static String baseURL = "http://172.17.248.121/bookshop/Service1.svc/";

    public Book(String bookID, String title, String categoryName, String isbn,String author,String stock,String price,String discount) {
        put("BookID", bookID);
        put("Title", title);
        put("CategoryName", categoryName);
        put("ISBN", isbn);
        put("Author", author);
        put("Stock", stock);
        put("Price", price);
        put("Discount", discount);
    }

    public Book(String bookId, String title,String author) {
        put("BookID", bookId);
        put("Title", title);
        put("Author", author);
    }

    public Book(String bookId, String title,String author, String isbn) {
        put("BookID", bookId);
        put("Title", title);
        put("Author", author);
        put("ISBN", isbn);
    }

    public Book(){

    }

    public static List<Book> list() {
        List<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "Books");
        try {
            for (int i =0; i<a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book(b.getString("BookID"), b.getString("Title"), b.getString("Author"), b.getString("ISBN")));
            }
        } catch (Exception e) {
            Log.e("Book.list()", "JSONArray error");
        }
        return(list);
    }


    public static Book getEmp(String bid) {
        JSONObject b = JSONParser.getJSONFromUrl(baseURL + "Book/" + bid);
        try {
            return new Book(b.getString("BookID"), b.getString("Title"),
                    b.getString("CategoryName"), b.getString("ISBN"),b.getString("Author"), b.getString("Stock"),
                    b.getString("Price"), b.getString("Discount"));
        } catch (Exception e) {
            Log.e("Book.getEmp()", "JSONArray error");
            Log.e("BookId", bid);

        }
        return(null);
    }

    public static void updateCustomer(Book b) {
        JSONObject book = new JSONObject();
        try {
            book.put("BookID", b.get("BookID"));
            book.put("Stock", Integer.parseInt(b.get("Stock")));
            book.put("Price", new BigDecimal(b.get("Price")));

            if(b.get("Discount") .equals("null")|| b.get("Discount").isEmpty()||b.get("Discount").equals(null) || b.get("Discount").equals("")){
                book.put("Discount",new BigDecimal(0.0));
            }
            else{
                book.put("Discount",new BigDecimal(b.get("Discount")));
            }
        } catch (Exception e) {
        }
        String result = JSONParser.postStream(baseURL+"/Update", book.toString());
    }
}
