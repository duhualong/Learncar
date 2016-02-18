package lala.com.learncar;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lala.com.learncar.activity.AdviceFeedbackActivity;
import lala.com.learncar.activity.DetailActivity;
import lala.com.learncar.activity.MyWalletActivity;
import lala.com.learncar.activity.PasswordModifyActivity;
import lala.com.learncar.activity.ServiceRoadActivity;

public class MyActivity extends Activity{
    private LinearLayout home;
    private LinearLayout trainee;
    private LinearLayout my;
    private TextView tv_home;
    private ImageView iv_home;
    private TextView tv_my;
    private ImageView iv_my;
    private RelativeLayout rl_detail_my;
    private RelativeLayout rl_modify_password_my;
    private RelativeLayout rl_service_road;
    private RelativeLayout rl_wallet_my;
    private RelativeLayout rl_advice_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initUI();
        setOnClickListener();
    }

    private void setOnClickListener() {
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this,MainActivity.class));
            }
        });
        trainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this,TraineeActivity.class));

            }
        });
        rl_detail_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this, DetailActivity.class));

            }
        });
        rl_modify_password_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this, PasswordModifyActivity.class));
            }
        });
        rl_service_road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this, ServiceRoadActivity.class));
            }
        });
        rl_wallet_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this, MyWalletActivity.class));
            }
        });
        rl_advice_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this, AdviceFeedbackActivity.class));
            }
        });
    }

    private void initUI() {
        home = (LinearLayout) findViewById(R.id.home);
        trainee = (LinearLayout) findViewById(R.id.trainee);
        my = (LinearLayout) findViewById(R.id.my);
        tv_home = (TextView) findViewById(R.id.tv_home);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        tv_my = (TextView) findViewById(R.id.tv_my);
        iv_my = (ImageView) findViewById(R.id.iv_my);
        tv_home.setTextColor(ContextCompat.getColor(this, R.color.gray));
        iv_home.setImageResource(R.drawable.home_gray);
        tv_my.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        iv_my.setImageResource(R.drawable.my_blue);
        rl_detail_my = (RelativeLayout) findViewById(R.id.rl_detail_my);
        rl_modify_password_my = (RelativeLayout) findViewById(R.id.rl_modify_password_my);
        rl_service_road = (RelativeLayout) findViewById(R.id.rl_service_road);
        rl_wallet_my = (RelativeLayout) findViewById(R.id.rl_wallet_my);
        rl_advice_feedback = (RelativeLayout) findViewById(R.id.rl_advice_feedback);
        findViewById(R.id.rl_customer_care).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
                builder.setIcon(R.drawable.logo)
                        .setTitle(R.string.app_name)
                        .setMessage("是否拨打电话")
                        .setPositiveButton("确定", null)
                        .setNegativeButton("取消", null)
                        .show();
            }
        });

    }
}
