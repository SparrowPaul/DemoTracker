package com.sparrowpaul.tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText name, location, phone;
    private String mName , mLocation, mPhone, smsText;
    private String phoneNumber = "+233542686544";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        intViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MainActivityPermissionsDispatcher.checkSmsWithPermissionCheck(MainActivity.this);
                MainActivityPermissionsDispatcher.checkPhoneWithPermissionCheck(MainActivity.this);


                mName = name.getText().toString();
                mLocation = location.getText().toString();
                mPhone = phone.getText().toString();

                smsText = "Hi Auto Mobile Company i am "+mName+" and have developed a fault at "+mLocation+" you can reach me on " +
                        ""+mPhone+" Thanks";

//
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage("+233542686544", null,smsText,null,null);

                try {
                    Intent sInt = new Intent(Intent.ACTION_VIEW);
                    sInt.putExtra("address", new String[]{phoneNumber});
                    sInt.putExtra("sms_body",smsText);
                    sInt.setType("vnd.android-dir/mms-sms");
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }



            }
        });




    }

    @NeedsPermission(Manifest.permission.SEND_SMS)
    void checkSms() {
    }
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void checkPhone() {
    }

    private void intViews(){
        name = findViewById(R.id.mainNameID);
        location = findViewById(R.id.mainLocationID);
        phone = findViewById(R.id.mainPhoneID);
        button = findViewById(R.id.mainSubmitBtnID);
    }
}
