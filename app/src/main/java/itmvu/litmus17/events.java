package itmvu.litmus17;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class events extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new technovision(), "Technovision");
        adapter.addFragment(new magnif(), "Magnif");
        adapter.addFragment(new chakravyuh(), "Chakravyuh");
        adapter.addFragment(new kalakriti(),"Kalakriti");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

  /*      if(id==android.R.id.home)
        {
            onBackPressed();
        }
*/

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {

            String shareBody = getResources().getString(R.string.link);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Have a look at LITMUS'16, Annual fest of ITMVU. \n\n");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.frwd)));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


   /* @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
*/
    public void t1 (View view )
    {
        Intent intent = new Intent(this, t1.class);
        startActivity(intent);
    }

    public void t2 (View view)
    {
        Intent intent = new Intent(this,t2.class);
        startActivity(intent);
    }

    public void t3(View view)
    {
        Intent intent = new Intent(this,t3.class);
        startActivity(intent);
    }

    public void t4(View view)
    {
        Intent intent = new Intent(this,t4.class);
        startActivity(intent);
    }
    public void t5(View view)
    {
        Intent intent = new Intent(this,t5.class);
        startActivity(intent);
    }
    public void t6(View view)
    {
        Intent intent = new Intent(this,t6.class);
        startActivity(intent);
    }
    public void t7(View view)
    {
        Intent intent = new Intent(this,t7.class);
        startActivity(intent);
    }
    public void t8(View view)
    {
        Intent intent = new Intent(this,t8.class);
        startActivity(intent);
    }
    public void t9(View view)
    {
        Intent intent = new Intent(this,t9.class);
        startActivity(intent);
    }
    public void t10(View view)
    {
        Intent intent = new Intent(this,t10.class);
        startActivity(intent);
    }
    public void t11(View view)
    {
        Intent intent = new Intent(this,t11.class);
        startActivity(intent);
    }

    public void t12 (View view)
    {
        Intent intent = new Intent (this,t12.class);
        startActivity(intent);
    }

    public void t13 (View view )
    {
        Intent intent = new Intent(this,t13.class);
        startActivity(intent);
    }

    public void c1 (View view )
    {
        Intent intent = new Intent(this,GirlsGullyCricket_magnif.class);
        startActivity(intent);
    }

    public void c2 (View view )
    {
        Intent intent = new Intent(this,c2.class);
        startActivity(intent);
    }

    public void c3 (View view )
    {
        Intent intent = new Intent(this,c3.class);
        startActivity(intent);
    }

    public void c4 (View view )
    {
        Intent intent = new Intent(this,c4.class);
        startActivity(intent);
    }

    public void c5 (View view )
    {
        Intent intent = new Intent(this,c5.class);
        startActivity(intent);
    }

    public void c6 (View view )
    {
        Intent intent = new Intent(this,c6.class);
        startActivity(intent);
    }
    public void c7 (View view )
    {
        Intent intent = new Intent(this,c7.class);
        startActivity(intent);
    }

    public void c8 (View view )
    {
        Intent intent = new Intent(this,c8.class);
        startActivity(intent);
    }

    public void c9 (View view)
    {
        Intent intent = new Intent (this,c9.class);
        startActivity(intent);
    }

    public void m1 (View view)
    {
        Intent intent = new Intent (this,m1.class);
        startActivity(intent);
    }

    public void m2 (View view)
    {
        Intent intent = new Intent (this,m2.class);
        startActivity(intent);
    }
    public void m3 (View view)
    {
        Intent intent = new Intent (this,m3.class);
        startActivity(intent);
    }
    public void m4 (View view)
    {
        Intent intent = new Intent (this,m4.class);
        startActivity(intent);
    }
    public void m5 (View view)
    {
        Intent intent = new Intent (this,m5.class);
        startActivity(intent);
    }
    public void m6 (View view)
    {
        Intent intent = new Intent (this,m6.class);
        startActivity(intent);
    }
    public void m7 (View view)
    {
        Intent intent = new Intent (this,m7.class);
        startActivity(intent);
    }

    public void k1 (View view)
    {
        Intent intent = new Intent (this,k1.class);
        startActivity(intent);
    }

    public void k2 (View view)
    {
        Intent intent = new Intent (this,k2.class);
        startActivity(intent);
    }
    public void k3 (View view)
    {
        Intent intent = new Intent (this,k3.class);
        startActivity(intent);
    }
    public void k4 (View view)
    {
        Intent intent = new Intent (this,k4.class);
        startActivity(intent);
    }





}
