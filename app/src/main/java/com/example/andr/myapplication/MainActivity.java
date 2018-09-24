package com.example.andr.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menu
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if(mToggle.onOptionsItemSelected(item)) {
                return true;
            }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem) {
        Fragment myfragment = null;
        Class myfragmentclass;

        switch(menuItem.getItemId()) {
            case R.id.da:
                myfragmentclass = area.class;
                break;
            case R.id.db:
                myfragmentclass = doacao.class;
                break;
            case R.id.dc:
                myfragmentclass = home.class;
                break;
            case R.id.dd:
                myfragmentclass = projeto.class;
                break;
            case R.id.de:
                myfragmentclass = entidade.class;
                break;
            case R.id.df:
                myfragmentclass = faq.class;
                break;
            case R.id.dg:
                myfragmentclass = contato.class;
                break;
            default:
                myfragmentclass = home.class;
        }

        try {
            myfragment = (Fragment) myfragmentclass.newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.f1content, myfragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerlayout.closeDrawers();

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

}
