package itmvu.litmus17;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class t3 extends AppCompatActivity {

    Toolbar toolbar;

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t3);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        t1 = (TextView)findViewById(R.id.coo1);

      //  Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://itml.firebaseio.com/");

        myFirebaseRef.child("Technovision/t3/GirlsGullyCricket_magnif").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                String s1 = (String) snapshot.getValue();
                t1.setText(s1);
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
    }



    public void loc (View view)
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(t3.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Location");
        builder.setMessage("open arean, Beside Basket Ball Court ,ITM Vocational Univeristy ");
        //builder.setPositiveButton("OK", null);//second parameter used for onclicklistener
        builder.setNegativeButton("Yeah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
