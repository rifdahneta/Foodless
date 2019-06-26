package pnj.ac.id.foodless.Activity;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


import pnj.ac.id.foodless.Fragment.HistoryFragment;

import pnj.ac.id.foodless.Fragment.HistoryKomunitasFragment;

import android.view.MenuItem;
import android.widget.FrameLayout;

import pnj.ac.id.foodless.Fragment.FoodfactFragment;
import pnj.ac.id.foodless.Fragment.HistoryFragment;
import pnj.ac.id.foodless.Fragment.HistoryKomunitasFragment;
import pnj.ac.id.foodless.Fragment.HomeFragment;
import pnj.ac.id.foodless.Fragment.HomeKomunitasFragment;
import pnj.ac.id.foodless.Fragment.ProfileFragment;
import pnj.ac.id.foodless.Fragment.ProfileKomunitasFragment;
import pnj.ac.id.foodless.R;

public class MainActivityKomunitas extends AppCompatActivity {

    private BottomNavigationView mainNavKomunitas;
    private FrameLayout mainFrameKomunitas;
    private HomeKomunitasFragment HomeFragmentKomunitas;
    private HistoryKomunitasFragment HistoryKomunitasFragment;
    private ProfileKomunitasFragment ProfileKomunitasFragment;
    private boolean navItemSelected= false;
    private int checkedItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_main_komunitas);

        mainNavKomunitas = findViewById(R.id.main_nav_komunitas);
        mainFrameKomunitas = findViewById(R.id.main_frame_komunitas);

        HomeFragmentKomunitas = new HomeKomunitasFragment();
        HistoryKomunitasFragment = new HistoryKomunitasFragment();
        ProfileKomunitasFragment = new ProfileKomunitasFragment();

        mainNavKomunitas.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.btnHomeKomunitas:
                        setFragment(HomeFragmentKomunitas);
                        break;

                    case R.id.btnhistoryKomunitas:
                        setFragment(HistoryKomunitasFragment);
                        break;

                    case R.id.btnfprofileKomunitas:
                        setFragment(ProfileKomunitasFragment);
                        break;

                }
                return true;
            }
        });

        mainNavKomunitas.setSelectedItemId(R.id.btnHomeKomunitas);
//        RecyclerCardCommunities();
    }

    private void setFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_komunitas, fragment);
        fragmentTransaction.commit();
    }
}
