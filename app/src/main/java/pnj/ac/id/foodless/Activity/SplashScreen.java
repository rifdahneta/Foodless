package pnj.ac.id.foodless.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import pnj.ac.id.foodless.R;

public class SplashScreen extends AppCompatActivity {

    ImageView imglogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imglogo = findViewById(R.id.imglogo);

        AlphaAnimation animation = new AlphaAnimation(0.4f, 1f);
        animation.setDuration(1500);
        animation.setFillAfter(true);
        imglogo.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(MainApp.sharedPreferences.getBoolean("isLogin",false)){
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);
                    finish();
                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
