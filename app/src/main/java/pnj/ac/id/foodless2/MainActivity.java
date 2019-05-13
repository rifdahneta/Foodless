package pnj.ac.id.foodless2;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private HomeFragment homefrgament;
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

        homefrgament = new HomeFragment();
        foodfactfragment = new FoodfactFragment();
        historyfragment = new HistoryFragment();
        profilefragment = new ProfileFragment();

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.btnHome:
                        setFragment(homefrgament);
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

    }

    private void setFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}
