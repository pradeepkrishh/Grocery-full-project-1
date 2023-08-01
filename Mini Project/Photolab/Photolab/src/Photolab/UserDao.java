package Photolab;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
    User getUserById(int userId);
	User getUserByUsernameAndPassword(String username, String password);
}
