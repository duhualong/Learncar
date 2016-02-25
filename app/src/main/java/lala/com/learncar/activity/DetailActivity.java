package lala.com.learncar.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.util.Set;

import lala.com.learncar.R;
import lala.com.learncar.util.PhotoUtil;

public class DetailActivity extends Activity implements View.OnClickListener {

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
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private RelativeLayout rl_birth_calendar_detail;
    private TextView birth_date_register_detail;
    private RelativeLayout rl_height_weight_detail;
    private TextView height_weight_register_detail;
    private RelativeLayout rl_ancestral_homeland_detail;
    private TextView ancestral_homeland_register_detail;
    private RelativeLayout rl_introduction_detail;
    private TextView introduction_register_detail;
    private RelativeLayout rl_driving_license_detail;
    private RelativeLayout rl_teacher_certificate_detail;
    private RelativeLayout rl_car_license_detail;
    private RelativeLayout rl_my_photo_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        setInitUI();

    }

    private void setViewListener() {
        back_left_white.setOnClickListener(this);
        rl_edit_head_photo.setOnClickListener(this);
        rl_name_detail.setOnClickListener(this);
        rl_sex_detail.setOnClickListener(this);
        rl_drive_age_detail.setOnClickListener(this);
        rl_birth_calendar_detail.setOnClickListener(this);
        rl_height_weight_detail.setOnClickListener(this);
        rl_ancestral_homeland_detail.setOnClickListener(this);
        rl_introduction_detail.setOnClickListener(this);
        rl_driving_license_detail.setOnClickListener(this);
        rl_teacher_certificate_detail.setOnClickListener(this);
        rl_car_license_detail.setOnClickListener(this);
        rl_my_photo_detail.setOnClickListener(this);
    }

    private void showHeightWeightFindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        View view = View.inflate(DetailActivity.this, R.layout.dialog_modify_weight_height, null);
        final EditText et_height_number = (EditText) view.findViewById(R.id.et_height_number);
        final EditText et_weight_number = (EditText) view.findViewById(R.id.et_weight_number);
        String savedWeight = sharedPreferences.getString("drive_weight", "");
        String savedHeight = sharedPreferences.getString("drive_height", "");

        if (!TextUtils.isEmpty(savedWeight)) {
            et_weight_number.setText(savedWeight);
        }
        if (!TextUtils.isEmpty(savedHeight)) {
            et_height_number.setText(savedHeight);
        }
        builder.setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String height = et_height_number.getText().toString().trim();
                String weight = et_weight_number.getText().toString().trim();

                String weightHeight = height + " cm" + " | " + weight + " kg";

                if (TextUtils.isEmpty(height) || TextUtils.isEmpty(weight)) {
                    Toast.makeText(DetailActivity.this, "身高或体重不能为空！", Toast.LENGTH_SHORT).show();

                } else if (!TextUtils.isEmpty(weight) && !TextUtils.isEmpty(height)) {

                    names = weightHeight;
                    height_weight_register_detail.setText(names);


                }

                editor.putString("drive_weight", weight);
                editor.putString("drive_height", height);
                editor.apply();

            }
        }).setCancelable(true);
        builder.create().show();
    }

    private void showCalenderFindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        View view = View.inflate(DetailActivity.this, R.layout.dialog_drive_calendar, null);
        final DatePicker dialogCalendar = (DatePicker) view.findViewById(R.id.dialogCalendar);
        builder.setView(view).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String date = dialogCalendar.getYear() + "-" + (dialogCalendar.getMonth() + 1) + "-" + dialogCalendar.getDayOfMonth();
                birth_date_register_detail.setText(date);

            }
        }).setCancelable(true);
        builder.create().show();
    }

    private void showDriveAgeFindDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        final View view = View.inflate(DetailActivity.this, R.layout.dialog_drive_age, null);
        EditText et_drive_age = (EditText) view.findViewById(R.id.et_drive_age);
        String driveAge = sharedPreferences.getString("drive_age", "");
        if (!TextUtils.isEmpty(driveAge)) {
            et_drive_age.setText(driveAge);
        }
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
                    names = drive_age;
                    drive_register_detail.setText(names);
                    editor.putString("drive_age", names);
                    editor.apply();
                    Toast.makeText(DetailActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();


                }
                dialog.dismiss();

            }
        }).setCancelable(true);
        builder.create().show();
    }

    private void showPhotoHeadFindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
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
        if (resultCode == RESULT_OK) {
            Uri uri;
            switch (requestCode) {
                case PhotoUtil.REQUEST_CAMERA:
                    System.out.println("!!!!!!---!!!!!");
                    uri = Uri.fromFile(PhotoUtil.getTempFile());
                    PhotoUtil.beginCrop(DetailActivity.this, uri);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        View view = View.inflate(DetailActivity.this, R.layout.dialog_modify_sex, null);

        final RadioGroup rg_select_sex = (RadioGroup) view.findViewById(R.id.rg_select_sex);
        RadioButton man_sex = (RadioButton) view.findViewById(R.id.man_sex);
        RadioButton woman_sex = (RadioButton) view.findViewById(R.id.woman_sex);
        int savedSexId = sharedPreferences.getInt("sex_id", 0);
        if (man_sex.getId() == savedSexId) {
            man_sex.setChecked(true);
        } else if (woman_sex.getId() == savedSexId) {
            woman_sex.setChecked(true);
        }
        builder.setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取性别的id
                int select = rg_select_sex.getCheckedRadioButtonId();
                if (select == R.id.man_sex) {
                    sex_register_detail.setText("男");
                    Toast.makeText(DetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    sex_register_detail.setText("女");
                    Toast.makeText(DetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
                editor.putInt("sex_id", select);
                editor.apply();
                dialog.dismiss();

            }
        }).setCancelable(true);
        builder.create().show();
    }


    private void setInitUI() {
        back_left_white = (ImageView) findViewById(R.id.back_left_white);
        rl_name_detail = (RelativeLayout) findViewById(R.id.rl_name_detail);
        name_register_detail = (TextView) findViewById(R.id.name_register_detail);
        rl_sex_detail = (RelativeLayout) findViewById(R.id.rl_sex_detail);
        sex_register_detail = (TextView) findViewById(R.id.sex_register_detail);
        rl_edit_head_photo = (RelativeLayout) findViewById(R.id.rl_edit_head_photo);
        head_portrait = (ImageView) findViewById(R.id.head_portrait);
        rl_drive_age_detail = (RelativeLayout) findViewById(R.id.rl_drive_age_detail);
        drive_register_detail = (TextView) findViewById(R.id.drive_register_detail);
        rl_birth_calendar_detail = (RelativeLayout) findViewById(R.id.rl_birth_calendar_detail);
        birth_date_register_detail = (TextView) findViewById(R.id.birth_date_register_detail);
        rl_height_weight_detail = (RelativeLayout) findViewById(R.id.rl_height_weight_detail);
        height_weight_register_detail = (TextView) findViewById(R.id.height_weight_register_detail);
        rl_ancestral_homeland_detail = (RelativeLayout) findViewById(R.id.rl_ancestral_homeland_detail);
        ancestral_homeland_register_detail = (TextView) findViewById(R.id.ancestral_homeland_register_detail);
        rl_introduction_detail = (RelativeLayout) findViewById(R.id.rl_introduction_detail);
        introduction_register_detail = (TextView) findViewById(R.id.introduction_register_detail);
        rl_driving_license_detail = (RelativeLayout) findViewById(R.id.rl_driving_license_detail);
        rl_teacher_certificate_detail = (RelativeLayout) findViewById(R.id.rl_teacher_certificate_detail);
        rl_car_license_detail = (RelativeLayout) findViewById(R.id.rl_car_license_detail);
        sharedPreferences = getSharedPreferences("sql", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        TextView car_license_register_detail= (TextView) findViewById(R.id.car_license_register_detail);
        Set<String> set = sharedPreferences.getStringSet("carLicenses", null);
        String carLicenses="";
        if (set!=null) {

            for (String str : set) {
                carLicenses += str + " ";
            }

            car_license_register_detail.setText(carLicenses);
        }
        rl_my_photo_detail = (RelativeLayout) findViewById(R.id.rl_my_photo_detail);


        setViewListener();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_left_white:
                onBackPressed();
                break;
            case R.id.rl_edit_head_photo:
                showPhotoHeadFindDialog();
                break;
            case R.id.rl_name_detail:
                showNameFindDialog();
                break;
            case R.id.rl_sex_detail:
                showSexFindDialog();
                break;
            case R.id.rl_drive_age_detail:
                showDriveAgeFindDialog();
                break;
            case R.id.rl_birth_calendar_detail:
                showCalenderFindDialog();
                break;
            case R.id.rl_height_weight_detail:
                showHeightWeightFindDialog();
                break;
            case R.id.rl_ancestral_homeland_detail:
                showAncestralHomelandFindDialog();
                break;
            case R.id.rl_introduction_detail:
                showIntroductionFindDialog();
                break;
            case R.id.rl_driving_license_detail:
                showDrivingLicenseFindDialog();
                break;
            case R.id.rl_teacher_certificate_detail:
                showTeacherCertificateFindDialog();
                break;
            case R.id.rl_car_license_detail:
                startActivity(new Intent(DetailActivity.this,ModifyCarLicense.class));
                break;
            case R.id.rl_my_photo_detail:
                showUploadPhotoFindDialog();
                break;
        }

    }

    private void showUploadPhotoFindDialog() {

    }


    private void showTeacherCertificateFindDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(DetailActivity.this);
        builder.setView(View.inflate(DetailActivity.this,R.layout.dialog_drive_license,null)).
                setNegativeButton("照片", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setCancelable(true);
        builder.create().show();

    }

    private void showDrivingLicenseFindDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(DetailActivity.this);
        builder.setView(View.inflate(DetailActivity.this,R.layout.dialog_drive_license,null)).
                setNegativeButton("照片", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setCancelable(true);
        builder.create().show();

    }

    private void showIntroductionFindDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(DetailActivity.this);
        View view=View.inflate(DetailActivity.this,R.layout.dialog_modify_introduction,null);
       final EditText et_introduction_dialog= (EditText) view.findViewById(R.id.et_introduction_dialog);
        String savedIntroduction=sharedPreferences.getString("introduction","");
        if (!TextUtils.isEmpty(savedIntroduction)){
            et_introduction_dialog.setText(savedIntroduction);
        }
        builder.setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String introduction = et_introduction_dialog.getText().toString().trim();
                if (TextUtils.isEmpty(introduction)) {
                    Toast.makeText(DetailActivity.this, "信息为空", Toast.LENGTH_SHORT).show();
                } else {
                    names = introduction;
                    introduction_register_detail.setText(names);
                    editor.putString("introduction",names);
                    editor.apply();
                    Toast.makeText(DetailActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                }

            }
    }).setCancelable(true);
        builder.create().show();

    }

    private void showNameFindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        View view = View.inflate(DetailActivity.this, R.layout.dialog_modify_name, null);
        final EditText et_name_dialog = (EditText) view.findViewById(R.id.et_name_dialog);
        String savedName = sharedPreferences.getString("name", "");
        if (!TextUtils.isEmpty(savedName)) {
            et_name_dialog.setText(savedName);
        }
        builder.setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = et_name_dialog.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(DetailActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();

                } else {
                    names = name;
                    name_register_detail.setText(names);
                    editor.putString("name", names);
                    editor.apply();
                    Toast.makeText(DetailActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        }).setCancelable(true);
        builder.create().show();



    }

    private void showAncestralHomelandFindDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        View view = View.inflate(DetailActivity.this, R.layout.dialog_modify_location, null);
        final EditText et_location_dialog = (EditText) view.findViewById(R.id.et_location_dialog);
        String savedName = sharedPreferences.getString("location", "");
        if (!TextUtils.isEmpty(savedName)) {
            et_location_dialog.setText(savedName);
        }
        builder.setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String location = et_location_dialog.getText().toString().trim();
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(DetailActivity.this, "祖籍不能为空", Toast.LENGTH_SHORT).show();

                } else {
                    names = location;
                    ancestral_homeland_register_detail.setText(names);
                    editor.putString("location", names);
                    editor.apply();
                    Toast.makeText(DetailActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();

                }

            }
        }).setCancelable(true);
        builder.create().show();
    }
}
