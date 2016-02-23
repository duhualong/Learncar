package lala.com.learncar.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lala.com.learncar.R;

public class ModifyCarLicense extends Activity implements View.OnClickListener{

    private ImageView back_left_white;
    private TextView addCarLicense;
    private ListView listView;
    private List<String> list;
    private MyAdapter adapter;
    private TextView finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_car_license);
        setInitUI();
    }

    private void setInitUI() {
        back_left_white = (ImageView) findViewById(R.id.back_left_white);
        addCarLicense = (TextView) findViewById(R.id.addCarLicense);
        listView = (ListView) findViewById(R.id.listView);
        finish = (TextView) findViewById(R.id.finish);
        list=new ArrayList<>();
       //list.add("");
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
              //  ListView listView = (ListView) parent;

                AlertDialog.Builder builder=new AlertDialog.Builder(ModifyCarLicense.this);
                view =View.inflate(ModifyCarLicense.this,R.layout.dialog_modify_car_license_detail,null);
                final EditText modifyCarLicense= (EditText) view.findViewById(R.id.modifyCarLicense);
                modifyCarLicense.setText(list.get(position));
                builder.setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content=modifyCarLicense.getText().toString().trim();
                        if (TextUtils.isEmpty(content)){

                                System.out.println("11111_____1111111");
                                list.remove(position);
                                adapter.notifyDataSetChanged();

                        }else {
                          View v= parent.getChildAt(position);

                            adapter.notifyDataSetChanged();
                        }


                    }
                }).setCancelable(true);
                builder.create().show();

            }
        });

        setViewListener();
    }

    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=null;
            ViewHolder holder = null;
            if (convertView==null) {
                holder=new ViewHolder();
                view = View.inflate(ModifyCarLicense.this, R.layout.list_item, null);
                holder.textViewItem= (TextView) view.findViewById(R.id.textViewItem);
                view.setTag(holder);

            }else{
                view=convertView;
                holder= (ViewHolder) view.getTag();
            }
            holder.textViewItem.setText(list.get(position));
            return view;
        }
    }
    static class ViewHolder
    {
        public TextView textViewItem;
    }

    private void setViewListener() {
        back_left_white.setOnClickListener(this);
        addCarLicense.setOnClickListener(this);
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_left_white:
                onBackPressed();
                break;
            case R.id.addCarLicense:
                AlertDialog.Builder builder=new AlertDialog.Builder(ModifyCarLicense.this);
                View view=View.inflate(ModifyCarLicense.this,R.layout.dialog_modify_car_license,null);
               final EditText modifyCarLicense= (EditText) view.findViewById(R.id.modifyCarLicense);

                builder.setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String carLicense= modifyCarLicense.getText().toString().trim();
                        if (TextUtils.isEmpty(carLicense)){
                            Toast.makeText(ModifyCarLicense.this, "车牌不能为空！", Toast.LENGTH_LONG).show();

                        }else{
                            list.add(carLicense);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }).setCancelable(true);
                builder.create().show();
                break;
            case R.id.finish:
                onBackPressed();
                break;

        }
    }
}
