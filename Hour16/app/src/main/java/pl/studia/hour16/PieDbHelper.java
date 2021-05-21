package pl.studia.hour16;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PieDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FOOD_DATABASE.db";
    private static final String PIE_TABLE = "PIE_TABLE";

    public static final String KEY_ROWID = "Id";

    public static final String NAME = "Name";
    public static final String DESCRIPTION = "Desrption";
    public static final String PRICE = "Price";
    public static final String FAVOURITE = "Favourite";

    public PieDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PIE =
                "CREATE TABLE " + PIE_TABLE + " ( " + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + NAME + " TEXT, " + DESCRIPTION + " TEXT, " + PRICE + " REAL, "
                        + FAVOURITE + " BOOL)";

        db.execSQL(CREATE_TABLE_PIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PIE_TABLE);
        onCreate(db);
    }

    public boolean addOne(Pie pie) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, pie.getmName());
        cv.put(DESCRIPTION, pie.getmDescription());
        cv.put(PRICE, pie.getmPrice());
        cv.put(FAVOURITE, pie.ismFovorite());

        long insert = db.insert(PIE_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteOne(Pie pie) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PIE_TABLE + " WHERE " + KEY_ROWID + " = " + pie.getmId();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<Pie> getEveryone() {
        List<Pie> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + PIE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null );

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                boolean favorite = cursor.getInt(4) == 1;
                String description = cursor.getString(2);
                float price = cursor.getFloat(3);

                Pie newPie = new Pie(id, name, description, price, favorite);
                returnList.add(newPie);

            }
            while (cursor.moveToNext());
        } else {
            //fail.
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
