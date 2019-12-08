package ru.mirzoyan.toussaint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirzoyan.toussaint.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
