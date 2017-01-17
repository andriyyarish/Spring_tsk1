package epamUniversity.dao;

import epamUniversity.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserAccount , Long> {
    UserAccount findByUsername(String username);
}