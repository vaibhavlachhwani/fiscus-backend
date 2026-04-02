package tech.vaibhavlachhwani.fiscusbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.vaibhavlachhwani.fiscusbackend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
