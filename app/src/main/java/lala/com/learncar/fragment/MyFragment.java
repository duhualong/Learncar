package lala.com.learncar.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my,container,false);
        setInitUI();
        setViewListener();
        return view;
    }

    private void setViewListener() {
        rl_detail_my.setOnClickListener(this);
    }

    private void setInitUI() {
        rl_detail_my = (RelativeLayout) view.findViewById(R.id.rl_detail_my);
        rl_modify_password_my = (RelativeLayout) view.findViewById(R.id.rl_modify_password_my);
        rl_service_road = (RelativeLayout) view.findViewById(R.id.rl_service_road);
        rl_wallet_my = (RelativeLayout) view.findViewById(R.id.rl_wallet_my);
        rl_advice_feedback = (RelativeLayout) view.findViewById(R.id.rl_advice_feedback);
        rl_customer_care = (RelativeLayout) view.findViewById(R.id.rl_customer_care);


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
        }

    }
}
