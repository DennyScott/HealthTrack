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

    public Journal(Context currCont) {
        dataDef = new DatabaseDefinition(currCont);
    }

    public Map<Date, JournalEntry> getJournalEntriesByDateRange(Date beginDate, Date endDate) {
        return null;
    }

    public ArrayList<JournalEntry> getJournalEntriesByDate(Date searchDate) {
        SQLiteDatabase db = dataDef.getReadableDatabase();
        Cursor result = db.query(
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
                null, DataTransactionalHistory.COLNAME_EATEN_TIME   //order it by date THEN time
        );
        db.close();
        if (result != null) {
            if (result.moveToFirst()) {
                do {
                    journalEntryList.add(
                            new JournalEntry(
                                    Date.valueOf(result.getString(result.getColumnIndex(DataTransactionalHistory.COLNAME_EATEN_DATE))),
                                    Time.valueOf(result.getString(result.getColumnIndex(DataTransactionalHistory.COLNAME_EATEN_TIME))),
                                    result.getInt(result.getColumnIndex(DataTransactionalHistory.COLNAME_PORTIONSIZE)),
                                    result.getInt(result.getColumnIndex(DataTransactionalHistory.COLNAME_FOODTABLE_ID))
                                    )
                    );
                } while (result.moveToNext());
            }
        }
        result.close();
        return journalEntryList;
    }
}
