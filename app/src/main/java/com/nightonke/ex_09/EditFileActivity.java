package com.nightonke.ex_09;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;

public class EditFileActivity extends AppCompatActivity {

    private AutoCompleteTextView name;
    private EditText content;

    private Button save;
    private Button read;
    private Button delete;

    private Context mContext;

    private FileUtils fileUtils = FileUtils.getInstance();

    private ArrayList<String> fileList;
    private ArrayAdapter<String> fileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_file);

        mContext = this;

        getAllFiles();
        fileAdapter = new ArrayAdapter<>
                (mContext, android.R.layout.simple_dropdown_item_1line, fileList);

        name = (AutoCompleteTextView)findViewById(R.id.name);
        content = (EditText)findViewById(R.id.content);

        name.setAdapter(fileAdapter);

        save = (Button)findViewById(R.id.save);
        read = (Button)findViewById(R.id.read);
        delete = (Button)findViewById(R.id.delete);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileUtils.save(mContext,
                        name.getText().toString(), content.getText().toString());
                fileAdapter.add(name.getText().toString());
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.setText(fileUtils.read(mContext, name.getText().toString()));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileUtils.delete(mContext, name.getText().toString())) {
                    fileAdapter.remove(name.getText().toString());
                    name.setText("");
                    content.setText("");
                }
            }
        });
    }

    private void getAllFiles() {
        fileList = new ArrayList<>();
        File[] files = getFilesDir().listFiles();
        for (File file : files) {
            fileList.add(file.getName());
        }
    }

}
