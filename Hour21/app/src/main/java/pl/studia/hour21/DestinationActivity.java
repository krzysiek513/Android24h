package pl.studia.hour21;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class DestinationActivity extends Activity {
    public static String ID = "pl.studia.hour21.notificationId";
    public static String MESSAGE = "pl.studia.hour21.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        Intent intent = getIntent();
        if (intent.hasExtra(ID)){
            int notificationId = intent.getIntExtra(ID,0);
            NotificationManager  notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationId==2){
                Notification.Builder builder =
                        new Notification.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setAutoCancel(true)
                                .setContentTitle("Aktualizacja powiadomienia")
                                .setContentText("Aktualizacja przez DestinationActivity");
                notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(notificationId, builder.build());
            } else{
                notificationManager.cancel(notificationId);
            }
        }
        if (intent.hasExtra(MESSAGE)){
            TextView textView = (TextView)findViewById(R.id.textView);
            String message = intent.getStringExtra(MESSAGE);
            textView.setText(message);
        }
    }

}
