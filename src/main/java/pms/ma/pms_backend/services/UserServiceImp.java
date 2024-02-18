package pms.ma.pms_backend.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pms.ma.pms_backend.dto.AuthenticationDTO;
import pms.ma.pms_backend.entities.User;
import pms.ma.pms_backend.repositories.UserRepository;
import pms.ma.pms_backend.security.JwtService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {

//    private AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllUsersPages(Pageable pageable, String keyword, String value) {
        Page<User> users;
        switch (keyword) {
            case "username":
                users = userRepository.findUserByUsernameContainingIgnoreCase(value, pageable);
                break;
            case "firstName_lastName":
                String[] nameParts = value.split(" ");
                String fname;
                String lname;
                if (nameParts.length == 1) {
                    fname = nameParts[0];
                    lname = "";
                } else {
                    fname = nameParts[0];
                    lname = nameParts[1];
                }
                users = userRepository.findUserByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(fname, lname, pageable);
                break;
            case "email":
                users = userRepository.findUserByEmailContainingIgnoreCase(value, pageable);
                break;
            default:
                users = userRepository.findUserByUsernameContainingIgnoreCase(value, pageable);
        }
        return users;
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
    }

    @Override
    public User addUser(User user) {
        Optional<User> optionalUser = this.userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("Username or email already in use");
        }
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user, String id) {
        User user_update = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        user_update.setEmail(user.getEmail());
        user_update.setUsername(user.getUsername());
        user_update.setFirstName(user.getFirstName());
        user_update.setLastName(user.getLastName());
        user_update.setEnabled(user.isEnabled());
        return userRepository.save(user_update);
    }

    @Override
    public User loadUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with this username"));
    }
}
