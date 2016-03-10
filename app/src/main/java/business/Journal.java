package business;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.Map;
import persistence.*;


/**
 * Created by scott on 03/03/16.
 */
public class Journal {

    DatabaseDefinition dataDef;
    Map<Date, JournalEntry> journalEntryMap;

    public Journal (Context currCont){
        dataDef = new DatabaseDefinition(currCont);
    }

    public Map<Date, JournalEntry> getJournalEntryMap() {
        return journalEntryMap;
    }

    public Map<Date, JournalEntry> getJournalEntriesByDateRange(Date beginDate, Date endDate){
        return null;
    }
    public Map<Date, JournalEntry> getJournalEntriesByDate(Date searchDate){
        //TODO correct return val
        Map<Date, JournalEntry> resultMap;
        SQLiteDatabase db = dataDef.getReadableDatabase();
        Cursor result = db.query(
                dataDef.TABLE_TRANS_HIST,
                new String[] {
                        DataTransactionalHistory.COLNAME_EATEN_DATE,
                        DataTransactionalHistory.COLNAME_EATEN_TIME
                },
                (DataTransactionalHistory.COLNAME_EATEN_DATE+" = ?"),
                new String[] {
                        searchDate.toString()
                },null,null, DataTransactionalHistory.COLNAME_EATEN_DATE
        );
        db.close();
        return null;
    }
}
