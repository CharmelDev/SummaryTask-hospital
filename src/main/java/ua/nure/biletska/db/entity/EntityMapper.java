package ua.nure.biletska.db.entity;

import java.sql.ResultSet;

public interface EntityMapper<T> {
    T mapRow(ResultSet resultSet);
}
