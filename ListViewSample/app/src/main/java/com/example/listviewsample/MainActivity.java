package com.example.listviewsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnListViewClickLister{
    ListView listView;
    ArrayList<Student> mData;
    TextView txtMain;
    MyDB myDatabase;
    EditText edtId, edtName, edtAge;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtId = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name);
        edtAge = findViewById(R.id.edt_age);
        listView = findViewById(R.id.list_view);
        mData = new ArrayList<>();
         myDatabase = new MyDB(this) ;

        getData();
         adapter = new MyAdapter(MainActivity.this, mData, this);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void setData(){


        myDatabase.addStudent(new Student(1,"Student 1",20));
        myDatabase.addStudent(new Student(2,"Student 2",25));


    }

    public void onInsert(View v){
        myDatabase.addStudent(new Student(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString(),
                Integer.parseInt(edtAge.getText().toString())));
        getData();
        adapter.notifyDataSetChanged();
    }

    public void getData(){
        mData.clear();
        mData.addAll(myDatabase.getAllStudents());
    }

    @Override
    public void onItemClick(String name) {
        Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();

    }
}