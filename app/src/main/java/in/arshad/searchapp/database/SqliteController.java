package in.arshad.searchapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

public class SqliteController extends SQLiteOpenHelper {

  private static final String LOGCAT = "SearchApp";

  public SqliteController(Context applicationcontext) {
    super(applicationcontext, "androidsqlite.db", null, 1);
    Log.d(LOGCAT, "Created");
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    String query;
    query = "CREATE TABLE Comments ( CommentId INTEGER PRIMARY KEY, Comment TEXT, ImageId TEXT)";
    database.execSQL(query);
    Log.d(LOGCAT, "Comments Created");
  }

  @Override
  public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
    String query;
    query = "DROP TABLE IF EXISTS Comments";
    database.execSQL(query);
    onCreate(database);
  }

  public void insertComment(HashMap<String, String> queryValues) {
    SQLiteDatabase database = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("Comment", queryValues.get("Comment"));
    values.put("ImageId", queryValues.get("ImageId"));
    database.insert("Comments", null, values);
    database.close();
  }

  public int updateComment(HashMap<String, String> queryValues) {
    SQLiteDatabase database = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("Comment", queryValues.get("Comment"));
    values.put("ImageId", queryValues.get("ImageId"));
    return database.update("Comments", values, "ImageId" + " = ?", new String[]{queryValues.get(
        "ImageId")});
  }

  public void deleteComment(String id) {
    Log.d(LOGCAT, "delete");
    SQLiteDatabase database = this.getWritableDatabase();
    String deleteQuery = "DELETE FROM Comments where ImageId='" + id + "'";
    Log.d("query", deleteQuery);
    database.execSQL(deleteQuery);
  }

  public ArrayList<HashMap<String, String>> getAllComments() {
    ArrayList<HashMap<String, String>> wordList;
    wordList = new ArrayList<HashMap<String, String>>();
    String selectQuery = "SELECT * FROM Comments";
    SQLiteDatabase database = this.getWritableDatabase();
    Cursor cursor = database.rawQuery(selectQuery, null);
    if (cursor.moveToFirst()) {
      do {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("CommentId", cursor.getString(0));
        map.put("Comment", cursor.getString(1));
        map.put("ImageId", cursor.getString(2));
        wordList.add(map);
      } while (cursor.moveToNext());
    } // return contact list return wordList;
    return wordList;
  }

  public ArrayList<HashMap<String, String>> getCommentInfo(String id) {
    ArrayList<HashMap<String, String>> wordList;
    wordList = new ArrayList<HashMap<String, String>>();
    String selectQuery = "SELECT * FROM Comments where ImageId='" + id + "'";
    SQLiteDatabase database = this.getWritableDatabase();
    Cursor cursor = database.rawQuery(selectQuery, null);
    if (cursor.moveToFirst()) {
      do {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("CommentId", cursor.getString(0));
        map.put("Comment", cursor.getString(1));
        map.put("ImageId", cursor.getString(2));
        wordList.add(map);
      } while (cursor.moveToNext());
    } // return contact list return wordList;
    return wordList;
  }
}
