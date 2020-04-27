package ua.nure.biletska.web.command.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.CategoryDAO;
import ua.nure.biletska.db.DAO.MedicalStaffDAO;
import ua.nure.biletska.db.entity.Category;
import ua.nure.biletska.db.entity.MedicalStaff;
import ua.nure.biletska.web.command.Command;

/**
 * admin's patient view command.
 * @author Olha Biletska
 *
 */
public class OpenAddPatientFormCommand extends Command {

    private static final long serialVersionUID = -5190835459542062443L;
    private static final Logger log = Logger.getLogger(OpenMedStaffFormCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getSession(false).getAttribute("recordSuccessful") != null) {
            request.getSession(false).removeAttribute("recordSuccessful");
        }
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;

        // check the session
        if (request.getSession(false) == null) {
            errorMessage = "You are not register";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        // check the role
        Role role = (Role) request.getSession(false).getAttribute("medRole");
        if (role == null || !role.equals(Role.ADMIN)) {
            errorMessage = "Wrong priviliges";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        List<Category> categoryList = new CategoryDAO().getCategories();
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.DOCTOR);
        roleList.add(Role.NURSE);
        request.setAttribute("role", roleList);
        request.setAttribute("categoryList", categoryList);

        List<MedicalStaff> medList = new MedicalStaffDAO().getDoctors();
        request.setAttribute("medStaffList", medList);
        forward = Path.PAGE__ADMIN_ADD_PATIENT;
        return forward;
    }
}

