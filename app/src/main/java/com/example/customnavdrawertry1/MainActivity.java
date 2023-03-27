package com.example.customnavdrawertry1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    private TextView dragMe;
    float xDown = 0, yDown = 0;
    private FrameLayout frame_container;
    CollapsingToolbarLayout collapsing_toolbar;
    View myLayout;
    View myView ;
    ImageView navigation_drawer;
    AppBarLayout appBarLayout;
    ImageButton back_button;
    Dialog background_choose_dialogue;
    RelativeLayout choose_color_dialogue;
    CardView orange_card,red_card,purple_card,grey_card;
Animation rotation;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //frame_container = findViewById(R.id.frame_container);
       // frame_container.setOnTouchListener(new OnTouchEvent());
       // myLayout = findViewById( R.id.include_layout );
        //myView = myLayout.findViewById( R.id.someinnerview );
        back_button=findViewById(R.id.back_button);
        appBarLayout=findViewById(R.id.app_navigation_appbarlayout);
        navigation_drawer=findViewById(R.id.navigation_drawer);
        choose_color_dialogue=findViewById(R.id.choose_color_dialogue);


        background_choose_dialogue =new Dialog(this);
        background_choose_dialogue .setContentView(R.layout.custom_dialogue_choose_color);
        background_choose_dialogue .getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_box));
        background_choose_dialogue .getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        background_choose_dialogue .setCancelable(true);

        collapsing_toolbar=findViewById(R.id.navigation_collapsing_toolbar);

        orange_card=background_choose_dialogue.findViewById(R.id.orange_background);
        red_card=background_choose_dialogue.findViewById(R.id.red_background);
        purple_card=background_choose_dialogue.findViewById(R.id.purple_background);
        grey_card=background_choose_dialogue.findViewById(R.id.grey_background);

        Resources res = this.getResources();
        grey_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapsing_toolbar.setBackgroundDrawable(res.getDrawable(R.drawable.greybackground));
            }
        });
        purple_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapsing_toolbar.setBackgroundDrawable(res.getDrawable(R.drawable.purplebackground));
            }
        });
        red_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapsing_toolbar.setBackgroundDrawable(res.getDrawable(R.drawable.redbackground));
            }
        });

        orange_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapsing_toolbar.setBackgroundDrawable(res.getDrawable(R.drawable.orangebackground));
            }
        });
        rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_down);
        rotation.setFillAfter(true);

        navigation_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appBarLayout.setVisibility(View.VISIBLE);
                appBarLayout.setExpanded(true,true);
                navigation_drawer.startAnimation(rotation);
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // appBarLayout.setVisibility(View.GONE);
               appBarLayout.setExpanded(false,true);
                navigation_drawer.startAnimation(rotation);
            }
        });
        choose_color_dialogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background_choose_dialogue.show();
            }
        });
    }
    private class OnTouchEvent implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Log.d(TAG, "onTouch: MotionEvent.ACTION_DOWN" );
                    Log.d(TAG, "onTouch: ACTION_DOWN X "
                            + frame_container.getX());
                    Log.d(TAG, "onTouch: ACTION_DOWN Y "
                            + frame_container.getY());
                    xDown = motionEvent.getX();
                    yDown = motionEvent.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d(TAG, "onTouch: MotionEvent.ACTION_UP" );
                    Log.d(TAG, "onTouch: ACTION_UP X "
                            + frame_container.getX());
                    Log.d(TAG, "onTouch: ACTION_UP Y "
                            + frame_container.getY());
                    break;

            }
            return true;
        }
    }
}