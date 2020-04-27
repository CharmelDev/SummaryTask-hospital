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
import ua.nure.biletska.db.entity.MedicalStaff;
import ua.nure.biletska.web.command.Commands;

public class MedicalStaffDAO {

    public List<MedicalStaff> getDoctors() {
        List<MedicalStaff> doctorsList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            MadicalStaffMapper mapper = new MadicalStaffMapper();
            resultSet = statement.executeQuery(Commands.SQL_FIND_ALL_DOCTORS);
            while (resultSet.next())
                doctorsList.add(mapper.mapRow(resultSet));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return doctorsList;
    }

    public int getCountOfPatientsForDoctorId(long id) {
        int count = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Commands.SQL_NUMB_OF_PATIENTS);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = Integer.parseInt(resultSet.getString("Count"));
        } catch (Exception ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return count;
    }

    public MedicalStaff getDoctorById(long id) {
        MedicalStaff med = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Commands.SQL_FIND_DOC_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            MadicalStaffMapper mapper = new MadicalStaffMapper();
            med = mapper.mapRow(resultSet);
            return med;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return med;
    }

    public MedicalStaff getMedByLogin(String login) {
        MedicalStaff med = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Commands.SQL_FIND_MED_STAFF_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            MadicalStaffMapper mapper = new MadicalStaffMapper();
            med = mapper.mapRow(resultSet);
            return med;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return med;
    }

    public void addMedicalStaff(MedicalStaff med) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            int k = 1;
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Commands.SQL_ADD_NEW_MEDICAL_STAFF);
            preparedStatement.setString(k++, med.getLogin());
            preparedStatement.setString(k++, med.getPassword());
            preparedStatement.setString(k++, med.getFirstName());
            preparedStatement.setString(k++, med.getLastName());
            preparedStatement.setInt(k++, med.getCategoryId());
            preparedStatement.setInt(k++, med.getRoleId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    private static class MadicalStaffMapper implements EntityMapper<MedicalStaff> {
        @Override
        public MedicalStaff mapRow(ResultSet resultSet) {
            try {
                MedicalStaff med = new MedicalStaff();
                med.setId(resultSet.getLong(Fields.ENTITY__ID));
                med.setFirstName(resultSet.getString(Fields.MEDICAL_STAFF_FIRST_NAME));
                med.setLastName(resultSet.getString(Fields.MEDICAL_STAFF_LAST_NAME));
                med.setLogin(resultSet.getString(Fields.MEDICAL_STAFF_LOGIN));
                med.setPassword(resultSet.getString(Fields.MEDICAL_STAFF_PASSWORD));
                med.setCategoryId(resultSet.getInt(Fields.MEDICAL_STAFF_CATEGORY_ID));
                med.setRoleId(resultSet.getInt(Fields.MEDICAL_STAFF_ROLE_ID));
                return med;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}