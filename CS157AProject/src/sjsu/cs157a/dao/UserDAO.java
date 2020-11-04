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
        String sql = "INSERT INTO `user_register` (`first_name`, `last_name`, `email`, `phone`, `password`) VALUES ('?', '?', '?', '?', '?');";
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
        Map<String, String> data = databaseConnection.executePreparedStatement(sql, id).get(0);

        if(data != null){
            return new User(data.get("user_id"),data.get("first_name"),data.get("last_name"),data.get("phone"),data.get("email"),data.get("password"));
        }

        return null;

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

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO(new DatabaseConnection());

        try {
            User user = userDAO.getById("13883700");

            user.setFirstName("newname");

            user.setUserID("459");

            boolean res = userDAO.update(user);

            System.out.println(res);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
