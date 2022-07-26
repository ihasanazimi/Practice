package ir.ha.dep.utility;

import static ir.ha.dep.utility.extentions.UtilityClsKt.isAppAvailable;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

import java.util.List;

import ir.ha.dep.R;

public class IntentActions {

    private final Context context;

    public IntentActions(Context context){
        this.context = context;
    }


    public void openLinkedInPage(String linkedId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%@" + linkedId));
        final PackageManager packageManager = context.getPackageManager();
        @SuppressLint("QueryPermissionsNeeded") final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.isEmpty()) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + linkedId));
        }
        context.startActivity(intent);
    }


    @SuppressLint("IntentReset")
    public void sendEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("message/rfc822");
        intent.setData(Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
        intent.putExtra(Intent.EXTRA_TEXT   , "");
        context.startActivity(Intent.createChooser(intent, "Send mail..."));
    }


    public void openInstagram(String InstagramID){
        Uri uri = Uri.parse("http://instagram.com/_u/"+InstagramID);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            context.startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/"+InstagramID)));
        }
    }


    public void shareMessageToTelegram(String msg)
    {
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = isAppAvailable(context.getApplicationContext(), appName);
        if (isAppInstalled)
        {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            myIntent.setPackage(appName);
            myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
            context.startActivity(Intent.createChooser(myIntent, "Share with"));
        }
        else Toast.makeText(context, "Telegram not Installed", Toast.LENGTH_SHORT).show();
    }




}
