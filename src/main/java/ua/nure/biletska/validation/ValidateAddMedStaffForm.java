package ua.nure.biletska.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.biletska.db.DAO.MedicalStaffDAO;
import ua.nure.biletska.db.entity.MedicalStaff;

public class ValidateAddMedStaffForm {

    private static final String LAST_NAME_REGEXP = "[a-zA-Z0-9]{2,20}";
	private static final String FIRST_NAME_REGEXP = "[a-zA-Z0-9]{2,15}";

	public static boolean CheckLogin(String login) {
        MedicalStaff med = null;
        try {
            med = new MedicalStaffDAO().getMedByLogin(login);
        } catch (Exception e) {
            med = null;
        }

        if (med != null)
            return false;
        return true;

    }

    public static boolean checkFirstNameField(String firstName) {
        String pattern = FIRST_NAME_REGEXP;
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(firstName);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean checkLastName(String lastName) {
        String pattern = LAST_NAME_REGEXP;
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(lastName);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
