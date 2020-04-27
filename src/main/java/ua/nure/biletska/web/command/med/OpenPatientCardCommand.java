package ua.nure.biletska.web.command.med;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Assignment;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.AssignmentDAO;
import ua.nure.biletska.db.DAO.DiagnosDAO;
import ua.nure.biletska.db.DAO.PatientDAO;
import ua.nure.biletska.db.entity.Diagnos;
import ua.nure.biletska.db.entity.Patient;
import ua.nure.biletska.db.entity.PatientAssignment;
import ua.nure.biletska.web.command.Command;

public class OpenPatientCardCommand extends Command {

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

        Patient patient = new PatientDAO().getPatientById(Long.parseLong(request.getParameter("patient_id")));
        List<PatientAssignment> patientAssignmentList = new AssignmentDAO().getPatientAssignments(patient.getId());
        List<Assignment> assignmentList = new ArrayList<>(Arrays.asList(Assignment.values()));
        List<Diagnos> diagnosList = new DiagnosDAO().GetDiagnoses();
        request.setAttribute("diagnosList", diagnosList);
        request.setAttribute("assignmentList", assignmentList);
        request.setAttribute("patientAssignmentList", patientAssignmentList);
        request.setAttribute("patient", patient);
        return Path.PAGE_PATIENT_CARD;
    }
}

