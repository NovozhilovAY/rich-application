package com.example.richapplication.repository;

import com.example.richapplication.dto.Payment;
import com.example.richapplication.model.ShortUser;
import com.example.richapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM users ORDER BY money DESC")
    List<User> findAll();
    List<User> getUsersByCityOrderByMoneyDesc(String city);
    List<User> getUsersByCountryOrderByMoneyDesc(String country);

    Optional<User> findUserByLogin(String login);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "CALL make_payment(?1, ?2)")
    void makePayment(Integer id, Double amount);
}
