package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hanan on 1/22/2018.
 */

public class Category {
    private String categoryName;
    private int categoryNotesNum;
    private String categoryDate;

    public Category(String categoryName, int categoryNotesNum){
        this.categoryName = categoryName;
        this.categoryNotesNum = categoryNotesNum;
        setCategoryDate();
    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryNotesNum() {
        return categoryNotesNum;
    }

    public void setCategoryNotesNum(int categoryNotesNum) {
        this.categoryNotesNum = categoryNotesNum;
    }

    public String getCategoryDate() {
        return categoryDate;
    }

    public void setCategoryDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat spf = new SimpleDateFormat("dd, MMM yyyy");
        String formattedDate = spf.format(date);
        this.categoryDate = formattedDate;
    }
}
