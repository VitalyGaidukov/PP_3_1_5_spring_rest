package PP_3_1_5_spring_rest_test.service;



import PP_3_1_5_spring_rest_test.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);

    public void updateUser(User user, int id);

    User findByUsername(String username);

    User findUserById( int id);

}
