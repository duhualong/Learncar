package lala.com.learncar.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import lala.com.learncar.R;
import lala.com.learncar.activity.AdviceFeedbackActivity;
import lala.com.learncar.activity.DetailActivity;
import lala.com.learncar.activity.MyWalletActivity;
import lala.com.learncar.activity.PasswordModifyActivity;
import lala.com.learncar.activity.ServiceRoadActivity;

public class MyFragment extends Fragment implements View.OnClickListener{

    private View view;
    private RelativeLayout rl_detail_my;
    private RelativeLayout rl_modify_password_my;
    private RelativeLayout rl_service_road;
    private RelativeLayout rl_wallet_my;
    private RelativeLayout rl_advice_feedback;
    private RelativeLayout rl_customer_care;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my,container,false);
        setInitUI();

        return view;
    }

    private void setViewListener() {
        rl_detail_my.setOnClickListener(this);
        rl_modify_password_my.setOnClickListener(this);
        rl_service_road.setOnClickListener(this);
        rl_wallet_my.setOnClickListener(this);
        rl_advice_feedback.setOnClickListener(this);
        rl_customer_care.setOnClickListener(this);


    }

    private void setInitUI() {
        rl_detail_my = (RelativeLayout) view.findViewById(R.id.rl_detail_my);
        rl_modify_password_my = (RelativeLayout) view.findViewById(R.id.rl_modify_password_my);
        rl_service_road = (RelativeLayout) view.findViewById(R.id.rl_service_road);
        rl_wallet_my = (RelativeLayout) view.findViewById(R.id.rl_wallet_my);
        rl_advice_feedback = (RelativeLayout) view.findViewById(R.id.rl_advice_feedback);
        rl_customer_care = (RelativeLayout) view.findViewById(R.id.rl_customer_care);
        setViewListener();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_detail_my:
                startActivity(new Intent(getActivity(), DetailActivity.class));
                break;
            case R.id.rl_modify_password_my:
                startActivity(new Intent(getActivity(), PasswordModifyActivity.class));
                break;
            case R.id.rl_service_road:
                startActivity(new Intent(getActivity(), ServiceRoadActivity.class));
                break;
            case R.id.rl_wallet_my:
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
                break;
            case R.id.rl_advice_feedback:
                startActivity(new Intent(getActivity(), AdviceFeedbackActivity.class));
                break;
            case R.id.rl_customer_care:
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setView(View.inflate(getActivity(),R.layout.dialog_modify_customer_care,null))
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + "18817772486");
                        intent.setData(data);
                        startActivity(intent);
                    }
                }).setCancelable(true);
                builder.create().show();
                break;

        }

    }
}
