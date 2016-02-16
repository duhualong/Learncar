package lala.com.learncar;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyActivity extends Activity{
    private LinearLayout home;
    private LinearLayout trainee;
    private LinearLayout my;
    private TextView tv_home;
    private ImageView iv_home;
    private TextView tv_my;
    private ImageView iv_my;

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
        RelativeLayout rl_detail_my= (RelativeLayout) findViewById(R.id.rl_detail_my);
    }
}
