package ua.nure.biletska.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateAddPationtForm {

    private static final String EMAIL_REGEXP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_NUMBER_REGEXP = "[0-9]{10}";
	private static final String LAST_NAME_REGEXP = "[a-zA-Z0-9]{2,20}";
	private static final String FIRST_NAME_REGEXP = "[a-zA-Z0-9]{2,15}";

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

    public static boolean checkEmail(String email) {
        String EMAIL_PATTERN = EMAIL_REGEXP;
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches())
            return true;
        return false;
    }

    public static boolean checkPhoneNumber(String phone) {
        String pattern = PHONE_NUMBER_REGEXP;
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}

