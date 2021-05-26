package pl.studia.hour19;

import java.util.ArrayList;

public class Pie {
    int mId;
    String mName;
    String mDescription;
    double mPrice;
    boolean mIsFavorite;

    public Pie(){
    }

    public Pie (String name, String description, double price){
        this.mName = name;
        this.mDescription = description;
        this.mPrice = price;
        this.mIsFavorite=false;
    }

    public static ArrayList<Pie> makePies(){
        ArrayList<Pie> pies = new ArrayList<Pie>();
        pies.add(new Pie("Jabłecznik", "Z pachnącymi i rumianymi jabłkami.", 1.0));
        pies.add(new Pie("Jagodzianka", "Zawsze świeże i soczyste jagody.", 1.5));
        pies.add(new Pie("Sernik", "Cudownie delikatny i aromatyczny.", 2.0));
        pies.add(new Pie("Kokosanki", "Ulubione ciastka każdego łasucha.", 2.5));
        return pies;
    }
}

