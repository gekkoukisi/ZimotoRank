package tokki.planningdev.com.zimotorank.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tokki.planningdev.com.zimotorank.R;

public class ResultActivity extends Activity {
    TextView your_place,rank_type,hotel_name,rank,detail;
    String todouhuken;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        dialog = new ProgressDialog(this);
        dialog.setTitle("通信中");
        dialog.setMessage("Now Loading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        your_place = (TextView)findViewById(R.id.yourplace);
        rank_type = (TextView)findViewById(R.id.ranktype);
        hotel_name = (TextView)findViewById(R.id.placename);
        rank = (TextView)findViewById(R.id.rank);
        detail = (TextView)findViewById(R.id.detail);
        todouhuken = getIntent().getStringExtra("todouhuken");
        ConnectRanking con = new ConnectRanking();
        con.execute(todouhuken);

    }

    public void setResult(String result) throws JSONException {
        JSONObject rootJSON = new JSONObject(result);
        JSONArray rankings = rootJSON.getJSONArray("Rankings");
        JSONObject rankingJSON = rankings.getJSONObject(0);
        JSONObject ranking = rankingJSON.getJSONObject("Ranking");
        JSONArray hotels = ranking.getJSONArray("hotels");
        JSONObject hotelJSON,hotel;
        String place_name,type="";
        your_place.setText(todouhuken+"は、");
        for(int i=0;i<hotels.length();i++){
            hotelJSON = hotels.getJSONObject(i);
            hotel = hotelJSON.getJSONObject("hotel");
            place_name = hotel.getString("middleClassName");
            Log.d("www",todouhuken+"    "+place_name);

            if(todouhuken.equals(place_name)){
                if(ranking.getString("genre").equals("all")){

                    type = "総合部門";

                }
                rank_type.setText(type+"で、");
                hotel_name.setText(hotel.getString("hotelName")+"が、");
                rank.setText("第"+hotel.getInt("rank")+"位でした！！！");
                return;
            }
        }
        hotel_name.setText("ランク外！！！");
        rank.setText("人気ねえな！！！");


    }

    public class ConnectRanking extends AsyncTask<String, Integer, String> {

        @Override
        
        protected String doInBackground(String... params) {
            String url = params[0];
            try
            {
                HttpGet method = new HttpGet( "https://app.rakuten.co.jp/services/api/Travel/HotelRanking/20131024?applicationId=1015673794411346393&format=json&carrier=0&genre=all" );

                DefaultHttpClient client = new DefaultHttpClient();

                // ヘッダを設定する
                method.setHeader( "Connection", "Keep-Alive" );

                HttpResponse response = client.execute( method );
                int status = response.getStatusLine().getStatusCode();
                Log.d("www",status+"");

                if ( status != HttpStatus.SC_OK )
                    throw new Exception( "" );


                String ret = EntityUtils.toString(response.getEntity(), "UTF-8");
                return ret;
            }
            catch ( Exception e )
            {
                Log.d("www","error   "+e.getMessage());
                return null;
            }
        }

        protected void onPreExecute(){

            Log.d("www","start");
            dialog.show();

        }

        protected void onPostExecute(String req){

            Log.d("www",req);
            dialog.dismiss();
            try {
                setResult(req);
            } catch (JSONException e) {
                Log.d("www","json error");

                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result, menu);
        return true;
    }
    
}
