package com.example.mainpchan.csc201_projectassignmentdistribution_app;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;

public class signupform extends AppCompatActivity implements View.OnClickListener {
    static final String FILE_NAME = "signstu.txt";
    EditText stuname, stuemail;
    Button signbtn;
    String strConcat = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupform);

        signbtn = (Button) findViewById(R.id.signbtn);
        signbtn.setOnClickListener(this);

        stuname = (EditText) findViewById(R.id.stuname);
        stuemail = (EditText) findViewById(R.id.stuemail);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.signbtn) {

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
                pw.println(stuname.getText().toString() + ";'" + stuemail.getText().toString());
                pw.close();

                Toast toast = Toast.makeText(this, "student info saved",
                        Toast.LENGTH_SHORT);
                toast.show();

            } catch (Exception e) {
                Log.e("File", "예외 =>" + e);
            }
        }

    }
}







