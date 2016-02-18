package lala.com.learncar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import lala.com.learncar.R;

public class AdviceFeedbackActivity extends AppCompatActivity {

    private ImageView back_left_white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_feedback);
        initUI();
        setOnClickListener();
    }

    private void setOnClickListener() {
        back_left_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initUI() {
        back_left_white = (ImageView) findViewById(R.id.back_left_white);
    }
}
