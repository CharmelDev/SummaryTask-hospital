package ua.nure.biletska.web.command;

public class Commands {
	
	public static final String SQL_FIND_ALL_PATIENTS = "SELECT * FROM patient";
	public static final String SQL_FIND_PATIENT_BY_ID = "SELECT * FROM patient where id=?";
    public static final String SQL_ADD_NEW_PATIENT = "INSERT INTO patient (first_name, last_name, doctor_id, "
            + "date_of_birth, telephon_number, email) " + "VALUES (?,?,?,?,?,?)";
    public static final String SQL_FIND_DOCTORS_PATIENTS = "SELECT * FROM patient WHERE doctor_id=?";
    public static final String SQL_SET_DIAGNOS_BY_PATIENT_ID = "UPDATE patient SET diagnose_id=? WHERE id=?";
    public static final String SQL_DISCHARGED_PATIENT_BY_ID = "UPDATE patient SET isDischarger=1 WHERE id=?";
    
    public static final String SQL_FIND_ALL_DOCTORS = "SELECT * FROM medical_staff WHERE role_id=1 OR role_id=2";
    public static final String SQL_FIND_MED_STAFF_BY_LOGIN = "SELECT * FROM medical_staff WHERE login=?";
    public static final String SQL_ADD_NEW_MEDICAL_STAFF = "INSERT INTO medical_staff(login, password, first_name, last_name, category_id, role_id) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SQL_FIND_DOC_BY_ID = "SELECT * FROM medical_staff WHERE id=?";
    public static final String SQL_NUMB_OF_PATIENTS = "select count(patient.doctor_id) as Count from patient where doctor_id = ?";

    public static final String SQL_GET_DIAGNOSIS = "SELECT * FROM diagnose";
    public static final String SQL_GET_DIAGNOS_BY_ID = "SELECT * FROM diagnose WHERE id = ?";
    
    public static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM category;";
    public static final String SQL_GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE ID = ?";
    
    public static final String SQL_FIND_ALL_ASSIGNMENT = "SELECT * FROM patient_assignment";
    public static final String SQL_FIND_PATIENT_ASSIGNMENTS = "SELECT * FROM patient_assignment WHERE patient_id=?";
    public static final String SQL_ADD_ASSIGNMENT = "INSERT INTO patient_assignment (patient_id, assignment_id, assignment_status_id)"
            + "VALUES(?,?,?)";
    public static final String SQL_COMPLETE_ASSIGNMENT_BY_ID = "UPDATE patient_assignment SET assignment_status_id = 1 WHERE id = ?";
    public static final String SQL_FINND_ALL_ASSIGNMENT_FOR_NURCE = "SELECT * FROM patient_assignment WHERE assignment_id=0 "+ "OR assignment_id=1";
       


}
