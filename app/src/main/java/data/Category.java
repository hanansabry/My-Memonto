package data;

/**
 * Created by Hanan on 1/22/2018.
 */

public class Category {
    private String categoryName;
    private int categoryNums;
    private String categoryDate;

    public Category(String categoryName, int categoryNums, String categoryDate){
        this.categoryName = categoryName;
        this.categoryNums = categoryNums;
        this.categoryDate = categoryDate;
    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryNums() {
        return categoryNums;
    }

    public void setCategoryNums(int categoryNums) {
        this.categoryNums = categoryNums;
    }

    public String getCategoryDate() {
        return categoryDate;
    }

    public void setCategoryDate(String categoryDate) {
        this.categoryDate = categoryDate;
    }
}
