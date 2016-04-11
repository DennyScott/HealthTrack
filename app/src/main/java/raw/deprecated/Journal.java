package raw.deprecated;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import business.ApplicationConstants;
import persistence.DataTransactionalHistory;
import persistence.DatabaseDefinition;


public class Journal implements ApplicationConstants {

    ArrayList<JournalEntry> journalEntryList;

    public Journal() {
        journalEntryList = new ArrayList<>();
    }

    public Map<Date, JournalEntry> getJournalEntriesByDateRange(Date beginDate, Date endDate) {
        return null;
    }

    public ArrayList<JournalEntry> getJournalEntriesByDate(Date searchDate) {
        SQLiteDatabase db = DatabaseDefinition.currentDatabase.getReadableDatabase();
        Cursor cursor = db.query(
                DataTransactionalHistory.TABLE_NAME,
                new String[]{
                        DataTransactionalHistory.COLNAME_FOODNAME,
                        DataTransactionalHistory.COLNAME_EATEN_DATE,
                        DataTransactionalHistory.COLNAME_PORTIONSIZE,
                        DataTransactionalHistory.COLNAME_FOODTABLE_ID
                },
                (DataTransactionalHistory.COLNAME_EATEN_DATE + " = ?"),
                new String[]{
                        searchDate.toString()
                },
                null,
                null,
                null
        );
        db.close();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    journalEntryList.add(
                            new JournalEntry(
                                    Date.valueOf(cursor.getString(cursor.getColumnIndex(DataTransactionalHistory.COLNAME_EATEN_DATE))),
                                    cursor.getInt(cursor.getColumnIndex(DataTransactionalHistory.COLNAME_PORTIONSIZE)),
                                    cursor.getInt(cursor.getColumnIndex(DataTransactionalHistory.COLNAME_FOODTABLE_ID))
                                    )
                    );
                } while (cursor.moveToNext());
            }
            cursor.close();
        }


        return journalEntryList;
    }
}
