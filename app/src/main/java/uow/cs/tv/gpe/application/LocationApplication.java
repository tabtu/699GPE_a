package uow.cs.tv.gpe.application;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import uow.cs.tv.gpe.service.LocationService;
import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Tab on 2018-03-24.
 */

public class LocationApplication extends Application {
    public LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
//        SDKInitializer.initialize(getActivity().getApplicationContext());

    }
}