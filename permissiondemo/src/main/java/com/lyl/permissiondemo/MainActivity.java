package com.lyl.permissiondemo;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener{
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
            requestPermission(1, Manifest.permission.CALL_PHONE, new Runnable() {
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
            requestPermission(2, Manifest.permission.WRITE_CONTACTS, new Runnable() {
                @Override
                public void run() {
                    Log.i("tttt","ok");
                    readContact();
                }
            }, new Runnable() {
                @Override
                public void run() {
                    Log.i("tttt","no");
                    readContactDenied();
                }
            });
        }
    }

    private void callPhone() {
        Toast.makeText(MainActivity.this, "CALL_PHONE OK", Toast.LENGTH_SHORT)
                .show();
//        startActivity(new Intent(this,OtherActivity.class));
    }

    private void callPhoneDenied() {
        Toast.makeText(MainActivity.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT)
                .show();
    }

    private void readContact() {
        ContentResolver cr = getContentResolver();
        String str[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID};
        Cursor cur = cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, str, null,
                null, null);
        int count = cur.getCount();
        cur.close();

        Toast.makeText(MainActivity.this, String.format("发现%s条", count), Toast.LENGTH_SHORT)
                .show();
    }

    private void readContactDenied() {
        Toast.makeText(MainActivity.this, "Contact Denied", Toast.LENGTH_SHORT)
                .show();

    }
}
