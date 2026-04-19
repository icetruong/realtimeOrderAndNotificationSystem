package com.ice.realtimecache.User.Repository;

import com.ice.realtimecache.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByFullName(String fullName);
}
