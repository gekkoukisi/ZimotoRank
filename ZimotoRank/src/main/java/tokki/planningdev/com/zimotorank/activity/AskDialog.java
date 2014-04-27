package tokki.planningdev.com.zimotorank.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

/**
 * Created by gekkoukisi on 2014/04/27.
 */
class AskDialogClickListener implements DialogInterface.OnClickListener {
    String extra;
    Context context;
    Class dest;
    public AskDialogClickListener(Context context,String extra,Class dest) {
        super();
        this.context = context;
        this.extra = extra;
        this.dest = dest;
    }
    public String getExtra(){
        return this.extra;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent intent = new Intent(context,dest);
        intent.putExtra("todouhuken",extra);
        Log.d("www","intent");
        context.startActivity(intent);
    }
}
