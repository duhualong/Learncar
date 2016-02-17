package lala.com.learncar.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import lala.com.learncar.MyActivity;
import lala.com.learncar.R;

public class DetailActivity extends Activity {

    private ImageView back_left_white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        initUI();
    }

    private void initUI() {
        back_left_white = (ImageView) findViewById(R.id.back_left_white);
        back_left_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MyActivity.class));
            }
        });


    }
}
