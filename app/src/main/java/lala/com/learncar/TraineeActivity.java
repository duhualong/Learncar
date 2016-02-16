package lala.com.learncar;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TraineeActivity extends Activity{
    private LinearLayout home;
    private LinearLayout trainee;
    private LinearLayout my;
    private TextView tv_home;
    private ImageView iv_home;
    private ImageView iv_trainee;
    private TextView tv_trainee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee);
        initUI();
        setOnClickListener();
    }

    private void setOnClickListener() {
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TraineeActivity.this,MainActivity.class));
            }
        });
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TraineeActivity.this,MyActivity.class));

            }
        });

    }

    private void initUI() {

           home = (LinearLayout) findViewById(R.id.home);
            trainee = (LinearLayout) findViewById(R.id.trainee);
            my = (LinearLayout) findViewById(R.id.my);
        tv_home = (TextView) findViewById(R.id.tv_home);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_trainee = (ImageView) findViewById(R.id.iv_trainee);
        tv_trainee = (TextView) findViewById(R.id.tv_trainee);
      tv_home.setTextColor(ContextCompat.getColor(this, R.color.gray));
        iv_home.setImageResource(R.drawable.home_gray);
        tv_trainee.setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        iv_trainee.setImageResource(R.drawable.trainee_blue);
       // tv_home.setTextColor(0x000000);





    }
}
