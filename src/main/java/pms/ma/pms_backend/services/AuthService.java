package pms.ma.pms_backend.services;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pms.ma.pms_backend.dto.AuthenticationDTO;
import pms.ma.pms_backend.security.JwtService;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public Map<String, String> login(AuthenticationDTO authenticationDTO) {
        try {
            final Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password())
            );
            if(authenticate.isAuthenticated()) {
                return this.jwtService.generate(authenticationDTO.username());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
