package itmvu.litmus17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class notify extends AppCompatActivity {

    TextView t1;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Firebase myFirebaseRef = new Firebase("https://itml.firebaseio.com/");
        myFirebaseRef.keepSynced(true);

        t1 = (TextView) findViewById(R.id.h1);

        myFirebaseRef.child("notify").addValueEventListener(new ValueEventListener() {
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


}
