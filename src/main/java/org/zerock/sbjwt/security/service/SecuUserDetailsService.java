package org.zerock.sbjwt.security.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.sbjwt.entity.ERole;
import org.zerock.sbjwt.entity.User;
import org.zerock.sbjwt.repository.UserRepository;
import org.zerock.sbjwt.security.dto.SecuUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class SecuUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername .....email... : " + username);

        User userInfo = userRepository.login(username);

        if(userInfo == null) {
            throw new UsernameNotFoundException("NOT EXIST");
        }


        List<GrantedAuthority> authorityList = userInfo.getRoles().stream().map(role -> {

            log.info("role:" + role);

            if(role.getName().equals(ERole.ROLE_USER)) {
                return new SimpleGrantedAuthority("ROLE_USER");
            }else if(role.getName().equals(ERole.ROLE_MODERATOR)){
                return new SimpleGrantedAuthority("ROLE_MODERATOR");
            }else if(role.getName().equals(ERole.ROLE_ADMIN)){
                return new SimpleGrantedAuthority("ROLE_ADMIN");
            }
            return null;
        }).collect(Collectors.toList());


        SecuUser result = new SecuUser(userInfo.getUsername(), userInfo.getEmail(), userInfo.getPassword(), authorityList);

        log.info(result);

        return result;

//        List<GrantedAuthority> authorityList = new ArrayList<>();
//
//        SimpleGrantedAuthority authority1 = new SimpleGrantedAuthority("ROLE_MODERATOR");
//        authorityList.add(authority1);
//        SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_ADMIN");
//        authorityList.add(authority2);
//
//
//        SecuUser tempUser = new SecuUser("user00","aaa@bbb.com",
//                "$2a$10$Pl8wTMHcTFQ2XJVFTdCa/Oit0TRG.LRBA57m89t05rDJZ9pFmv/Iy", authorityList)
//        return tempUser;
    }
}
