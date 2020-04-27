package ua.nure.biletska.db.entity;

import ua.nure.biletska.db.DAO.CategoryDAO;
import ua.nure.biletska.db.DAO.MedicalStaffDAO;

public class MedicalStaff extends Entity {

    private static final long serialVersionUID = 5924501495383932228L;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private int categoryId;
    private int roleId;

    // util field
    private Integer numberOfPatients = null;
    private String categoryName = null;

    public String getCategoryName() {
        if (categoryName == null) {
            categoryName = new CategoryDAO().getCategoryById(categoryId).getCategoryName();
        }
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getNumberOfPatients() {
        if (numberOfPatients == null) {
            numberOfPatients = new MedicalStaffDAO().getCountOfPatientsForDoctorId(this.getId());
        }
        return numberOfPatients;
    }

    public void setNumberOfPatients(Integer numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {

        return "Med [login=" + login + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
                + ", categoryID=" + categoryId + ", roleId=" + roleId + "]";
    }
}
