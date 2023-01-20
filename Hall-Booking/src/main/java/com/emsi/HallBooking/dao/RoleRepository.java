package com.emsi.HallBooking.dao;

import com.emsi.HallBooking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role); //findByNameOfTheRole
    List<Role> findAll();
}
