package app.Repository;

import app.Model.Message;
import app.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
}


