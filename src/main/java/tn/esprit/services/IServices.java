package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;


public interface IServices<T> {
    void add(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    List<T> afficher() throws SQLException;
    void signup(T t) throws SQLException;
}
