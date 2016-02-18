package lala.com.learncar.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import lala.com.learncar.R;
import lala.com.learncar.util.PhotoUtil;

public class DetailActivity extends Activity {

    private ImageView back_left_white;
    private RelativeLayout rl_name_detail;
    private AlertDialog dialog;
    private TextView name_register_detail;

    private String names;
    private RelativeLayout rl_sex_detail;
    private TextView sex_register_detail;
    private RelativeLayout rl_edit_head_photo;
    private ImageView head_portrait;
    private RelativeLayout rl_drive_age_detail;
    private TextView drive_register_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        initUI();
        setOnClickListener();
    }

    private void setOnClickListener() {
        back_left_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回键，不能用跳转
                onBackPressed();
                //     startActivity(new Intent(DetailActivity.this, MyActivity.class));
            }
        });
        rl_edit_head_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhotoHeadFindDialog();
            }
        });
        rl_name_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   NameDialog nameDialog = new NameDialog();
                showNameFindDialog();


            }
        });
        rl_sex_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexFindDialog();
            }
        });
        rl_drive_age_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDriveAgeFindDialog();
            }
        });


    }

    private void showDriveAgeFindDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(DetailActivity.this);
       final View view= View.inflate(DetailActivity.this, R.layout.dialog_drive_age, null);
        builder.setView(view).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et_drive_age = (EditText) view.findViewById(R.id.et_drive_age);
                String drive_age = et_drive_age.getText().toString().trim();
                if (TextUtils.isEmpty(drive_age)) {
                    Toast.makeText(DetailActivity.this, "驾龄不能为空！", Toast.LENGTH_SHORT).show();

                } else {
                    names=drive_age;
                    drive_register_detail.setText(names);
                    Toast.makeText(DetailActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();


                }
                dialog.dismiss();

            }
        }).setCancelable(true);
        builder.create().show();
    }

    private void showPhotoHeadFindDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(DetailActivity.this);
       builder.setView(View.inflate(DetailActivity.this, R.layout.dialog_modify_photo_head, null)).
               setPositiveButton("相册", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       PhotoUtil.startGallery(DetailActivity.this);

                   }
               }).setNegativeButton("相机", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               PhotoUtil.startCamera(DetailActivity.this);

           }
       }).setCancelable(true);
        builder.create().show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri uri;
            switch (requestCode){
                case PhotoUtil.REQUEST_CAMERA:
                    System.out.println("!!!!!!---!!!!!");
                    uri = Uri.fromFile(PhotoUtil.getTempFile());
                    PhotoUtil.beginCrop(DetailActivity.this,uri);
                    break;
                case PhotoUtil.REQUEST_GALLERY:
                    System.out.println("!????---!!!???!!");
                    if (data != null) {
                        uri = data.getData();
                        PhotoUtil.beginCrop(DetailActivity.this, uri);

                    }
                    break;
                case PhotoUtil.REQUEST_CROP:
                    if (data != null) {
                        uri = Crop.getOutput(data); // 裁剪后图片Uri
                        System.out.println("image uri: " + uri);

                        // 展示图片
//                        Bitmap bm = ImageUtil.getSmallBitmap(PhotoUtil.getRealFilePath(context, uri));
//                        avatar.setImageBitmap(bm);
                        head_portrait.setImageURI(uri);
                        Toast.makeText(DetailActivity.this, "上传头像成功！", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    private void showSexFindDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(DetailActivity.this);
        View view=View.inflate(DetailActivity.this,R.layout.dialog_modify_sex,null);
        final RadioGroup rg_select_sex= (RadioGroup) view.findViewById(R.id.rg_select_sex);
        Button ok_bt = (Button) view.findViewById(R.id.ok_bt);
        Button cancel_bt = (Button) view.findViewById(R.id.cancel_bt);
        ok_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int select=rg_select_sex.getCheckedRadioButtonId();
                if(select==R.id.man_sex){
                    sex_register_detail.setText("男");
                   Toast.makeText(DetailActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                }else {
                    sex_register_detail.setText("女");
                    Toast.makeText(DetailActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        cancel_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog=builder.create();
        dialog.setView(view,0,0,0,30);
        dialog.show();


    }

    private void showNameFindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        View view = View.inflate(DetailActivity.this, R.layout.dialog_modify_name, null);
        final EditText et_name_dialog = (EditText) view.findViewById(R.id.et_name_dialog);
        Button ok_bt = (Button) view.findViewById(R.id.ok_bt);
        Button cancel_bt = (Button) view.findViewById(R.id.cancel_bt);
        cancel_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok_bt.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View v) {
             String name = et_name_dialog.getText().toString().trim();
             if (TextUtils.isEmpty(name)){
                 Toast.makeText(DetailActivity.this,"姓名不能为空",Toast.LENGTH_SHORT).show();

             }else {
                 names=name;
                 name_register_detail.setText(names);
                 Toast.makeText(DetailActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
             }

             dialog.dismiss();
           }
         });
        dialog=builder.create();
        dialog.setView(view,0,0,0,30);
        dialog.show();



    }

    private void initUI() {
        back_left_white = (ImageView) findViewById(R.id.back_left_white);
        rl_name_detail = (RelativeLayout) findViewById(R.id.rl_name_detail);
        name_register_detail = (TextView) findViewById(R.id.name_register_detail);
        rl_sex_detail = (RelativeLayout) findViewById(R.id.rl_sex_detail);
        sex_register_detail = (TextView) findViewById(R.id.sex_register_detail);
        rl_edit_head_photo = (RelativeLayout) findViewById(R.id.rl_edit_head_photo);
        head_portrait = (ImageView) findViewById(R.id.head_portrait);
        rl_drive_age_detail = (RelativeLayout) findViewById(R.id.rl_drive_age_detail);
        drive_register_detail = (TextView) findViewById(R.id.drive_register_detail);


    }
}
