//package ir.ha.dep.utility;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.telephony.SmsMessage;
//import android.widget.Toast;
//
//public class SmsReceiver extends BroadcastReceiver
//{
//    @Override
//    public void onReceive(Context context, Intent intent)
//    {
//
//        System.out.println("------------");
//        //---get the SMS message passed in---
//        Bundle bundle = intent.getExtras();
//        SmsMessage[] msgs = null;
//        String str = "";
//        if (bundle != null)
//        {
//            //---retrieve the SMS message received---
//            Object[] pdus = (Object[]) bundle.get("pdus");
//            msgs = new SmsMessage[pdus.length];
//            for (int i=0; i<msgs.length; i++){
//                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
//                str = msgs[i].getMessageBody().toString();
//            }
//
//
//            if(msgs[0].getMessageBody().split("\n")[0].equals("Done")){
//
//                Toast.makeText(context,"در خواست شما با موفقیت اننجام شد",Toast.LENGTH_SHORT).show();
//
//            }else {
//                System.out.println("---------:aaa"+msgs[0].getMessageBody());
////                SharedViewModel.massage.setValue(msgs[0].getMessageBody());
//
//            }
//
//
//            //---display the new SMS message---
//        }
//    }
//}
