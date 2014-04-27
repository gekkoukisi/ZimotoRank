package tokki.planningdev.com.zimotorank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.Window;

import tokki.planningdev.com.zimotorank.R;

public class Root extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_root);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Root.this, Ask.class));
                finish();
            }
        }, 1500);//1500ms後に画面遷移する


    }
    protected void onResume(){
        super.onResume();

      //  Intent intent = new Intent(this, Ask.class);
      //  startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.root, menu);
        return true;
    }
    
}
