package cs3443.teamshoemaker.taskmanager;

import java.util.ArrayList;
import java.util.Date;

public class JournalEntry {
    public static ArrayList<JournalEntry> journalArrayList = new ArrayList<>();
    public static String JOURNAL_EDIT_EXTRA =  "journalEdit";
    private static int idCounter = 0;
    private  int id;
    private String title;
    private String content;
    private Date deleted;


    public JournalEntry(int id, String title, String content, Date deleted) {
        this.id = idCounter++;
        this.title = title;
        this.content = content;
        this.deleted = deleted;

    }
    public JournalEntry(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.deleted = null;

    }
    public static JournalEntry getJournalForID(int passedJournalID)
    {
        for (JournalEntry journal : journalArrayList)
        {
            if(journal.getId() == passedJournalID)
                return journal;
        }

        return null;
    }

    public static ArrayList<JournalEntry> nonDeletedJournal()
    {
        ArrayList<JournalEntry> nonDeleted = new ArrayList<>();
        for(JournalEntry journal : journalArrayList)
        {
            if(journal.getDeleted() == null)
                nonDeleted.add(journal);
        }

        return nonDeleted;
    }

    // Getters and setters for the properties
    public  int getId()
    {

        return id;
    }

    public void setId(int id)
    {

        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Date getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }


}
