package lala.com.learncar.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import lala.com.learncar.R;

public class PasswordModifyActivity extends Activity{

    private ImageView back_left_white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_modify_my);
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
