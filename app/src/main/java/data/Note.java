package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nono on 1/24/2018.
 */

public class Note {
    private String noteTitle;
    private String noteContent;
    private String noteDate;

    public Note(String noteTitle, String noteContent){
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        setNoteDate();
    }

    public void setNoteTitle(String noteTitle){
        this.noteTitle = noteTitle;
    }

    public String getNoteTitle(){
        return noteTitle;
    }

    public void setNoteContent(String noteContent0){
        this.noteContent = noteContent;
    }

    public String getNoteContent(){
        return noteContent;
    }

    public void setNoteDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat spf = new SimpleDateFormat("dd, MMM yyyy");
        String formattedDate = spf.format(date);
        noteDate = formattedDate;
    }

    public String getNoteDate(){
        return noteDate;
    }
}
