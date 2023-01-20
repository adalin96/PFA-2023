package com.emsi.HallBooking.dao;

import com.emsi.HallBooking.service.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String userName);
}
