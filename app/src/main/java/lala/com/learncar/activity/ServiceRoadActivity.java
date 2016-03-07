package lala.com.learncar.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

import java.util.Random;

import lala.com.learncar.R;

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\  =  /O
 * ____/`---'\____
 * .'  \\|     |//  `.
 * /  \\|||  :  |||//  \
 * /  _||||| -:- |||||-  \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |   |
 * \  .-\__  `-`  ___/-. /
 * ___`. .'  /--.--\  '. .'__
 * ."" '<  `.___\_<|>_/___.'  >'"".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * 佛祖保佑       永无BUG
 */
public class ServiceRoadActivity extends Activity implements AMapLocationListener  {
    private static final String TAG = "ShippingDetailFragment";

    private AMap aMap;
    private MapView mapView;
    private boolean isLocated; // 是否已经定位

    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    TextView shippingStatusView;
    private Random mRandom = new Random();


    /**
     * 方法必须重写
     * map的生命周期方法
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_road);
        mapView= (MapView) findViewById(R.id.map);

        mapView.onCreate(savedInstanceState);

        if (aMap == null && mapView != null) {
            aMap = mapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        }
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        initLocationOptions();
        if (isLocated) {
            int randomTime = mRandom.nextInt(1000);
            // 设置定位时间间隔, 修改后无需重新执行startLocation()方法
            mLocationOption.setInterval(2000 + randomTime);
            // 重新设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
        } else {
            mLocationClient.startLocation();
            isLocated = true;
        }

    }

    /**
     * 初始化定位相关参数
     */
    private void initLocationOptions() {
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     * map的生命周期方法
     */

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        // 停止定位
        mLocationClient.stopLocation();
        mLocationClient.onDestroy();
        mapView.onDestroy();
        aMap = null;
        isLocated = false;
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            // 经纬度
            double lat = aMapLocation.getLatitude();
            double lng = aMapLocation.getLongitude();
            if (lat != 0 && lng != 0 && aMap != null) {
                LatLng latLng = new LatLng(lat, lng);
                LatLngBounds bounds = new LatLngBounds.Builder().include(latLng).build();
                aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
            }
        } else {
            Log.e(TAG, "location error!");
        }
    }
}
