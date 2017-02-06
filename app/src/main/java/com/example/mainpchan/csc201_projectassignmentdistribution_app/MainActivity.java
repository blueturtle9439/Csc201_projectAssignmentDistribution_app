package com.example.mainpchan.csc201_projectassignmentdistribution_app;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intentact;
    final String gmail_id = "mailsender4444@gmail.com";
    final String gmail_pwd = "i!N0nLlS*5cS";
    String[][] To;
    String[][] qs;
    static final String FILE_NAME1 = "signstu.txt";
    static final String FILE_NAME2 = "qlist.txt";

    static int stunum = 0, qnum = 0;
    String et_title = "", et_content = "", receiver = "";

    int[] randomqnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signformbtn = (Button) findViewById(R.id.signform);
        Button setqformbtn = (Button) findViewById(R.id.setqform);
        Button stulistbtn = (Button) findViewById(R.id.stulist);
        Button qlistbtn = (Button) findViewById(R.id.qlist);
        Button sendbtn = (Button) findViewById(R.id.send);
        signformbtn.setOnClickListener(this);
        setqformbtn.setOnClickListener(this);
        stulistbtn.setOnClickListener(this);
        qlistbtn.setOnClickListener(this);
        sendbtn.setOnClickListener(this);


    }


    public void onClick(View v) {
        if (v.getId() == R.id.signform) {
            intentact = new Intent(getApplicationContext(), signupform.class);
            startActivity(intentact);


        } else if (v.getId() == R.id.setqform) {

            intentact = new Intent(getApplicationContext(), setquestion.class);
            startActivity(intentact);

        } else if (v.getId() == R.id.stulist) {
            intentact = new Intent(getApplicationContext(), stulist.class);
            startActivity(intentact);
        } else if (v.getId() == R.id.qlist) {

            intentact = new Intent(getApplicationContext(), qlist.class);
            startActivity(intentact);
        } else if (v.getId() == R.id.send) {


            StringBuffer strBuffer = new StringBuffer();

            try {
                File file = new File(getExternalFilesDir(null), FILE_NAME1);
                BufferedReader in = new BufferedReader(new FileReader(file));
                String s;
                stunum = countLines(file);
                To = new String[stunum][2];

                for (int i = 0; i < stunum; i++) {
                    s = in.readLine();
                    To[i] = s.split(";'");
                }
                in.close();


            } catch (Exception e) {
                Log.e("File", "예외 =>" + e);
            }

            try {
                File file2 = new File(getExternalFilesDir(null), FILE_NAME2);
                BufferedReader in = new BufferedReader(new FileReader(file2));
                String s;
                qnum = countLines(file2);
                qs = new String[qnum][2];

                for (int i = 0; i < qnum; i++) {
                    s = in.readLine();
                    qs[i] = s.split(";'");
                }
                in.close();


            } catch (Exception e) {
                Log.e("File", "예외 =>" + e);
            }


            if (qnum > stunum) {
                randomqnum = randomnum(stunum, qnum);
                Log.e("stunum", "" + stunum);
                Log.e("qnum", "" + qnum);

                for (int i = 0; i < stunum; i++) {

                    et_title = ("CSC201 questions for " + To[i][0]);
                    et_content = qs[randomqnum[i]][0] + "\n The answer is " + qs[randomqnum[i]][1];
                    receiver = To[i][1];

                    GMailSender sender = new GMailSender(gmail_id, gmail_pwd); // SUBSTITUTE

                    if (android.os.Build.VERSION.SDK_INT > 9) {

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                .permitAll().build();

                        StrictMode.setThreadPolicy(policy);

                    }
                    // HERE
                    try {
                        sender.sendMail(et_title, // subject.getText().toString(),
                                et_content, // body.getText().toString(),
                                gmail_id, // from.getText().toString(),
                                receiver // to.getText().toString()
                        );

                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }


                }

                Toast toast = Toast.makeText(this, "questions sent!",
                        Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(this, "Please set some more questions.",
                        Toast.LENGTH_SHORT);
                toast.show();
            }


        }


    }


    public int countLines(File file) throws IOException {
        ArrayList<String> items = new ArrayList<String>();
        StringBuffer strBuffer = new StringBuffer();

        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String s;

            while ((s = in.readLine()) != null) {
                items.add(s);
            }
            in.close();


        } catch (Exception e) {
            Log.e("File", "예외 =>" + e);
        }
        return items.size();
    }

    public static int[] randomnum(int stunum, int qnum) {


        int[] numbers = new int[stunum];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] =
                    (int) (Math.random() * qnum) + 1;
            for (int j = 0; j < i; j++) {
                if (numbers[i] == numbers[j]) {
                    i--;
                    break;
                }
            }
        }

        return numbers;
    }

}
