package app.tca.thecloutapp.repositories;

import app.tca.thecloutapp.entities.User;
import app.tca.thecloutapp.models.FindAllUsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrId(String usernameOrEmail, Long usernameOrEmail1);
    @Query(value = "select name, username, email from users u where roles = ?1", nativeQuery = true)
    List<User> findByRoleName(String roleName);
}
