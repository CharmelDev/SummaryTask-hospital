package ua.nure.biletska.web.command.admin;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.PatientDAO;
import ua.nure.biletska.db.entity.Patient;
import ua.nure.biletska.validation.ValidateAddPationtForm;
import ua.nure.biletska.web.command.Command;

/**
 * add patient command
 * @author Biletska Olha
 *
 */
public class AddPatientCommand extends Command {

    private static final long serialVersionUID = 3071536584227692473L;
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

        String first_name = request.getParameter("first_name");
        if (!ValidateAddPationtForm.checkFirstNameField(first_name)) {
            request.setAttribute("form_error", "admin_jsp.errorInFirstName");
            return new OpenAddPatientFormCommand().execute(request, response);
        }
        String last_name = request.getParameter("last_name");
        if (!ValidateAddPationtForm.checkLastName(last_name)) {
            request.setAttribute("form_error", "admin_jsp.errorInLastName");
            return new OpenAddPatientFormCommand().execute(request, response);
        }
        int doctor_id = Integer.parseInt(request.getParameter("doctor"));
        Date date = Date.valueOf(request.getParameter("date_of_birth"));

        String telephon_number = request.getParameter("telephon_number");
        if (!ValidateAddPationtForm.checkPhoneNumber(telephon_number)) {
            request.setAttribute("form_error", "admin_jsp.errorInPhoneNumber");
            return new OpenAddPatientFormCommand().execute(request, response);
        }

        String email = request.getParameter("email");
        if (!ValidateAddPationtForm.checkEmail(email)) {
            request.setAttribute("form_error", "admin_jsp.errorInEmail");
            return new OpenAddPatientFormCommand().execute(request, response);
        }

        if (request.getSession(false).getAttribute("recordSuccessful") == null) {

            Patient patient = new Patient();
            patient.setFirstName(first_name);
            patient.setLastName(last_name);
            patient.setTelephoneNumber(telephon_number);
            patient.setEmail(email);
            patient.setDateOfBirth(date);
            patient.setDoctor_id(doctor_id);
            PatientDAO addPatientIntoDB = new PatientDAO();
            addPatientIntoDB.addNewPatient(patient);
            request.getSession(false).setAttribute("recordSuccessful", true);
            forward = Path.PAGE__ADMIN;
            return new AdminShowListOfPatientsCommand().execute(request, response);
        } else {
            forward = Path.PAGE__ERROR_PAGE;
            errorMessage = "You try to send form again";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
    }
}

