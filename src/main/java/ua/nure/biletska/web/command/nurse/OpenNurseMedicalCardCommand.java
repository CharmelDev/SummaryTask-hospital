package ua.nure.biletska.web.command.nurse;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biletska.Path;
import ua.nure.biletska.db.DAO.AssignmentDAO;
import ua.nure.biletska.db.entity.PatientAssignment;
import ua.nure.biletska.web.command.Command;

public class OpenNurseMedicalCardCommand extends Command {

    private static final long serialVersionUID = -7909122658043153024L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<PatientAssignment> patientAssignment = new AssignmentDAO().getAssignmentsforNurce();
        request.setAttribute("patientAssignment", patientAssignment);
        return Path.PAGE__NURCE;
    }
}

