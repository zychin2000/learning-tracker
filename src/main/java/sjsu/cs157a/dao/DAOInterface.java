package sjsu.cs157a.dao;

import org.json.JSONException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @param <T> The model
 *
 * The DAO is a layer where it has all the simple create, read and update operations
 *
 *
 */
public interface DAOInterface<T> {
    boolean insert(T t) throws SQLException, ClassNotFoundException;
    
    List<T> listAll() throws SQLException, ClassNotFoundException;

    T getById(String id) throws SQLException, ClassNotFoundException, JSONException;

    boolean update(T t) throws SQLException, ClassNotFoundException;

    boolean delete(T t);

}
