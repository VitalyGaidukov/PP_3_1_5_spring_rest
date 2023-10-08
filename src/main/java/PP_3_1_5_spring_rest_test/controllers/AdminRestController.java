package PP_3_1_5_spring_rest_test.controllers;

import PP_3_1_5_spring_rest_test.models.Role;
import PP_3_1_5_spring_rest_test.models.User;
import PP_3_1_5_spring_rest_test.service.RoleService;
import PP_3_1_5_spring_rest_test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AdminRestController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminRestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/admin/roles")
    public ResponseEntity<List<Role>> getAllRoles(){
        List <Role> roleList = roleService.listRoles();
        return new ResponseEntity<>(roleList,HttpStatus.OK);
    }

    @GetMapping("/admin/role")
    public ResponseEntity<Set<Role>> adminGetRole(Principal principal){
        User user = userService.findByUsername(principal.getName());
        Set<Role> userRole = user.getRoles();
        return new ResponseEntity<>(userRole, HttpStatus.OK);
    }

    @GetMapping("/admin/roles/{id}")
    public ResponseEntity<Set<Role>> getRole(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.findUserById(id).getRoles(), HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User>userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> addNewUser(@RequestBody User newUser){
        userService.saveUser(newUser);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

    @PatchMapping (path = "/admin/{id}")
    public ResponseEntity<User>updateUser(@PathVariable int id, @RequestBody User userUpdate){
        userService.updateUser(userUpdate, id);
        return new ResponseEntity<>(userUpdate,HttpStatus.OK);
    }
}
