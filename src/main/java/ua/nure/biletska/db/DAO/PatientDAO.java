package ua.nure.biletska.db.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.biletska.db.DBManager;
import ua.nure.biletska.db.Fields;
import ua.nure.biletska.db.entity.EntityMapper;
import ua.nure.biletska.db.entity.Patient;
import ua.nure.biletska.web.command.Commands;

public class PatientDAO {

    public void DischargedPatient(long id) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Commands.SQL_DISCHARGED_PATIENT_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public void Set_diagnos(int diagnos_id, long id) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            int index = 1;
            preparedStatement = connection.prepareStatement(Commands.SQL_SET_DIAGNOS_BY_PATIENT_ID);
            preparedStatement.setInt(index++, diagnos_id);
            preparedStatement.setLong(index++, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public Patient getPatientById(Long id) {
        Patient patient = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Commands.SQL_FIND_PATIENT_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            PatientMapper mapper = new PatientMapper();
            patient = mapper.mapRow(resultSet);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return patient;
    }

    public List<Patient> getPatients() {
        List<Patient> patientList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            PatientMapper mapper = new PatientMapper();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Commands.SQL_FIND_ALL_PATIENTS);
            while (resultSet.next())
                patientList.add(mapper.mapRow(resultSet));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return patientList;
    }
    
    public int getAllPatients() {
    	int result=0;
    	 Statement statement = null;
         Connection connection = null;
         try {
             int index = 1;
             connection = DBManager.getInstance().getConnection();
             statement = connection.createStatement();
             ResultSet rs =  statement.executeQuery("select count(*) as all_patients from patient;");
             while (rs.next()) {
            	  result = rs.getInt("all_patients");	
            	}
             statement.close();
         } catch (SQLException ex) {
             DBManager.getInstance().rollbackAndClose(connection);
             ex.printStackTrace();
         } finally {
             DBManager.getInstance().commitAndClose(connection);
         }
    	return result;
    }


    public List<Patient> getPatientsByDoctorId(Long id) {
        List<Patient> patientList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Commands.SQL_FIND_DOCTORS_PATIENTS);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            PatientMapper mapper = new PatientMapper();
            while (resultSet.next()) {
                patientList.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return patientList;
    }

    public void addNewPatient(Patient patient) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            int index = 1;
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Commands.SQL_ADD_NEW_PATIENT);
            preparedStatement.setString(index++, patient.getFirstName());
            preparedStatement.setString(index++, patient.getLastName());
            preparedStatement.setInt(index++, patient.getDoctor_id());
            preparedStatement.setDate(index++, patient.getDateOfBirth());
            preparedStatement.setString(index++, patient.getTelephoneNumber());
            preparedStatement.setString(index++, patient.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    private static class PatientMapper implements EntityMapper<Patient> {
        @Override
        public Patient mapRow(ResultSet resultSet) {
            try {
                Patient patient = new Patient();
                patient.setId(resultSet.getLong(Fields.ENTITY__ID));
                patient.setFirstName(resultSet.getString(Fields.PATIENT_FIRST_NAME));
                patient.setLastName(resultSet.getString(Fields.PATIENT_LAST_NAME));
                patient.setDoctor_id(resultSet.getInt(Fields.PATIENT_DOCTOR_ID));
                patient.setDateOfBirth(resultSet.getDate(Fields.PATIENT_DATE_OF_BIRTH));
                patient.setTelephoneNumber(resultSet.getString(Fields.PATIENT_TELEPHON));
                patient.setEmail(resultSet.getString(Fields.PATIENT_EMAIL));
                patient.setDischarged(resultSet.getBoolean(Fields.PATIENT_IS_DISCHARGED));
                patient.setDiagnosId(resultSet.getInt(Fields.PATIENT_DIAGNOSE_ID));
                return patient;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

