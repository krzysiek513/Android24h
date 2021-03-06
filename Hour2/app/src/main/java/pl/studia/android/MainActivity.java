package pl.studia.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button activityButton =  (Button) findViewById(R.id.button) ;

        activityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SecondaryActivity.class);
                startIntent.putExtra("com.talkingandroid.MESSAGE","Pozdrowienia dla SecondaryActivity" );
                startActivity(startIntent);

            }
        });

        Button mapButton =  (Button) findViewById(R.id.button2) ;
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String geoURI = "geo:37.422,-122.084?z=23";
                Uri geo = Uri.parse(geoURI);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geo);
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        Button webButton =  (Button) findViewById(R.id.button3) ;
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String webURI = "http://www.google.com";
                Uri web = Uri.parse(webURI);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, web);
                if (webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });

        Button mailButton =  (Button) findViewById(R.id.button4) ;
        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] recipient = {"carmendelessio@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, recipient);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "W sprawie ksi????ki o systemie Android");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Cze????, Carmen, ");
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
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