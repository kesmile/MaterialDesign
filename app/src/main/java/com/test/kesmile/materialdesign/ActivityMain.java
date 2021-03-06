package com.test.kesmile.materialdesign;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.test.kesmile.materialdesign.adapters.PagerAdapter;
import com.test.kesmile.materialdesign.fragments.Bluetooth;
import com.test.kesmile.materialdesign.fragments.FragmentMainTest;

import java.util.ArrayList;
import java.util.List;


public class ActivityMain extends ActionBarActivity {
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_test);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
      //  setSupportActionBar(toolbar);

        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable( getResources().getDrawable(android.R.drawable.ic_dialog_alert));
        icon.setBackgroundColor( getResources().getColor(R.color.color_primary_dark) );
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable( getResources().getDrawable(android.R.drawable.ic_dialog_alert));
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();


        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .attachTo(actionButton)
                .build();


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),fragments);
        if (savedInstanceState == null) {
            fragments.add(new FragmentMainTest());
            fragments.add(new Bluetooth());
        }else{
            FragmentManager fragManager = getSupportFragmentManager();
            fragments.add(fragManager.findFragmentByTag(adapter.getFragmentTag(0)));
            fragments.add(fragManager.findFragmentByTag(adapter.getFragmentTag(1)));
        }
        adapter.notifyDataSetChanged();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);

        PagerTabStrip strip = (PagerTabStrip) findViewById(R.id.pager_title_strip);
        strip.setDrawFullUnderline(true);

        strip.setTabIndicatorColor(getResources().getColor(R.color.color_primary_dark));
        strip.setTextColor(getResources().getColor(R.color.color_primary_dark));
        strip.setNonPrimaryAlpha(0.5f);
        strip.setTextSpacing(25);
        strip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
