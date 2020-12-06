package ru.kpekepsalt.diary.service.Impl;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.model.AppUser;
import ru.kpekepsalt.diary.model.AppUserDetails;
import ru.kpekepsalt.diary.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = userRepository.findByLogin(s)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User %s not found", s))
                );
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

    public AppUserDetails getUserDetails() {
        AppUserDetails user = (AppUserDetails) getAuthentication().getPrincipal();
        return user;
    }

    public Long getUserid() {
        return getUserDetails().getUserId();
    }

    public String getUserLogin() {
        return getUserDetails().getUsername();
    }

    public AppUser getUser() {
        return getUserDetails().getUser();
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }
}
