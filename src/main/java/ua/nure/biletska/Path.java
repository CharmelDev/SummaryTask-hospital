package ua.nure.biletska;

public class Path {

    private Path() {
    }

    // pages
    public static final String PAGE__LOGIN = "/login.jsp";
    public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE__SETTINGS = "/WEB-INF/jsp/settings.jsp";

    public static final String PAGE__ADMIN = "/WEB-INF/jsp/admin/adminPage.jsp";
    public static final String PAGE__ADMIN_ADD_PATIENT = "/WEB-INF/jsp/admin/addPatient.jsp";
    public static final String PAGE__ADMIN_ADD_MED = "/WEB-INF/jsp/admin/addMedStaff.jsp";
    public static final String PAGE__DOC = "/WEB-INF/jsp/doctor/doctor_page.jsp";
    public static final String PAGE__NURCE = "/WEB-INF/jsp/nurse/nurce_page.jsp";

    public static final String PAGE_PATIENT_LIST_ADMIN = "/WEB-INF/jsp/admin/listOfPatients.jsp";
    public static final String PAGE_MEDICAL_STAFF_LIST_ADMIN = "/WEB-INF/jsp/admin/listOfMedStaff.jsp";

    public static final String PAGE_PATIENT_LIST_DOC = "/WEB-INF/jsp/doctor/listOfPatientsDoc.jsp";
    public static final String PAGE_MEDICAL_CARD = "/WEB-INF/jsp/doctor/medicalCard.jsp";
    public static final String PAGE_PATIENT_CARD = "/WEB-INF/jsp/doctor/patient_card.jsp";
}
