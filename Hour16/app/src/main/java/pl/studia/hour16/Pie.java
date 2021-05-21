package pl.studia.hour16;

import java.util.ArrayList;

public class Pie {
    private int mId;
    private String mName;
    private String mDescription;
    private double mPrice;
    private boolean mFovorite;

    public Pie() {
    }

    public Pie(int id, String name, String description, double price, boolean favorite){
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mPrice = price;
        this.mFovorite = favorite;
    }

    public static ArrayList<Pie> makePies(){
        ArrayList<Pie> pies = new ArrayList<Pie>();
        pies.add(new Pie(456, "jablecznik", "bardzo dobry", 1.1, true ));
        pies.add(new Pie(243, "jagodzianka", "bardzobbbb dobry", 1.2, false ));
        pies.add(new Pie(2344, "sernik", "bardzobb dobry", 1.3, false ));
        pies.add(new Pie(62435, "kokosanki", "bardzob dobry", 1.4, false ));
        return pies;
    }

    @Override
    public String toString() {
        return "Pie{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mPrice=" + mPrice +
                ", mFovorite=" + mFovorite +
                '}';
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public boolean ismFovorite() {
        return mFovorite;
    }

    public void setmFovorite(boolean mFovorite) {
        this.mFovorite = mFovorite;
    }
}