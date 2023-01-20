package com.emsi.HallBooking.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.emsi.HallBooking.dao.RoleRepository;
import com.emsi.HallBooking.dao.UserRepository;
import com.emsi.HallBooking.service.model.AppUser;
import com.emsi.HallBooking.domaine.RoleConverter;
import com.emsi.HallBooking.domaine.RoleVo;
import com.emsi.HallBooking.domaine.UserConverter;
import com.emsi.HallBooking.domaine.UserVo;
import com.emsi.HallBooking.service.model.Role;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service("userService")
@Transactional
//@RequiredArgsConstructor
//@Slf4j
@NoArgsConstructor
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //all args contractor with a pwd encryption
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(appUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> springSecurityAuthorities = new ArrayList<>();
        for (Role r : roles) {
            springSecurityAuthorities.add(new SimpleGrantedAuthority(r.getRole()));
        }
        return springSecurityAuthorities;
    }

    @Override
    public AppUser saveUser(UserVo userVo) {
        AppUser appUser =UserConverter.toUser(userVo);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    @Override
    public Role saveRole(RoleVo roleVo) {
        return roleRepository.save(RoleConverter.toRole(roleVo));
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername((username));
        Role role = roleRepository.findByRole(roleName);
        user.getRoles().add(role);
    }


    @Override
    public UserVo getUser(String username) {
        return UserConverter.toUserVo(userRepository.findByUsername(username));
    }

    @Override
    public List<UserVo> getAllUsers() {
        return UserConverter.toUserVoList((userRepository.findAll()));
    }

//    @Override this for some other time
//    public List<RoleVo> getAllRoles() { this for some other time
//        return RoleConverter.toRoleVoList((roleRepository.findAll()));
//    }
//this for some other time
//    @Override this for some other time
//    public RoleVo getRoleByName(String role) { this for some other time
//        return RoleConverter.toRoleVo(roleRepository.findByRole(role));
//    }

    @Override
    public void cleanDataBase() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }
}
