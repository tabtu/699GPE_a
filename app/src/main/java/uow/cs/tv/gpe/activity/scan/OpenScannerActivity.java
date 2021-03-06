package uow.cs.tv.gpe.activity.scan;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import uow.cs.tv.gpe.R;

public class OpenScannerActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    private String usid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        usid = getIntent().getStringExtra("usid").toString().trim();

        BGAQRCodeUtil.setDebug(true);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_scan_qrcode:
                Intent intent = new Intent(OpenScannerActivity.this, ScannerActivity.class);
                intent.putExtra("usid", usid);
                startActivity(intent);
//                startActivity(new Intent(this, ScannerActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }
}
