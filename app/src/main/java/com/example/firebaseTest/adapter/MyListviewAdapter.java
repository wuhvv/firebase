package com.example.firebaseTest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.firebaseTest.model.Post;
import com.example.firebaseTest.R;

import java.util.ArrayList;

public class MyListviewAdapter extends BaseAdapter {

    ArrayList<Post> items;
    Context context;

    public MyListviewAdapter(ArrayList<Post> items, Context context){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listview_item, viewGroup, false);

        TextView tv_title = view.findViewById(R.id.textview_title);
        tv_title.setText(items.get(i).getTitle());

        return view;
    }
}
