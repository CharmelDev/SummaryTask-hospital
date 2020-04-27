package ua.nure.biletska.db.entity;

public class Category extends Entity {

    private static final long serialVersionUID = 7456257860808346236L;
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category ID: " + getId() + ", Category name: " + categoryName;
    }
}
