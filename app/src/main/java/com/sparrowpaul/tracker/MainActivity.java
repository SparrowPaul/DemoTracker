package com.sparrowpaul.tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.cardview.widget.CardView;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    private CardView  button;
    private EditText name, location, phone;
    private String mName , mLocation, mPhone, smsText;
    private String phoneNumber = "+233542686544";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityPermissionsDispatcher.checkSmsWithPermissionCheck(MainActivity.this);

        intViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                mName = name.getText().toString();
                mLocation = location.getText().toString();
                mPhone = phone.getText().toString();

                smsText = "Hi Sparrow Multimedia i am "+mName+" and have developed a fault at "+mLocation+" you can reach me on " +
                        ""+mPhone+" Thanks";


                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+233542686544", null,smsText,null,null);
                    Toast.makeText(MainActivity.this, "Fault Alert Sent", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }




            }
        });




    }

    @NeedsPermission(Manifest.permission.SEND_SMS)
    void checkSms() {
    }


    private void intViews(){
        name = findViewById(R.id.mainNameID);
        location = findViewById(R.id.mainLocationID);
        phone = findViewById(R.id.mainPhoneID);
        button = findViewById(R.id.mainSubmitBtnID);
    }
}
