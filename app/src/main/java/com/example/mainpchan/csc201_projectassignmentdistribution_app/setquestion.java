package com.example.mainpchan.csc201_projectassignmentdistribution_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by mainpcHan on 2017-01-30.
 */
public class setquestion extends AppCompatActivity  implements View.OnClickListener {
    static final String FILE_NAME = "qlist.txt";
    EditText q, a;
    Button saveqbtn;
    String strConcat = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setquestion);

        saveqbtn = (Button) findViewById(R.id.setqbtn);
        saveqbtn.setOnClickListener(this);

        q = (EditText) findViewById(R.id.q);
        a = (EditText) findViewById(R.id.a);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.setqbtn) {

            try {


                File file = new File(getExternalFilesDir(null), FILE_NAME);
                if (!file.exists()) {
                    file.createNewFile();
                }

                BufferedReader in = new BufferedReader(new FileReader(file));
                String s;

                while ((s = in.readLine()) != null) {
                    strConcat += s + "\n";
                }
                in.close();

                PrintWriter pw = new PrintWriter(file);
                pw.print(strConcat );
                pw.println(q.getText().toString() + ";'" + a.getText().toString());
                pw.close();

                Toast toast = Toast.makeText(this, "question info saved",
                        Toast.LENGTH_SHORT);
                toast.show();

            } catch (Exception e) {
                Log.e("File", "예외 =>" + e);
            }
        }

    }
}


