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
import ua.nure.biletska.db.entity.Category;
import ua.nure.biletska.db.entity.EntityMapper;
import ua.nure.biletska.web.command.Commands;
public class CategoryDAO {

    public List<Category> getCategories() {
        List<Category> categoryList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            CategoryMapper mapper = new CategoryMapper();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Commands.SQL_FIND_ALL_CATEGORIES);
            while (resultSet.next())
                categoryList.add(mapper.mapRow(resultSet));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return categoryList;
    }

    public Category getCategoryById(long id) {
        Category category = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Commands.SQL_GET_CATEGORY_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            CategoryMapper mapper = new CategoryMapper();
            category = mapper.mapRow(resultSet);
            return category;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return category;
    }

    private static class CategoryMapper implements EntityMapper<Category> {
        @Override
        public Category mapRow(ResultSet resultSet) {
            try {
                Category category = new Category();
                category.setId(resultSet.getLong(Fields.ENTITY__ID));
                category.setCategoryName(resultSet.getString(Fields.CATEGORY_NAME));
                return category;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}