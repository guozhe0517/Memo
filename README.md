# Memo
처음만든간단메모
```java
package com.guozhe.android.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.guozhe.android.util.FileUtil;


public class DetailActivity extends AppCompatActivity {
    EditText content;
    FloatingActionButton save;

    String document_id = "";
    public static final String DOC_KEY_NAME = "document_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            document_id = bundle.getString(DOC_KEY_NAME);
        }

        content = (EditText) findViewById(R.id.content);
        save = (FloatingActionButton) findViewById(R.id.save);

        loadContent();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                writeContent();


            }
        });
    }

    private void writeContent() {
        String c = content.getText().toString();
        String filename = "MEMO" + System.nanoTime() + ".txt";
        if (!document_id.equals("")) {
            filename = document_id;
        }
        FileUtil.write(this, filename, c);
        finish();

    }

    private void loadContent() {
        if (!"".equals(document_id)) {
            String memo = FileUtil.read(this, document_id);
            content.setText(memo);
        }
    }

}

package com.guozhe.android.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.guozhe.android.memo.domain.Load;
import com.guozhe.android.memo.domain.Memo;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    FloatingActionButton fab;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Memo> datas = Load.getData(this);
        adapter = new RecyclerAdapter(datas);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
        protected void  onResume(){
        super.onResume();
        Load.getData(this);
        adapter.notifyDataSetChanged();
    }

}
