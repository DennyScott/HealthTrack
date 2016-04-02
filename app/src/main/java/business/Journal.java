package business;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;

import persistence.DataTransactionalHistory;
import persistence.DatabaseDefinition;


public class Journal implements ApplicationConstants {

    DatabaseDefinition dataDef;
    ArrayList<JournalEntry> journalEntryList;

    public Journal() {
        journalEntryList = new ArrayList<>();
    }

    public Map<Date, JournalEntry> getJournalEntriesByDateRange(Date beginDate, Date endDate) {
        return null;
    }

    public ArrayList<JournalEntry> getJournalEntriesByDate(Date searchDate) {
        SQLiteDatabase db = dataDef.getReadableDatabase();
        Cursor cursor = db.query(
                DataTransactionalHistory.TABLE_NAME,
                new String[]{
                        DataTransactionalHistory.COLNAME_FOODNAME,
                        DataTransactionalHistory.COLNAME_EATEN_DATE,
                        DataTransactionalHistory.COLNAME_EATEN_TIME,
                        DataTransactionalHistory.COLNAME_PORTIONSIZE,
                        DataTransactionalHistory.COLNAME_FOODTABLE_ID
                },
                (DataTransactionalHistory.COLNAME_EATEN_DATE + " = ?"),
                new String[]{
                        searchDate.toString()
                },
                null,
                null,
                DataTransactionalHistory.COLNAME_EATEN_TIME   //order it by date THEN time
        );
        db.close();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    journalEntryList.add(
                            new JournalEntry(
                                    Date.valueOf(cursor.getString(cursor.getColumnIndex(DataTransactionalHistory.COLNAME_EATEN_DATE))),
                                    Time.valueOf(cursor.getString(cursor.getColumnIndex(DataTransactionalHistory.COLNAME_EATEN_TIME))),
                                    cursor.getInt(cursor.getColumnIndex(DataTransactionalHistory.COLNAME_PORTIONSIZE)),
                                    cursor.getInt(cursor.getColumnIndex(DataTransactionalHistory.COLNAME_FOODTABLE_ID))
                                    )
                    );
                } while (cursor.moveToNext());
            }
        }

        return journalEntryList;
    }
}
