package itmvu.litmus17;

import android.content.Context;
import android.content.Intent;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ABCD on 1/10/2016.
 */
public class pushrecive extends ParsePushBroadcastReceiver {

   /* @Override
    protected void onPushReceive(Context context,Intent intent)
    {
        //super.onPushReceive(context,intent);
        Intent i = new Intent(context, abtus.class);
        //i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }*/

    @Override
    protected void onPushOpen(Context context, Intent intent) {
       // super.onPushOpen(context, intent);

        Intent i = new Intent(context, notify.class);
        //i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
     String jsonString =   intent.getStringExtra("data");
        try {
            JSONObject object = new JSONObject(jsonString);
            String type = (String) object.get("type");
            String objectId = (String) object.get("objectId");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}


