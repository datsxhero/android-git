package com.example.user.myapplication;

//package com.example.appgetsql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
    TextView tv1,tv2;
    TableLayout user_list;
    String data1 = null,data2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2);
        user_list = (TableLayout)findViewById(R.id.user_list);
        user_list.setStretchAllColumns(true);


        String uri = "http://xxx/index_phone.php";
        String TAG_STRING = "file path";
        // check if you are connected or not
        if(isConnected()){
            tv1.setBackgroundColor(0xFF00CC00);
            tv1.setText("You are conncted");
        }
        else{
            tv1.setText("You are NOT conncted");
        }

        // call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute("http://demo.netyea.com/index_phone.php");

    }
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException, JSONException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();

        return result;

    }
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            TableLayout.LayoutParams row_layout = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            TableRow.LayoutParams view_layout = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i=0;i<jArray.length();i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                                    /*data1 = json_data.getString("id");
                                    data2 = json_data.getString("subject");
                                    tv1.setText(data1+":"+data2 );*/

                    TableRow tr = new TableRow(MainActivity.this);
                    tr.setLayoutParams(row_layout);
                    tr.setGravity(Gravity.CENTER_HORIZONTAL);

                    TextView user_id = new TextView(MainActivity.this);
                    user_id.setText(json_data.getString("id"));
                    user_id.setLayoutParams(view_layout);

                    TextView user_subject = new TextView(MainActivity.this);
                    user_subject.setText(json_data.getString("subject"));
                    user_subject.setLayoutParams(view_layout);

                    tr.addView(user_id);
                    tr.addView(user_subject);

                    user_list.addView(tr);
                }
            } catch (JSONException e) {
                // TODO 自動產生的 catch 區塊
                e.printStackTrace();
            } // convert String to JSONObject
        }
    }

}