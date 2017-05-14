package com.github.igorek2312.blog.app.repositories;

import com.github.igorek2312.blog.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Rybak
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
