package com.example.INTERSWITCH.SERVICE;

import com.example.INTERSWITCH.DTO.UserDTO;
import com.example.INTERSWITCH.ENTITY.User;
import com.example.INTERSWITCH.REPOSITORY.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());

        user = userRepository.save(user);

        return new UserDTO(user.getId(), user.getUsername());
    }

    public User login(String username, HttpSession session) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            session.setAttribute("userId", user.get().getId());
            return user.get();
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            userRepository.save(newUser);
            session.setAttribute("userId", newUser.getId());
            return newUser;
        }
    }

    public Long getLoggedInUserId(HttpSession session) {
        return (Long) session.getAttribute("userId");
    }
}
