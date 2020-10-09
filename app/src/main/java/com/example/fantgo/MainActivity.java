package com.example.fantgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fantgo.fragment.AddItemFragment;
import com.example.fantgo.fragment.ItemsFragment;
import com.example.fantgo.fragment.LoginFragment;
import com.example.fantgo.fragment.RegisterFragment;
import com.example.fantgo.storage.UserPrefs;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Menu navMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navMenu = navigationView.getMenu();
        updateOnStartUp();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new LoginFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_login);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ItemsFragment()).commit();
                break;

            case R.id.nav_newitem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddItemFragment()).commit();
                break;

            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LoginFragment()).commit();
                break;

            case R.id.nav_register:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RegisterFragment()).commit();
                break;

            case R.id.nav_logout:
                UserPrefs userPrefs = new UserPrefs(this);
                userPrefs.setToken("");
                finish();
                startActivity(getIntent());
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void updateOnStartUp() {

        UserPrefs userPrefs = new UserPrefs(getApplicationContext());


        if (userPrefs.getToken().isEmpty()) {
            navMenu.findItem(R.id.nav_home).setVisible(true);
            navMenu.findItem(R.id.nav_login).setVisible(true);
            navMenu.findItem(R.id.nav_register).setVisible(true);
            navMenu.findItem(R.id.nav_newitem).setVisible(false);
            // navMenu.findItem(R.id.nav_logout).setVisible(false);
        } else {
            navMenu.findItem(R.id.nav_home).setVisible(true);
            navMenu.findItem(R.id.nav_login).setVisible(false);
            navMenu.findItem(R.id.nav_register).setVisible(false);
            navMenu.findItem(R.id.nav_newitem).setVisible(true);
            //  navMenu.findItem(R.id.nav_logout).setVisible(true);
        }

    }

}