package varun.com.russshassignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import varun.com.russshassignment.bean.UserBean;

/**
 * Created by VarunBarve on 28/04/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RussshAssignment.db";
    public static final String TABLE_NAME = "contacts";
    public static final String ID = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String MOBILE_NUMBER = "mobileNumber";
    public static final String EMAIL_ADDRESS = "emailAddress";
    public static final String PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createQuery = "Create Table " + TABLE_NAME + " (" + ID + " integer primary key," + FIRST_NAME + " text," + LAST_NAME + " text," + MOBILE_NUMBER + " text," + EMAIL_ADDRESS + " text," + PASSWORD + " text, CONSTRAINT uc_PersonID UNIQUE (" + MOBILE_NUMBER + "," + EMAIL_ADDRESS + "));";

        db.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String fName, String lName, String mobileNumber, String emailAddress, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, fName);
        contentValues.put(LAST_NAME, lName);
        contentValues.put(MOBILE_NUMBER, mobileNumber);
        contentValues.put(EMAIL_ADDRESS, emailAddress);
        contentValues.put(PASSWORD, password);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<UserBean> getAllUsers() {

        ArrayList<UserBean> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            String fName = res.getString(res.getColumnIndex(FIRST_NAME));
            String lName = res.getString(res.getColumnIndex(LAST_NAME));
            String mobileNumber = res.getString(res.getColumnIndex(MOBILE_NUMBER));
            String emailAddress = res.getString(res.getColumnIndex(EMAIL_ADDRESS));
            String password = res.getString(res.getColumnIndex(PASSWORD));

            userList.add(new UserBean(fName, lName, mobileNumber, emailAddress, password));
            res.moveToNext();
        }
        return userList;
    }

    public Cursor getUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME + " where id = " + id, null);
        return res;
    }
}
