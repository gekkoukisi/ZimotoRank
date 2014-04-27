package tokki.planningdev.com.zimotorank.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import tokki.planningdev.com.zimotorank.R;

public class Ask extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // アイテムを追加します
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // アダプターを設定します
        setTodouhuken(adapter);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("wwwwww","selected");
                if(position == 0) return;
                Spinner spinner = (Spinner) parent;
                // 選択されたアイテムを取得します
                String item = (String) spinner.getSelectedItem();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ask.this);
                // アラートダイアログのタイトルを設定します
                alertDialogBuilder.setTitle("確認");
                // アラートダイアログのメッセージを設定します
                alertDialogBuilder.setMessage(item+"でよろしいですか？");
                // アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
                alertDialogBuilder.setPositiveButton("決定！！",
                        new AskDialogClickListener(Ask.this,item,ResultActivity.class) {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context,dest);
                                intent.putExtra("todouhuken",extra);
                                Log.d("www","intent");
                                context.startActivity(intent);
                            }
                        });
                // アラートダイアログの否定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
                alertDialogBuilder.setNegativeButton("キャンセル",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //何もしない
                            }
                        });
                // アラートダイアログのキャンセルが可能かどうかを設定します
                alertDialogBuilder.setCancelable(true);
                AlertDialog alertDialog = alertDialogBuilder.create();
                // アラートダイアログを表示します
                alertDialog.show();
                //Toast.makeText(SpinnerSampleActivity.this, item, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        Log.d("wwwwww","prepare");

    }

    private void setTodouhuken(ArrayAdapter<String> adapter){
        adapter.add("出身地を選択してください。");
        adapter.add("北海道");
        adapter.add("青森県");
        adapter.add("秋田県");
        adapter.add("岩手県");
        adapter.add("山形県");
        adapter.add("宮城県");
        adapter.add("福島県");
        adapter.add("栃木県");
        adapter.add("茨城県");
        adapter.add("群馬県");
        adapter.add("東京都");
        adapter.add("千葉県");
        adapter.add("神奈川県");
        adapter.add("埼玉県");
        adapter.add("新潟県");
        adapter.add("長野県");
        adapter.add("富山県");
        adapter.add("石川県");
        adapter.add("山梨県");
        adapter.add("静岡県");
        adapter.add("愛知県");
        adapter.add("岐阜県");
        adapter.add("三重県");
        adapter.add("福井県");
        adapter.add("滋賀県");
        adapter.add("京都府");
        adapter.add("大阪府");
        adapter.add("奈良県");
        adapter.add("和歌山県");
        adapter.add("兵庫県");
        adapter.add("鳥取県");
        adapter.add("島根県");
        adapter.add("岡山県");
        adapter.add("広島県");
        adapter.add("山口県");
        adapter.add("香川県");
        adapter.add("愛媛県");
        adapter.add("高知県");
        adapter.add("徳島県");
        adapter.add("福岡県");
        adapter.add("佐賀県");
        adapter.add("大分県");
        adapter.add("熊本県");
        adapter.add("長崎県");
        adapter.add("宮崎県");
        adapter.add("鹿児島県");
        adapter.add("沖縄県");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ask, menu);
        return true;
    }
    
}
