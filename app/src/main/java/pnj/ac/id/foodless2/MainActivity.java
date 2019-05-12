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
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_out);

                switch(menuItem.getItemId()){
                    case R.id.btnHome:
                        loadHomeFragment(savedInstanceState);
                        transaction.show(homefrgament);
                        transaction.hide(foodfactfragment);
                        transaction.hide(historyfragment);
                        transaction.hide(profilefragment);
                        transaction.commit();
                        break;

                    case R.id.btnfoodfact:
                        loadFoodFactFragment(savedInstanceState);
                        transaction.hide(homefrgament);
                        transaction.show(foodfactfragment);
                        transaction.hide(historyfragment);
                        transaction.hide(profilefragment);
                        transaction.commit();
                        break;

                    case R.id.btnhistory:
                        loadHistoryFragment(savedInstanceState);
                        transaction.hide(homefrgament);
                        transaction.hide(foodfactfragment);
                        transaction.show(historyfragment);
                        transaction.hide(profilefragment);
                        transaction.commit();
                        break;

                    case R.id.btnfprofile:
                        loadProfileFragmnet(savedInstanceState);
                        transaction.hide(homefrgament);
                        transaction.hide(foodfactfragment);
                        transaction.hide(historyfragment);
                        transaction.show(profilefragment);
                        transaction.commit();
                        break;

                }
                return true;
            }
        });

    }


    private void loadHomeFragment( Bundle savedInstanceState){
        if(savedInstanceState == null) {
            this.getSupportFragmentManager().beginTransaction().replace((R.id.main_frame),
                    (Fragment) (new HomeFragment()), HomeFragment.class.getSimpleName()).commit();
        }
    }

    private void loadHistoryFragment( Bundle savedInstanceState){

        if(savedInstanceState == null) {
            this.getSupportFragmentManager().beginTransaction().replace((R.id.main_frame),
                    (Fragment) (new HistoryFragment()), HistoryFragment.class.getSimpleName()).commit();
        }
    }

    private void loadFoodFactFragment( Bundle savedInstanceState){
        if(savedInstanceState == null) {
            this.getSupportFragmentManager().beginTransaction().replace((R.id.main_frame),
                    (Fragment) (new FoodfactFragment()), FoodfactFragment.class.getSimpleName()).commit();
        }
    }

    private void loadProfileFragmnet( Bundle savedInstanceState){
        if(savedInstanceState == null) {
            this.getSupportFragmentManager().beginTransaction().replace((R.id.main_frame),
                    (Fragment) (new ProfileFragment()), ProfileFragment.class.getSimpleName()).commit();
        }
    }
    private void setFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}
