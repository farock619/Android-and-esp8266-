
package com.example.faroc.fyp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LED=ON");

            }
        });
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                request("/LED=OFF");

            }
        });
    }

    public void request(String command){

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){

            String url = "http://192.168.43.92/";

            new RequestedData().execute(url+command);
        }
        else{
            Toast.makeText(MainActivity.this,"No connection detected", Toast.LENGTH_LONG).show();
        }
    }
    private class RequestedData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            return Connection.getData(url[0]);
        }


        /*@Override
        protected void onPostExecute(String result) {
            if (result !=null) {
                txtresult.setText(result);
                if (result.contains("STEM LAB LIGHT IS NOW:ON")) {
                    btn1.setText("TURN ON");
                }
                else if (result.contains("STEM LAB LIGHT IS NOW:OFF")) {

                    btn1.setText("TURN OFF");
                }

            } else {
                Toast.makeText(MainActivity.this,"OPPS", Toast.LENGTH_LONG).show();
            }
        }*/
    }
}
