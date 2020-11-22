package ru.kpekepsalt.diary.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.model.AppUser;
import ru.kpekepsalt.diary.model.AppUserDetails;
import ru.kpekepsalt.diary.repository.UserRepository;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = userRepository.findByLogin(s).orElseThrow(() -> new UsernameNotFoundException("User "+s+" not found"));
        Set<SimpleGrantedAuthority> authorities = user.getRole().getAuthorities();
        AppUserDetails appUserDetails = new AppUserDetails(user);
        return appUserDetails;
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean hasAuthority(String authority) {
        return  getAuthentication().getAuthorities().stream()
                .anyMatch(
                        grantedAuthority -> grantedAuthority.getAuthority()
                                .equals(authority)
                );
    }

    public Long getUserid() {
        AppUserDetails user = (AppUserDetails) getAuthentication().getPrincipal();
        return user.getUserId();
    }

    public String getUserLogin() {
        AppUserDetails user = (AppUserDetails) getAuthentication().getPrincipal();
        return user.getUsername();
    }

    public AppUser getUser() {
        AppUserDetails user = (AppUserDetails) getAuthentication().getPrincipal();
        return user.getUser();
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }
}
