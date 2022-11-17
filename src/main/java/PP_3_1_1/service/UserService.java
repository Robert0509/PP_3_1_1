package PP_3_1_1.service;

import PP_3_1_1.models.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    List<User> getUsers();

    User getUserById(int id);

    void update(int id,User updatedUser);

    void delete(int id);
}
