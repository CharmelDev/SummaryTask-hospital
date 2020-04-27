package ua.nure.biletska.web.command.med;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Assignment;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.AssignmentDAO;
import ua.nure.biletska.web.command.Command;

public class MakeAnAppoinmentCommand extends Command {

    private static final long serialVersionUID = -6075347981523918546L;

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

        String assignment_name = request.getParameter("assignment_name");
        int assignment_id = Assignment.getIndex(assignment_name);
        Long patient_id = Long.parseLong(request.getParameter("patient_id"));
        new AssignmentDAO().AddAssignment(patient_id, assignment_id);
        return new OpenPatientCardCommand().execute(request, response);
    }
}
