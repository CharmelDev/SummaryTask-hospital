package ua.nure.biletska.web.command.admin;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.PatientDAO;
import ua.nure.biletska.db.entity.Patient;
import ua.nure.biletska.web.command.Command;

public class AdminShowListOfPatientsCommand extends Command {

    private static final long serialVersionUID = -5361970576847952484L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;
        System.out.println(request.getSession(false));
        // check the session
        if (request.getSession(false) == null) {
            errorMessage = "You are not register";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        // check the role
        if (request.getSession(false).getAttribute("medRole") == null
                || !request.getSession(false).getAttribute("medRole").equals(Role.ADMIN)) {
            errorMessage = "Wrong priviliges";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        List<Patient> patientList = new PatientDAO().getPatients();
        request.setAttribute("patient_list", patientList);
        if (request.getParameter("sorting_order") == null
                || request.getParameter("sorting_order").equals("Sort Id")) {
            Collections.sort(patientList, (Patient o1, Patient o2) -> (int) (o1.getId() - o2.getId()));
        } else if (request.getParameter("sorting_order").equals("Sort Name")) {
            Collections.sort(patientList, (Patient o1, Patient o2) -> o1.getFirstName().compareTo(o2.getFirstName()));
        } else if (request.getParameter("sorting_order").equals("Sort Email")) {
            Collections.sort(patientList, (Patient o1, Patient o2) -> o1.getEmail().compareTo(o2.getEmail()));
        } else if (request.getParameter("sorting_order").equals("Sort Birth")) {
            Collections.sort(patientList,
                    (Patient o1, Patient o2) -> o1.getDateOfBirth().compareTo(o2.getDateOfBirth()));
        }
         request.getSession(false).setAttribute("all_patients", new PatientDAO().getAllPatients());
        return Path.PAGE_PATIENT_LIST_ADMIN;
    }
}
