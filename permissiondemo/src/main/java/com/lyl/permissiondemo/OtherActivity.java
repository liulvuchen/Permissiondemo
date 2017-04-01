package com.lyl.permissiondemo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OtherActivity extends Activity implements View.OnClickListener{
    private Button btCallPhone;
    private Button btContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCallPhone = (Button) findViewById(R.id.call_phone);
        btContact = (Button) findViewById(R.id.contact);

        btCallPhone.setOnClickListener(this);
        btContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btCallPhone){
            //拨打电话
            PermissionsUtils.requestPermission(this,1, Manifest.permission.ACCESS_COARSE_LOCATION, new Runnable() {
                @Override
                public void run() {
                    callPhone();
                }
            }, new Runnable() {
                @Override
                public void run() {
                    callPhoneDenied();
                }
            });
        }else if(v == btContact){
            //读取联系人信息
            PermissionsUtils.requestPermission(this,2, Manifest.permission.RECORD_AUDIO, new Runnable() {
                @Override
                public void run() {
                    readContact();
                }
            }, new Runnable() {
                @Override
                public void run() {
                    readContactDenied();
                }
            });
        }
    }

    private void callPhone() {
        Toast.makeText(OtherActivity.this, "ACCESS_COARSE_LOCATION OK", Toast.LENGTH_SHORT)
                .show();
    }

    private void callPhoneDenied() {
        Toast.makeText(OtherActivity.this, "ACCESS_COARSE_LOCATION Denied", Toast.LENGTH_SHORT)
                .show();
    }

    private void readContact() {

        Toast.makeText(OtherActivity.this, "RECORD_AUDIO OK", Toast.LENGTH_SHORT)
                .show();
    }

    private void readContactDenied() {
        Toast.makeText(OtherActivity.this, "RECORD_AUDIO Denied", Toast.LENGTH_SHORT)
                .show();
    }
}
