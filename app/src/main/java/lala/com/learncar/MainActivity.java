package lala.com.learncar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    int[] bottom_grey={R.drawable.home_gray,R.drawable.trainee_gray,R.drawable.my_gray};
    int[] bottom_blue={R.drawable.home_blue,R.drawable.trainee_blue,R.drawable.my_blue};
    private LinearLayout home;
    private LinearLayout trainee;
    private LinearLayout my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setOnClickListener();
    }

    private void setOnClickListener() {
        trainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TraineeActivity.class));
            }
        });
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MyActivity.class));

            }
        });


    }

    private void initUI() {
        home = (LinearLayout) findViewById(R.id.home);
        trainee = (LinearLayout) findViewById(R.id.trainee);
        my = (LinearLayout) findViewById(R.id.my);

    }
}
