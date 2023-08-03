package cs3443.teamshoemaker.taskmanager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.Date;



public class JournalEntryDatabaseHelper extends SQLiteOpenHelper {

    private static JournalEntryDatabaseHelper jeDatabaseHelper;
    // Define the database name and version
    private static final String DATABASE_NAME = "journal_entries.db";
    private static final int DATABASE_VERSION = 1;

    // Define the table name and column names
    public static final String TABLE_NAME = "journal_entries";
    private static final String COUNTER = "Counter";
    private static final String ID_FIELD = "id";
    public static final String TITLE_FIELD  = "title";
    public static final String CONTENT_FIELD = "content";
    private static final String DELETED_FIELD = "deleted";

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public JournalEntryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static JournalEntryDatabaseHelper instanceOfDatabase(Context context)
    {
        if(jeDatabaseHelper == null)
            jeDatabaseHelper = new JournalEntryDatabaseHelper(context);

        return jeDatabaseHelper;
    }


    // Constructor


    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(TITLE_FIELD)
                .append(" TEXT, ")
                .append(CONTENT_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");

        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades (if any)
    }
    public void addJournalToDatabase(JournalEntry journal)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, journal.getId());
        contentValues.put(TITLE_FIELD, journal.getTitle());
        contentValues.put(CONTENT_FIELD, journal.getContent());
        contentValues.put(DELETED_FIELD, getStringFromDate(journal.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populateJournalListArray()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(1);
                    String title = result.getString(2);
                    String content = result.getString(3);
                    String stringDeleted = result.getString(4);

                    // Check if the deleted date is null or empty before parsing
                    Date deleted = null;
                    if (stringDeleted != null && !stringDeleted.isEmpty()) {
                        deleted = getDateFromString(stringDeleted);
                    }

                    //    Date deleted = getDateFromString(stringDeleted);
                    JournalEntry journal = new JournalEntry(id,title,content,deleted);
                    JournalEntry.journalArrayList.add(journal);
                }
            }
        }
    }

    public void updateJournalInDB(JournalEntry journal)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, journal.getId());
        contentValues.put(TITLE_FIELD, journal.getTitle());
        contentValues.put(CONTENT_FIELD, journal.getContent());
        contentValues.put(DELETED_FIELD, getStringFromDate(journal.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(journal.getId())});
    }

    private String getStringFromDate(Date date)
    {
        if(date == null)
            return null;
        return dateFormat.format(date);
    }

    private Date getDateFromString(String string)
    {
        try
        {
            return dateFormat.parse(string);
        }
        catch (ParseException | NullPointerException e)
        {
            return null;
        }
    }
}