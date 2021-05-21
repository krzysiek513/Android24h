package pl.studia.hour16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button add, viewAll;
    Switch sw_favorite;
    EditText et_name, et_description, et_price;
    ListView lv_pies;
    ArrayAdapter pieArrayAdapter;
    PieDbHelper pieDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        viewAll = findViewById(R.id.viewAll);
        sw_favorite = findViewById(R.id.sw_favorite);
        et_name = findViewById(R.id.etName);
        et_description = findViewById(R.id.etDescription);
        et_price = findViewById(R.id.etPrice);
        lv_pies = findViewById(R.id.lvPies);

        pieDbHelper = new PieDbHelper(MainActivity.this);
        showPieOnListView(pieDbHelper);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pie pie;

                try {
                    pie = new Pie(-1, et_name.getText().toString(), et_description.getText().toString(),
                            Float.parseFloat(et_price.getText().toString()), sw_favorite.isChecked());
                }
                catch ( Exception e) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    pie = new Pie(-1, "error", "error", 0, false);
                }

                PieDbHelper pieDbHelper = new PieDbHelper(MainActivity.this);
                boolean succes = pieDbHelper.addOne(pie);

                showPieOnListView(pieDbHelper);

            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PieDbHelper pieDbHelper = new PieDbHelper(MainActivity.this);
                showPieOnListView(pieDbHelper);

            }
        });

        lv_pies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pie clickerPie = (Pie) parent.getItemAtPosition(position);
                pieDbHelper.deleteOne(clickerPie);
                showPieOnListView(pieDbHelper);
            }
        });
    }

    private void showPieOnListView(PieDbHelper pieDbHelper2) {
        pieArrayAdapter = new ArrayAdapter<Pie>(MainActivity.this,
                android.R.layout.simple_list_item_1, pieDbHelper2.getEveryone());
        lv_pies.setAdapter(pieArrayAdapter);
    }
}