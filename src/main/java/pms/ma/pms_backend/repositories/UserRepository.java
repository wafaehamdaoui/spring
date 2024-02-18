package pms.ma.pms_backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pms.ma.pms_backend.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByUsernameOrEmail(String username, String email);
    Page<User> findUserByEmailContainingIgnoreCase(String email, Pageable pageable);
    Page<User> findUserByUsernameContainingIgnoreCase(String username, Pageable pageable);
    Page<User> findUserByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);

}
