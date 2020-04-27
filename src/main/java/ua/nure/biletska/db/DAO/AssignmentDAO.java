package ua.nure.biletska.db.DAO;

import ua.nure.biletska.db.DBManager;
import ua.nure.biletska.db.Fields;
import ua.nure.biletska.db.entity.EntityMapper;
import ua.nure.biletska.db.entity.PatientAssignment;
import ua.nure.biletska.web.command.Commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {
   
    public void CompleteAssignmentById(int id) {
        PreparedStatement prepareStatment = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            prepareStatment = connection.prepareStatement(Commands.SQL_COMPLETE_ASSIGNMENT_BY_ID);
            prepareStatment.setInt(1, id);
            prepareStatment.executeUpdate();
            prepareStatment.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public void AddAssignment(long patient_id, int assignment_id) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        try {
            int k = 1;
            connection = DBManager.getInstance().getConnection();
            pstmt = connection.prepareStatement(Commands.SQL_ADD_ASSIGNMENT);
            pstmt.setLong(k++, patient_id);
            pstmt.setInt(k++, assignment_id);
            pstmt.setInt(k++, 0);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public List<PatientAssignment> getPatientAssignments(long id) {
        List<PatientAssignment> assignmentList = new ArrayList<>();
        PreparedStatement prepareStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            prepareStatement = connection.prepareStatement(Commands.SQL_FIND_PATIENT_ASSIGNMENTS);
            prepareStatement.setLong(1, id);
            resultSet = prepareStatement.executeQuery();
            AssignmentMapper mapper = new AssignmentMapper();
            while (resultSet.next()) {
                assignmentList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return assignmentList;
    }

    public List<PatientAssignment> getAssignments() {
        List<PatientAssignment> assignmentList = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            AssignmentMapper mapper = new AssignmentMapper();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Commands.SQL_FIND_ALL_ASSIGNMENT);
            while (resultSet.next()) {
                assignmentList.add(mapper.mapRow(resultSet));
            }
            return assignmentList;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return assignmentList;
    }

    public List<PatientAssignment> getAssignmentsforNurce() {
        List<PatientAssignment> assignmentList = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            AssignmentMapper mapper = new AssignmentMapper();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Commands.SQL_FINND_ALL_ASSIGNMENT_FOR_NURCE);
            while (resultSet.next()) {
                assignmentList.add(mapper.mapRow(resultSet));
            }
            return assignmentList;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return assignmentList;
    }

    private static class AssignmentMapper implements EntityMapper<PatientAssignment> {
        @Override
        public PatientAssignment mapRow(ResultSet resultSet) {
            try {
                PatientAssignment assignment = new PatientAssignment();
                assignment.setId(resultSet.getLong(Fields.ENTITY__ID));
                assignment.setPatient_id(resultSet.getInt(Fields.PATIENT_ID));
                assignment.setAssignment_id(resultSet.getInt(Fields.ASSIGNMENT_ID));
                assignment.setAssignment_status_id(resultSet.getInt(Fields.ASSIGNMENT_STATUS_ID));
                return assignment;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}