package krunal.com.example.newsapp.Activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import krunal.com.example.newsapp.Fragment.BusinessNewsFragment;
import krunal.com.example.newsapp.Fragment.HealthNewsFragment;
import krunal.com.example.newsapp.Fragment.SportsNewsFragment;
import krunal.com.example.newsapp.Fragment.TechnologyNewsFragment;
import krunal.com.example.newsapp.Fragment.TopNewsFragment;
import krunal.com.example.newsapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TopNewsFragment mtopNewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mtopNewsFragment = new TopNewsFragment();
        displaySelectedFragment(mtopNewsFragment);
        getSupportActionBar().setTitle("Top News");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_topnews) {
            mtopNewsFragment = new TopNewsFragment();
            displaySelectedFragment(mtopNewsFragment);
            getSupportActionBar().setTitle("Top News");
           // getSupportFragmentManager()
            //        .beginTransaction()
            //        .replace(R.id.content_frame,blankFragment)
           //         .addToBackStack(null)
            //        .commit();

        } else if (id == R.id.nav_health) {
            HealthNewsFragment healthNewsFragment = new HealthNewsFragment();
            displaySelectedFragment(healthNewsFragment);
            getSupportActionBar().setTitle("Health News");

        } else if (id == R.id.nav_sports) {
            SportsNewsFragment sportsNewsFragment = new SportsNewsFragment();
            displaySelectedFragment(sportsNewsFragment);
            getSupportActionBar().setTitle("Sports News");

        } else if (id == R.id.nav_technology) {
            TechnologyNewsFragment technologyNewsFragment = new TechnologyNewsFragment();
            displaySelectedFragment(technologyNewsFragment);
            getSupportActionBar().setTitle("Technology News");

        } else if (id == R.id.nav_business) {
            BusinessNewsFragment businessNewsFragment = new BusinessNewsFragment();
            displaySelectedFragment(businessNewsFragment);
            getSupportActionBar().setTitle("Business News");

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Loads the specified fragment to the frame
     *
     * @param fragment
     */
    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }




}
