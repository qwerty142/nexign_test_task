package org.example.repository.repositories;

import org.example.repository.tables.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan
public interface UserRepository extends JpaRepository<User, String> {
}
