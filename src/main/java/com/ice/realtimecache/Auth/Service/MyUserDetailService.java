package com.ice.realtimecache.Auth.Service;

import com.ice.realtimecache.Auth.Security.UserPrincipal;
import com.ice.realtimecache.User.Entity.User;
import com.ice.realtimecache.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userService.getUserByEmail(username);

        if(u == null)
        {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(u);
    }
}
