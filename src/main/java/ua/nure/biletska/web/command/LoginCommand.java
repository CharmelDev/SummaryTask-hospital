package ua.nure.biletska.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.DAO.MedicalStaffDAO;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.entity.MedicalStaff;
import ua.nure.biletska.web.command.nurse.OpenNurseMedicalCardCommand;

public class LoginCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        // error handler
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;
        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("login");
        log.trace("Request parameter: loging --> " + login);
        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        MedicalStaff med = null;
        try {
            med = new MedicalStaffDAO().getMedByLogin(login);
        } catch (IllegalStateException e) {
            errorMessage = "Cannot find user with such login";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        log.trace("Found in DB: user --> " + med);
        if (med == null || !password.equals(med.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role medRole = Role.getRole(med);
            if (medRole == Role.ADMIN)
                forward = Path.PAGE__ADMIN;
            if (medRole == Role.DOCTOR)
                forward = Path.PAGE__DOC;
            if (medRole == Role.NURSE)
            	forward = new OpenNurseMedicalCardCommand().execute(request, response);
            session.setAttribute("medStaff", med);
            log.trace("Set the session attribute: user --> " + med);
            session.setAttribute("medRole", medRole);
            log.trace("Set the session attribute: userRole --> " + medRole);
            log.info("MedStaff " + med + " logged as " + medRole.toString().toLowerCase());
        }
        return forward;
    }
}

