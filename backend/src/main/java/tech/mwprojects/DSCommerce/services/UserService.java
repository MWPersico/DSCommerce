package tech.mwprojects.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.mwprojects.DSCommerce.dto.AuthenticatedUserDTO;
import tech.mwprojects.DSCommerce.entities.User;
import tech.mwprojects.DSCommerce.exceptions.ResourceNotFoundException;
import tech.mwprojects.DSCommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = repository.searchUserWithRolesByEmail(username);
        if(user == null) throw new UsernameNotFoundException("User with email "+username+" was not found.");

        return user;
    }

    @Transactional(readOnly = true)
    public AuthenticatedUserDTO getMe(){
        return new AuthenticatedUserDTO(authenticated());
    }

    @Transactional(readOnly = true)
    public User getUserWithRolesById(Integer id){
        return repository.findByIdWithRoles(id).orElseThrow(()->new ResourceNotFoundException("User with id "+id+"was not found."));
    }

    protected User authenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        String username = jwtPrincipal.getClaim("username");

        return repository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User with email "+username+" was not found."));
    }
}
