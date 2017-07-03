package itmvu.litmus17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class registermenu extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registermenu);
        
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void rt (View view  )

    {
        startActivity(new Intent(registermenu.this, RegisterTechnovision.class));
    }

    public void rm (View view  )

    {
        startActivity(new Intent(registermenu.this,RegisterMagnif.class));
    }

    public void rc (View view  )

    {
        startActivity(new Intent(registermenu.this,RegisterSports.class));
    }

    public void rk (View view  )
    {
        startActivity(new Intent(registermenu.this,registerk.class));

    }

}
