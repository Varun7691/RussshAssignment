package varun.com.russshassignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VarunBarve on 28/04/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RussshAssignment.db";
    public static final String TABLE_NAME = "contacts";
    public static final String ID = "id";
    public static final String FIRST_NAME = "id";
    public static final String LAST_NAME = "id";
    public static final String MOBILE_NUMBER = "id";
    public static final String EMAIL_ADDRESS = "id";
    public static final String PASSWORD = "id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
