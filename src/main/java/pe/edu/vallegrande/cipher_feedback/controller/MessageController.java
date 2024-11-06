package pe.edu.vallegrande.cipher_feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.cipher_feedback.model.EncryptedMessage;
import pe.edu.vallegrande.cipher_feedback.model.User;
import pe.edu.vallegrande.cipher_feedback.repository.EncryptedMessageRepository;
import pe.edu.vallegrande.cipher_feedback.repository.UserRepository;
import pe.edu.vallegrande.cipher_feedback.service.CFBService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private CFBService cfbService;

    @Autowired
    private EncryptedMessageRepository encryptedMessageRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/encrypt")
    public EncryptedMessage encryptMessage(@RequestBody String message, Authentication authentication) throws Exception {
        User user = (User) authentication.getPrincipal();
        String encryptedText = cfbService.encrypt(message);
        EncryptedMessage encryptedMessage = new EncryptedMessage();
        encryptedMessage.setUser(user);
        encryptedMessage.setEncryptedText(encryptedText);
        encryptedMessage.setDecryptedText(message);
        return encryptedMessageRepository.save(encryptedMessage);
    }

    @GetMapping("/messages")
    public List<EncryptedMessage> getMessages(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return encryptedMessageRepository.findByUser(user);
    }

}
