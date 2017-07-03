package itmvu.litmus17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class FireBaseExample extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv1;
    EditText ed1;
    Firebase myFirebaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_example);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv1 = (TextView)findViewById(R.id.coo1);


         myFirebaseRef = new Firebase("https://itml.firebaseio.com/");

        myFirebaseRef.child("Kalakriti/k1/c2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                String s1 = (String) snapshot.getValue();
                tv1.setText(s1);
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });



    }


}
