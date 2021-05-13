package pl.studia.hour9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView pieRecyclerView;
    private RecyclerView.LayoutManager pieLayoutManager;

    ArrayList<Pie> pies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale locale = new Locale("pl","PL");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);
        pieRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pieRecyclerView.setHasFixedSize(true);
        pies = makePies();
//        pieLayoutManager = new LinearLayoutManager(this);
        pieLayoutManager = new GridLayoutManager(this,1);

        pieRecyclerView.setLayoutManager(pieLayoutManager);
        PieAdapter adapter = new PieAdapter( pies);
        pieRecyclerView.setAdapter(adapter);
    }

    public class PieAdapter extends RecyclerView.Adapter<ViewHolder> {
        Context mContext;
        ArrayList<Pie> mPies;
        LayoutInflater mInflater;

        public PieAdapter(ArrayList<Pie> pies) {
            mPies = pies;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pie_view_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Pie currentPie = mPies.get(position);
            holder.textViewName.setText(currentPie.mName);
            holder.textViewDescription.setText(currentPie.mDescription);
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String price = formatter.format(currentPie.mPrice);
            holder.textViewPrice.setText(price);
        }


        @Override
        public int getItemCount() {
            return mPies.size();
        }
    }


    private  class ViewHolder  extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewDescription;
        public TextView textViewPrice;
        public ViewHolder(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.textViewName);
            textViewDescription = (TextView) v.findViewById(R.id.textViewDescription);
            textViewPrice = (TextView) v.findViewById(R.id.textViewPrice);
        }
    }

    private ArrayList<Pie> makePies(){
        ArrayList<Pie> pies = new ArrayList<Pie>();
        pies.add(new Pie("Jabłecznik", "Z pachnącymi i rumianymi jabłkami.", 1.5));
        pies.add(new Pie("Jagodzianka", "Zawsze świeże i soczyste jagody.", 1.5));
        pies.add(new Pie("Sernik", "Cudownie delikatny i aromatyczny.", 2.0));
        pies.add(new Pie("Kokosanki", "Ulubione ciastka każdego łasucha.", 2.5));
        return pies;
    }

    private class Pie {
        String mName;
        String mDescription;
        double mPrice;
        public Pie (String name, String description, double price){
            this.mName = name;
            this.mDescription = description;
            this.mPrice = price;
        }
    }
}
