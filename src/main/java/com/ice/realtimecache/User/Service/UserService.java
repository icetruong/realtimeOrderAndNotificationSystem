package com.ice.realtimecache.User.Service;

import com.ice.realtimecache.User.Entity.Role;
import com.ice.realtimecache.User.Entity.User;
import com.ice.realtimecache.User.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User addUser(String name, String email, String password)
    {
        User u = User.builder()
                .fullName(name)
                .email(email)
                .password(password)
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();
        return userRepo.save(u);
    }
}
