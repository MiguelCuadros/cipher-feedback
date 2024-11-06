package pe.edu.vallegrande.cipher_feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.cipher_feedback.model.EncryptedMessage;
import pe.edu.vallegrande.cipher_feedback.model.User;

import java.util.List;

@Repository
public interface EncryptedMessageRepository extends JpaRepository<EncryptedMessage, Long> {

    List<EncryptedMessage> findByUser(User user);

}
