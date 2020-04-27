package ua.nure.biletska.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.MedicalStaffDAO;
import ua.nure.biletska.db.entity.MedicalStaff;
import ua.nure.biletska.validation.ValidateAddMedStaffForm;
import ua.nure.biletska.web.command.Command;

/**
 * add medical staff command.
 * @author Olha Biletska
 *
 */
public class AddMedStaffCommand extends Command {

    private static final int NURCE_ROLE_ID = 2;
	private static final int DOCTOR_ROLE_ID = 1;
	private static final long serialVersionUID = 3071536586985632473L;
    private static final Logger log = Logger.getLogger(AddPatientCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");

        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;

        if (request.getSession(false) == null) {
            errorMessage = "You are not register";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        if (request.getSession(false).getAttribute("medRole") == null
                || !request.getSession(false).getAttribute("medRole").equals(Role.ADMIN)) {
            errorMessage = "Wrong priviliges";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        String login = request.getParameter("login");
        if (!ValidateAddMedStaffForm.CheckLogin(login)) {
            request.setAttribute("form_error", "admin_jsp.errorInLogin");
            return new OpenMedStaffFormCommand().execute(request, response);
        }

        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        if (!ValidateAddMedStaffForm.checkFirstNameField(firstName)) {
            request.setAttribute("form_error", "admin_jsp.errorInFirstName");

            return new OpenAddPatientFormCommand().execute(request, response);
        }

        String lastName = request.getParameter("last_name");
        if (!ValidateAddMedStaffForm.checkLastName(lastName)) {
            request.setAttribute("form_error", "admin_jsp.errorInLastName");
            return new OpenAddPatientFormCommand().execute(request, response);
        }
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String role = request.getParameter("role");
        int role_id;
        if (role.toLowerCase().equals(Role.DOCTOR.getName().toLowerCase())) {
            role_id = DOCTOR_ROLE_ID;
        } else {
            role_id = NURCE_ROLE_ID;
        }
        MedicalStaff med = new MedicalStaff();
        med.setLogin(login);
        med.setPassword(password);
        med.setFirstName(firstName);
        med.setLastName(lastName);
        med.setCategoryId(categoryId);
        med.setRoleId(role_id);
        new MedicalStaffDAO().addMedicalStaff(med);
        return new AdminShowListOfMedStaffCommand().execute(request, response);
    }
}

