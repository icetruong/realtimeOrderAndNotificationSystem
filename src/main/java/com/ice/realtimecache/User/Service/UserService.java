package com.ice.realtimecache.User.Service;
import com.ice.realtimecache.Common.Exception.ResourceNotFoundException;
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

    public void addUser(String name, String email, String password)
    {
        String normalizedEmail = email == null ? null : email.trim().toLowerCase();
        if (normalizedEmail != null && userRepo.existsByEmail(normalizedEmail)) {
            throw new ResourceNotFoundException("Email already exists");
        }

        User u = User.builder()
                .fullName(name)
                .email(normalizedEmail)
                .password(password)
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();
        userRepo.save(u);
    }

    public User getUserByEmail(String email)
    {
        String normalizedEmail = email == null ? null : email.trim().toLowerCase();
        if (normalizedEmail == null) {
            return null;
        }
        return userRepo.findByEmail(normalizedEmail).orElse(null);
    }
}
