package pl.studia.hour21;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;

import java.util.Random;


public class MainActivity extends Activity {
    String[] choices;
    ListView choicesListView;
    int notificationId;
    NotificationManager notificationManager;
    Notification.Builder builder;
    Intent intent;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources resources = getResources();
        choices = resources.getStringArray(R.array.choices_array);
        choicesListView = (ListView) findViewById(R.id.listView);
        choicesListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, choices));
        choicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        int notificationId = 0;
                        builder =
                                new Notification.Builder(MainActivity.this)
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setAutoCancel(true)
                                        .setContentTitle("Powiadomienie")
                                        .setContentText("Proste powiadomienie bez dodatkowej akcji.");
                        notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(notificationId, builder.build());
                        break;

                    case 1:
                        notificationId = 1;
                        intent = new Intent(MainActivity.this, DestinationActivity.class);
                        intent.putExtra(DestinationActivity.ID, 1);
                        intent.putExtra(DestinationActivity.MESSAGE, "Otrzymano powiadomienie 1");
                        pendingIntent=PendingIntent.getActivity(MainActivity.this, notificationId,
                                intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        Bitmap bigIcon = BitmapFactory.decodeResource(getResources(), R.drawable.nat);
                        builder =
                                new Notification.Builder(MainActivity.this)
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setLargeIcon(bigIcon)
                                        .setContentTitle("Powiadomienie")
                                        .setContentText("Przejdź do aktywności DestinationActivity")
                                        .setProgress(0,0,true)
                                        .setContentIntent(pendingIntent);
                        notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(notificationId, builder.build());
                        break;


                    case 2:
                        notificationId = 2;
                        intent = new Intent(MainActivity.this, DestinationActivity.class);
                        intent.putExtra(DestinationActivity.ID, 2);
                        intent.putExtra(DestinationActivity.MESSAGE, "Otrzymano powiadomienie 2");
                        pendingIntent=PendingIntent.getActivity(MainActivity.this, notificationId, intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        builder =
                                new Notification.Builder(MainActivity.this)
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("Powiadomienie")
                                        .setContentText("Przejdź do aktywności DestinationActivity")
                                        .setContentIntent(pendingIntent);
                        notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(notificationId, builder.build());
                        break;

                    case 3:
                        notificationId = 3;
                        RemoteViews notificationView = new RemoteViews(MainActivity.this.getPackageName(),
                                R.layout.notificaton_view);
                        notificationView.setTextViewText(R.id.notificationTextView, "Dostosowanie!");
                        String longText = getResources().getString(R.string.notification_text);
                        notificationView.setTextViewText(R.id.textView2,longText);
                        notificationView.setImageViewResource(R.id.imageView, R.mipmap.ic_launcher);


                        intent = new Intent(MainActivity.this, DestinationActivity.class);
                        intent.putExtra(DestinationActivity.ID, 3);
                        intent.putExtra(DestinationActivity.MESSAGE, "Otrzymano powiadomienie 3");
                        pendingIntent=PendingIntent.getActivity(MainActivity.this, notificationId, intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        builder =
                                new Notification.Builder(MainActivity.this)
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("Powiadomienie")
                                        .setContentText("Przejdź do aktywności DestinationActivity")
                                        .setContentIntent(pendingIntent)
                                        .setContent(notificationView);

                        Notification notification = builder.build();
                        notification.bigContentView = notificationView;
                        notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(notificationId, notification);
                        break;
                }



            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
