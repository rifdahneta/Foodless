package pnj.ac.id.foodless.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import pnj.ac.id.foodless.Fragment.FoodfactFragment;
import pnj.ac.id.foodless.Fragment.HistoryFragment;
import pnj.ac.id.foodless.Fragment.HomeFragment;
import pnj.ac.id.foodless.Fragment.ProfileFragment;
import pnj.ac.id.foodless.R;

public class MainActivity extends AppCompatActivity {

//    private static final String TAG = "HomeFragment";
//    private ArrayList<Communities> mKomunitas = new ArrayList<>();
//    private ArrayList<String> ImgList = new ArrayList<>();


    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private HomeFragment homefragment;
    private FoodfactFragment foodfactfragment;
    private HistoryFragment historyfragment;
    private ProfileFragment profilefragment;
    private boolean navItemSelected= false;
    private int checkedItem = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainNav = findViewById(R.id.main_nav);
        mainFrame = findViewById(R.id.main_frame);

        homefragment = new HomeFragment();
        foodfactfragment = new FoodfactFragment();
        historyfragment = new HistoryFragment();
        profilefragment = new ProfileFragment();

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.btnHome:
                        setFragment(homefragment);
                        break;

                    case R.id.btnfoodfact:
                        setFragment(foodfactfragment);
                        break;

                    case R.id.btnhistory:
                        setFragment(historyfragment);
                        break;

                    case R.id.btnfprofile:
                        setFragment(profilefragment);
                        break;

                }
                return true;
            }
        });

        mainNav.setSelectedItemId(R.id.btnHome);
//        RecyclerCardCommunities();
    }

    private void setFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}

