package sjsu.cs157a.dao;

import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDAO implements DAOInterface<User> {
    DatabaseConnection databaseConnection;

    public UserDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public boolean insert(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `user_register` (`first_name`, `last_name`, `email`, `phone`, `password`) VALUES (?,?,?,?,?);";
        databaseConnection.executeUpdate(sql,user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNo(),user.getPassword());
        return true;
    }

    @Override
    public List<User> listAll() {
        return null;
    }

    @Override
    public User getById(String id) throws SQLException, ClassNotFoundException {
        String sql =  "SELECT * FROM user_register WHERE user_id = ?";
        List<Map<String, String>> result = databaseConnection.executePreparedStatement(sql, id);

        return getUser(result);

    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `user_register` SET `first_name` = ?, `last_name` = ?, `email` = ?, `phone` = ?, `password` = ? WHERE (`user_id` = ?);";
        int res = databaseConnection.executeUpdate(sql,user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNo(),user.getPassword(),user.getUserID());
        return res == 1;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    public User getUserByCredentials(String email) throws SQLException, ClassNotFoundException {
        //todo password hashing
        String sql = "SELECT * FROM user_register WHERE email = ?";
        List<Map<String,String>> result = databaseConnection.executePreparedStatement(sql, email);
        return getUser(result);
    }

    private User getUser(List<Map<String, String>> result) {
        if(!result.isEmpty()){
            Map<String, String> data = result.get(0);
            return new User(data.get("user_id"),data.get("first_name"),data.get("last_name"),data.get("phone"),data.get("email"),data.get("password"));
        }

        return null;
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO(new DatabaseConnection());

        try {
            User user = userDAO.getById("013883753");


            boolean res = userDAO.update(user);

            System.out.println(res);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
