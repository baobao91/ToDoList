package com.example.a126308.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.attr.name;

public class AddActivity extends AppCompatActivity {

    EditText etTitle, etList;
    Button btnAdd, btnCancel;
    Spinner spnList;

    int reqCode = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = (EditText)findViewById(R.id.editTextTitle);
        etList = (EditText) findViewById(R.id.editTextList);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnCancel = (Button) findViewById(R.id.buttonCancel);

        spnList = (Spinner) findViewById(R.id.spinnerList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etTitle.getText().toString();
                String list = etList.getText().toString();

                if (title.isEmpty() || list.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill up the BLANKS!",
                            Toast.LENGTH_SHORT).show();
                } else {

                    DBHelper dbh = new DBHelper(AddActivity.this);
                    long row_affected = dbh.insertList(title, list);
                    dbh.close();

                    if (row_affected != -1){
                        Toast.makeText(AddActivity.this, "Insert successful",
                                Toast.LENGTH_SHORT).show();
                    }

                    if (spnList.getSelectedItem() == "Important") {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        setResult(RESULT_OK);
                        finish();
                    }

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etTitle.getText().toString();
                String list = etList.getText().toString();

                if (title.isEmpty() && list.isEmpty()) {

                    finish();

                } else {

                    etTitle.setText("");
                    etList.setText("");
                    spnList.setSelection(0);
                }

            }
        });
    }
}
