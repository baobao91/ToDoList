package com.example.a126308.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.data;
import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lvImpt;

    ArrayList<MyList> lists;
    ArrayAdapter aa;

    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.buttonAdd);

        lvImpt = (ListView) findViewById(R.id.lvImpt);
        registerForContextMenu(lvImpt);

        lists = new ArrayList<MyList>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        final ListAdapter adapter = new ListAdapter(this, R.layout.row, lists);

        lvImpt.setAdapter(adapter);

        DBHelper dbh = new DBHelper(MainActivity.this);
        lists.clear();
        lists.addAll(dbh.getAllList());
        dbh.close();

        aa.notifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i, 9);

            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.lvImpt) {
            menu.add(0, 0, 0, "Delete Item");
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == 0) { //check whether the selected menu item ID is 0

            DBHelper dbh = new DBHelper(MainActivity.this);
            dbh.deleteList(info.position);

            lists.remove(info.position);
            dbh.close();

            aa.notifyDataSetChanged();

            Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            return true;  //menu item successfully handled
        }


        return super.onContextItemSelected(item); //pass menu item to the superclass implementation.
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            DBHelper dbh = new DBHelper(MainActivity.this);
            lists.clear();
            lists.addAll(dbh.getAllList());
            dbh.close();

            aa.notifyDataSetChanged();
        }
    }
}
