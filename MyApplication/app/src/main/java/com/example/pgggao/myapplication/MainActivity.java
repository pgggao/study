package com.example.pgggao.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

   private String guide_url = getResources().getString(R.string.url_guide);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = (EditText) findViewById(R.id.inputData);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText text = (EditText) v;
                String data = text.getText().toString();
                if(hasFocus){
                    if (guide_url.equals(data)){
                        text.setText("");
                    }
                }else{
                    if(data==null||"".equals(data.trim())){
                        text.setText(guide_url);
                    }
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void requestUrlData(View view){
        EditText editText = (EditText) findViewById(R.id.inputData);
        String data = editText.getText().toString();
        if(data==null||"".equals(data.trim())||guide_url.equals(data)){
            Toast toast = Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else{
            HttpGet httpGet = new HttpGet(data);
            try {
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
                if(httpResponse.getStatusLine().getStatusCode()==200){
                  String content =   EntityUtils.toByteArray(httpResponse.getEntity()).toString();
                }
                RelativeLayout RelativeLayout= (android.widget.RelativeLayout) findViewById(R.id.layout);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
