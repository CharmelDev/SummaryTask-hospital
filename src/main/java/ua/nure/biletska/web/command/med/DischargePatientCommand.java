package ua.nure.biletska.web.command.med;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.Role;
import ua.nure.biletska.db.DAO.AssignmentDAO;
import ua.nure.biletska.db.DAO.PatientDAO;
import ua.nure.biletska.db.entity.Patient;
import ua.nure.biletska.db.entity.PatientAssignment;
import ua.nure.biletska.util.SendMailSSL;
import ua.nure.biletska.web.command.Command;

public class DischargePatientCommand extends Command {

    private static final long serialVersionUID = 7052213159842679224L;

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

        long id = Long.parseLong(request.getParameter("patient_id"));
        new PatientDAO().DischargedPatient(id);

        Patient patient = new PatientDAO().getPatientById(id);
        List<PatientAssignment> patient_assignment = new AssignmentDAO().getPatientAssignments(id);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Patient with id:" + patient.getId() + "\n" + patient.getFirstName() + " " + patient.getLastName()
                + "\n");
        stringBuilder.append("Patient assignment information:" + "\n");
        for (PatientAssignment patientAssignment : patient_assignment) {
            stringBuilder.append("ID: " + patientAssignment.getId() + ", Assignment name: " + patientAssignment.getAssignmentName()
                    + ", Assinment status: " + patientAssignment.getAssignmentStatusName() + "\n");
        }
        stringBuilder.append("This patient was discharged!");
        File file = new File("C:\\Users\\User\\IdeaProjects\\hospitall" + patient.getId() + "_"
                + patient.getFirstName() + ".txt");
        System.out.println(file.getAbsolutePath());
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
        printWriter.write(stringBuilder.toString());
        printWriter.close();
        SendMailSSL.SendMailToUser(patient.getEmail(), stringBuilder.toString());

        return new OpenPatientListDocCommand().execute(request, response);
    }
}
