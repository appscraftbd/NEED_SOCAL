package com.appscraftbd.needasocal.SQLite_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class User_InfoSQLite extends SQLiteOpenHelper {

    public static final String DB_NAME = "NEED_INFO_DB.db";
    public static final int DB_VERSION =1;
    Context context;

    public User_InfoSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {

//            db.execSQL("CREATE Table note_text (Id INTEGER primary key autoincrement , Title TEXT , Body TEXT , Date TEXT)");

            sqLiteDatabase.execSQL("CREATE Table NEED_INFO (Id INTEGER PRIMARY KEY AUTOINCREMENT , UserName TEXT , Fname TEXT," +
                    "Lname TEXT, Profile_pic TEXT , Bio_info TEXT , Education TEXT , Work_info TEXT , Link TEXT )");

        }catch (Exception e){

            Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {
            sqLiteDatabase.execSQL("Drop Table if exists NEED_INFO ");

        }catch (Exception e){

        }


    }
    public void getDataInsert(String Username , String Fname , String Lname , String Profile_pic , String Bio_info
             ,String Education_info , String Work_info ,String Link ){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName",Username);
        contentValues.put("Fname",Fname);
        contentValues.put("Lname",Lname);
        contentValues.put("Profile_pic",Profile_pic);
        contentValues.put("Bio_info",Bio_info);
        contentValues.put("Education",Education_info);
        contentValues.put("Work_info",Work_info);
        contentValues.put("Link",Link);

        sqLiteDatabase.insert("NEED_INFO",null,contentValues);

    }

    public Cursor getShowData(){

            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from NEED_INFO ",null);

        return cursor;


    }

    public void getDataDelete(String Id){

        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.delete("NEED_INFO","Id = ?",new String[]{Id});

        }catch (Exception e){

        }

    }



}
