package lala.com.learncar.activity;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lala.com.learncar.R;
import lala.com.learncar.fragment.HomeFragment;
import lala.com.learncar.fragment.MyFragment;
import lala.com.learncar.fragment.TraineeFragment;

public class ContainerActivity extends Activity implements View.OnClickListener {

    private LinearLayout home;
    private LinearLayout trainee;
    private LinearLayout my;
    private Fragment fragment;
    private TextView tv_home;
    private ImageView iv_home;
    private TextView tv_my;
    private ImageView iv_my;
    private TextView tv_trainee;
    private ImageView iv_trainee;
    private TextView fragment_title_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        setInitUI();
        if (fragment ==null){
            fragment =new HomeFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();

        }

        setViewListener();

    }

    private void setInitUI() {
        home = (LinearLayout) findViewById(R.id.home);
        trainee = (LinearLayout) findViewById(R.id.trainee);
        my = (LinearLayout) findViewById(R.id.my);
        fragment = getFragmentManager().findFragmentById(R.id.fragment_container);
        tv_home = (TextView) findViewById(R.id.tv_home);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        tv_trainee = (TextView) findViewById(R.id.tv_trainee);
        iv_trainee = (ImageView) findViewById(R.id.iv_trainee);
        tv_my = (TextView) findViewById(R.id.tv_my);
        iv_my = (ImageView) findViewById(R.id.iv_my);
        fragment_title_content = (TextView) findViewById(R.id.fragment_title_content);

    }

    private void setViewListener() {
        home.setOnClickListener(this);
        trainee.setOnClickListener(this);
        my.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                fragment_title_content.setText("五一学车");
                tv_trainee.setTextColor(ContextCompat.getColor(this, R.color.gray));
                iv_trainee.setImageResource(R.drawable.trainee_gray);
                tv_my.setTextColor(ContextCompat.getColor(this, R.color.gray));
                iv_my.setImageResource(R.drawable.my_gray);
                tv_home.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
                iv_home.setImageResource(R.drawable.home_blue);
                fragment=new HomeFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.trainee:
                fragment_title_content.setText("我的学员");
                tv_home.setTextColor(ContextCompat.getColor(this, R.color.gray));
                iv_home.setImageResource(R.drawable.home_gray);
                tv_my.setTextColor(ContextCompat.getColor(this, R.color.gray));
                iv_my.setImageResource(R.drawable.my_gray);
                tv_trainee.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
                iv_trainee.setImageResource(R.drawable.trainee_blue);
                fragment=new TraineeFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.my:
                fragment_title_content.setText("我的");
                tv_home.setTextColor(ContextCompat.getColor(this, R.color.gray));
                iv_home.setImageResource(R.drawable.home_gray);
                tv_trainee.setTextColor(ContextCompat.getColor(this, R.color.gray));
                iv_trainee.setImageResource(R.drawable.trainee_gray);
                tv_my.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
                iv_my.setImageResource(R.drawable.my_blue);
                fragment=new MyFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;

        }

    }
}
