package ru.mirzoyan.toussaint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirzoyan.toussaint.domain.Message;

public interface MessageRepo extends JpaRepository<Message,Long> {
}
