package com.asian5restaurant.lemongrass.Activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.asian5restaurant.lemongrass.Fragment.Curries_Fragment;
import com.asian5restaurant.lemongrass.Fragment.FromTheWok_Fragment;
import com.asian5restaurant.lemongrass.Fragment.RiceNNoodles_Fragment;
import com.asian5restaurant.lemongrass.Fragment.Salads_Fragment;
import com.asian5restaurant.lemongrass.Fragment.StartersNSoups_Fragment;
import com.asian5restaurant.lemongrass.R;
import com.asian5restaurant.lemongrass.Util.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by AMAL SAJU VARGHESE on 31-10-2016.
 */

public class Tab_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    ViewPagerAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.samplefoodhome);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_icon2));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sync_option, menu);
        return true;
    }
    public void doThis(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Tab_Activity.this);
        builder.setTitle("Warning!");
        builder.setMessage("App will erase previous datas and will be loaded with new data." +
                "Wish to continue");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                if(Utils.isNetworkAvailable(getApplicationContext()))
                {
                    Intent in = new Intent(getApplicationContext(),GetAll.class);
                    startActivity(in);
                }
                else {
                    Toast.makeText(getApplicationContext(),R.string.networkDown,Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),getApplicationContext());
        adapter.addFragment(new StartersNSoups_Fragment(), "APPETIZERS");
        adapter.addFragment(new Salads_Fragment(), "SOUPS");
        adapter.addFragment(new RiceNNoodles_Fragment(), "RICE & NOODLES");
        adapter.addFragment(new Curries_Fragment(), "MAIN COURSE");
        adapter.addFragment(new FromTheWok_Fragment(), "SANDWICHES");

        /*adapter.addFragment(new Deserts_Fragment(), "DESSERTS");
        adapter.addFragment(new MocktailNMoothies_Fragment(), "MOCKTAILS & SMOOTHIES");
        adapter.addFragment(new FreshJuiceNColdTeas_Fragment(), "FRESH JUICES & COLD TEAS");
        adapter.addFragment(new WaterNSodas(), "WATER & SODAS");
        adapter.addFragment(new TeaNCoffee_Fragment(), "TEAS & COFFEES");*/

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private Map<Integer,String>mFragmentTags;
        private FragmentManager manager;
        Context context ;

        public ViewPagerAdapter(FragmentManager manager,Context context) {
            super(manager);
            this.manager = manager;
            mFragmentTags = new HashMap<Integer, String>();
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
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

}



