package itmvu.litmus17;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.firebase.client.Firebase;

public class mainpage extends AppCompatActivity {

    SharedPreferences prefs = null;
    private SliderLayout mDemoSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        prefs = getSharedPreferences("itmvu.litmus17", MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Litmus'17");



//        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
//
//        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Fury of Tracks",R.drawable.t1);
//        file_maps.put("Code To Win",R.drawable.t2);
//        file_maps.put("FASHION FUSION",R.drawable.k1);
//        file_maps.put("FIERY FIGHTERS",R.drawable.t3);
//        file_maps.put("SPEEDING MACHINES", R.drawable.t4);
//        file_maps.put("DANCELLENIUM",R.drawable.k2);
//        file_maps.put("FLYING WARRIORS", R.drawable.t5);
//        file_maps.put("Construo", R.drawable.t6);
//        file_maps.put("Bridge The Gap", R.drawable.t7);
//        file_maps.put("CURTAIN UP",R.drawable.k3);
//        file_maps.put("Shift To Beat", R.drawable.t8);
//        file_maps.put("Hit it to Win it", R.drawable.t9);
//        file_maps.put("Folder Maze", R.drawable.t10);
//        file_maps.put("SOUND AT SIGHT AND UNPLUGGED",R.drawable.k4);
//        file_maps.put("Reclamation", R.drawable.t11);
//        file_maps.put("ARTRIVIA", R.drawable.t12);
//
//
//
//        for(String name : file_maps.keySet()){
//            TextSliderView textSliderView = new TextSliderView(this);
//            // initialize a SliderLayout
//            textSliderView
//                    .image(file_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
//
//            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra",name);
//
//            mDemoSlider.addSlider(textSliderView);
//        }
//        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet);
//        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
//        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
//        mDemoSlider.setDuration(2000);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {

            String shareBody = getResources().getString(R.string.link);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Have a look at LITMUS'17, Annual fest of ITMVU. \n\n");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.frwd)));

            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public void Hiren (View view  )

    {
        startActivity(new Intent(mainpage.this,events.class));
    }

    public void jayesh (View view  )

    {
        startActivity(new Intent(mainpage.this,registermenu.class));
    }

    public void abt (View view )
    {
        Intent intent = new Intent(this,abtus.class);
        startActivity(intent);
    }

    public void cntus (View view)
    {
        Intent intent = new Intent(this,contactus.class);
        startActivity(intent);
    }

    public void sch (View view)
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(mainpage.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("SCHEDULE");
        builder.setMessage("Schedule of the Litmus'17 will be avaliable soon...");
        //builder.setPositiveButton("OK", null);//second parameter used for onclicklistener
        builder.setNegativeButton("Got It", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void spo (View view)
    {
        Intent intent = new Intent(this,Sp.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit ...")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("No", null).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            Firebase.getDefaultConfig().setPersistenceEnabled(true);
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

}
