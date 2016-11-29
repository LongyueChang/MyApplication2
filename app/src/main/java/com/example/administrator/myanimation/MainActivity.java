package com.example.administrator.myanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int[] res={R.id.img0,R.id.img1,R.id.img2,R.id.img3,R.id.img4,R.id.img5};
    private ImageView btStar;
    private boolean flag=true;
    private List<ImageView> data=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < res.length; i++) {
            ImageView imag = (ImageView) findViewById(res[i]);
            imag.setOnClickListener(this);
            data.add(imag);
        }

        btStar= (ImageView) findViewById(R.id.img);
        btStar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img:
                if(flag){
                    flag=false;
                    startAnim();
                }else {
                    flag=true;
                    closeAnim();
                }
                break;
                default:
                    Toast.makeText(this,"点击了"+v.getId(),Toast.LENGTH_SHORT).show();
                    break;
        }
    }

    private void closeAnim() {//关闭动画
        //1、计算出每个菜单的位置
        float angle= (float) (Math.PI/2/6);
        for (int i = 0; i < 6; i++) {
           float x = (float) (500 * Math.cos(angle*i));
            float y= -(float)(500*Math.sin(angle*i));
            Log.i("TAG", "----------->close的y:" +y);


            ObjectAnimator animator2=ObjectAnimator.ofFloat(data.get(i),"translationY",0,y);
            ObjectAnimator animator1=ObjectAnimator.ofFloat(data.get(i),"translationX",0,x);
            ObjectAnimator alpha=ObjectAnimator.ofFloat(data.get(i),"alpha",0f,1f);
            AnimatorSet set=new AnimatorSet();
            animator1.setInterpolator(new BounceInterpolator());
            animator2.setInterpolator(new BounceInterpolator());

            set.playTogether(animator1,animator2,alpha);
            set.setDuration(2000);
            set.start();
        }
    }

    private void startAnim() {//开启动画

        //1、计算出每个菜单的位置
        float angle= (float) (Math.PI/2/6);
        for (int i = 0; i < 6; i++) {
            data.get(i).setVisibility(View.VISIBLE);
            float x = (float)(500 * Math.cos(angle*i));
            float y= (float) -(500*Math.sin(angle*i));
            Log.i("TAG", "----------->start的y:" +angle*i);

            ObjectAnimator animator2=ObjectAnimator.ofFloat(data.get(i),"translationY",y,0);
            ObjectAnimator animator=ObjectAnimator.ofFloat(data.get(i),"translationX",x,0);

            ObjectAnimator alpha=ObjectAnimator.ofFloat(data.get(i),"alpha",1f,0f);

            AnimatorSet set=new AnimatorSet();
            animator.setInterpolator(new BounceInterpolator());
            animator2.setInterpolator(new BounceInterpolator());

            set.playTogether(animator,animator2,alpha);
            set.setDuration(2000);
            set.start();
        }
    }
}
