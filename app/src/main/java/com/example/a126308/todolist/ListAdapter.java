package com.example.a126308.todolist;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by 126308 on 8/6/2017.
 */

public class ListAdapter extends ArrayAdapter<MyList> {

    private ArrayList<MyList> lists;
    private TextView tvTitle;
    private TextView tvLists;
    private Context context;
    private ArrayAdapter aa;

    public ListAdapter(Context context, int resource, ArrayList<MyList> objects) {
        super(context, resource, objects);

        lists = objects;

        this.context = context;
    }

    // getView() is the method ListView will call to get the
    //  View object every time ListView needs a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvTitle = (TextView) rowView.findViewById(R.id.textViewTitle);
        tvLists = (TextView) rowView.findViewById(R.id.textViewList);

        MyList currentLists = lists.get(position);

        // Set the TextView to show the food
        tvTitle.setText(currentLists.getTitle());
        tvLists.setText(currentLists.getList());
        return rowView;


    }


}
