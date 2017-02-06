package com.example.mainpchan.csc201_projectassignmentdistribution_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by mainpcHan on 2017-01-30.
 */
public class qlist extends AppCompatActivity {
    static final String FILE_NAME = "qlist.txt";
    ListView qscroll;
    ArrayList<String> items;
    ArrayAdapter adapter;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qlist);


        items = new ArrayList<String>();
        StringBuffer strBuffer = new StringBuffer();

        try {
            File file = new File(getExternalFilesDir(null), FILE_NAME);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String s;

            while ((s = in.readLine()) != null) {
                items.add(s.replace(";'", " -answer: "));
            }
            in.close();


        } catch (Exception e) {
            Log.e("File", "예외 =>" + e);
        }


        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
               adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        qscroll = (ListView) findViewById(R.id.qscroll);
        qscroll.setAdapter(adapter);



    }

}
