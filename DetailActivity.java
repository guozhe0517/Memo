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
