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
import ua.nure.biletska.db.entity.Diagnos;
import ua.nure.biletska.db.entity.EntityMapper;
import ua.nure.biletska.web.command.Commands;

public class DiagnosDAO {
  
    public Diagnos getDiagnosById(long id) {
        Diagnos diagnos = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connestion = null;
        try {
            connestion = DBManager.getInstance().getConnection();
            preparedStatement = connestion.prepareStatement(Commands.SQL_GET_DIAGNOS_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            DiagnossMapper mapper = new DiagnossMapper();
            diagnos = mapper.mapRow(resultSet);
            return diagnos;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connestion);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connestion);
        }
        return diagnos;
    }

    public List<Diagnos> GetDiagnoses() {
        List<Diagnos> diagnosList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            DiagnossMapper mapper = new DiagnossMapper();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Commands.SQL_GET_DIAGNOSIS);
            while (resultSet.next())
                diagnosList.add(mapper.mapRow(resultSet));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return diagnosList;
    }

    private static class DiagnossMapper implements EntityMapper<Diagnos> {
        @Override
        public Diagnos mapRow(ResultSet resultSet) {
            try {
                Diagnos diagnos = new Diagnos();
                diagnos.setId(resultSet.getLong(Fields.ENTITY__ID));
                diagnos.setDiagnosName(resultSet.getString(Fields.DIAGNOSE_NAME));
                return diagnos;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

