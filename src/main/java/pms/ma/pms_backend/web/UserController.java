package pms.ma.pms_backend.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pms.ma.pms_backend.dto.AuthenticationDTO;
import pms.ma.pms_backend.entities.User;
import pms.ma.pms_backend.services.AuthService;
import pms.ma.pms_backend.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthenticationDTO authenticationDTO) {
        System.out.println("Username ----------- "+authenticationDTO.username());
        Map<String, String> token = authService.login(authenticationDTO);
        return token;
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/pages")
    Page<User> getAllUsersPages(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int per_page,
                                @RequestParam(defaultValue = "") String keyword,
                                @RequestParam(defaultValue = "") String value,
                                @RequestParam(defaultValue = "username") String sortBy) {
        Pageable pageable = PageRequest.of(page, per_page, Sort.by(sortBy));
        return userService.getAllUsersPages(pageable, keyword, value);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable String id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
