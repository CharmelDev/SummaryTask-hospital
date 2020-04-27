package ua.nure.biletska.web.command.nurse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.AssignmentDAO;
import ua.nure.biletska.web.command.Command;

public class CompleteNurceAssignmentCommand extends Command {

    private static final long serialVersionUID = -6445881367059355735L;

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
                || !request.getSession(false).getAttribute("medRole").equals(Role.NURSE)) {
            errorMessage = "Wrong priviliges";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        int assignment_id = Integer.parseInt(request.getParameter("assignment_id"));
        new AssignmentDAO().CompleteAssignmentById(assignment_id);
        return new OpenNurseMedicalCardCommand().execute(request, response);
    }
}

