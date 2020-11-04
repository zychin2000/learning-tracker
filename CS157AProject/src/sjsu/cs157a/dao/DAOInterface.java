package sjsu.cs157a.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DAOInterface<T> {
    boolean insert(T t) throws SQLException, ClassNotFoundException;
    
    List<T> listAll();

    T getById(String id) throws SQLException, ClassNotFoundException;

    boolean update(T t) throws SQLException, ClassNotFoundException;

    boolean delete(T t);

}
