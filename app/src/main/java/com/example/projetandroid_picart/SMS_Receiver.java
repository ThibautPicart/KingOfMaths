package com.example.projetandroid_picart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;



/**
 * Created by Thibaut Picart on 13/12/20.
 */
public class SMS_Receiver extends BroadcastReceiver {

    //private InitEquation listener;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
    public static String msg, from = "";


    @Override
    public void onReceive(Context context, Intent intent) {


        if(intent.getAction()==SMS_RECEIVED)
        {
            Bundle bundle=intent.getExtras();
            if(bundle!=null){
                // on traite les infos contenues dans le bundle
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i=0; i<pdus.length; i++){
                    messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                    msg=messages[i].getMessageBody();
                    from = messages[i].getOriginatingAddress();
                    //abortBroadcast();

                    MultiActivity.initEquation(msg);
                }

                //Toast.makeText(context, "Message : " + msg + "\nNumber: " + from, Toast.LENGTH_LONG).show();


            }

        }

    }


} // receiver
