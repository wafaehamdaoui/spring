package pms.ma.pms_backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pms.ma.pms_backend.dto.AuthenticationDTO;
import pms.ma.pms_backend.entities.User;

import java.util.List;
import java.util.Map;

public interface UserService {

//    Map<String, String> login(AuthenticationDTO authenticationDTO);
    List<User> getAllUsers();
    Page<User> getAllUsersPages(Pageable pageable, String keyword, String value);
    User getUserById(String id);
    User addUser(User user);
    void deleteUser(String id);
    User updateUser(User user, String id);
    User loadUserByUsername(String username);

}
