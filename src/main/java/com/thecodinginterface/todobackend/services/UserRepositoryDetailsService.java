package com.thecodinginterface.todobackend.services;

import com.thecodinginterface.todobackend.models.User;
import com.thecodinginterface.todobackend.repositories.UserRepository;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRepositoryDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public UserRepositoryDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new TodosUserDetails(repo.findByUsername(username));
    }

    static class TodosUserDetails extends User implements UserDetails, CredentialsContainer {

        private final List<GrantedAuthority> authorities;

        TodosUserDetails(User user) {
            super(user);
            this.authorities = user.getUserAuthorities().stream()
                    .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
                    .collect(Collectors.toList());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return this.getEnabled();
        }

        @Override
        public void eraseCredentials() {
            this.setPassword(null);
        }
    }

}
