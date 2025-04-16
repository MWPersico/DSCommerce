package tech.mwprojects.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mwprojects.DSCommerce.exceptions.ForbiddenException;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Integer id, String deniedMessage){
        if(!isAdmin() && !isLoggedUser(id)){
            denied(deniedMessage);
        }
    }

    private boolean isLoggedUser(Integer id){
        return userService.authenticated().getId().equals(id);
    }

    private boolean isAdmin(){
        return userService.authenticated().hasRole("ROLE_ADMIN");
    }

    private void denied(String message) throws ForbiddenException{
        throw new ForbiddenException("Access Denied: "+ message);
    }
}
