package ua.nure.biletska.web.command.med;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.AssignmentDAO;
import ua.nure.biletska.db.entity.PatientAssignment;
import ua.nure.biletska.web.command.Command;

public class OpenMedicalCardCommand extends Command {

    private static final long serialVersionUID = 3340981522803068509L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;

        // check the session
        if (request.getSession(false) == null) {
            errorMessage = "You are not register";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        // check the role
        if (request.getSession(false).getAttribute("medRole") == null
                || !request.getSession(false).getAttribute("medRole").equals(Role.DOCTOR)) {
            errorMessage = "Wrong priviliges";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        List<PatientAssignment> patientAssignment = new AssignmentDAO().getAssignments();
        request.setAttribute("patientAssignment", patientAssignment);
        return Path.PAGE_MEDICAL_CARD;
    }
}

