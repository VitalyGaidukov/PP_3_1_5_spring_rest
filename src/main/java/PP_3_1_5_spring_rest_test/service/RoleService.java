package PP_3_1_5_spring_rest_test.service;




import PP_3_1_5_spring_rest_test.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> listRoles();

    Role getById(int id);

    void save(Role role);

}
