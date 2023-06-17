package com.luiz.projetos.check.services;

import com.luiz.projetos.check.dto.JWTToken;
import com.luiz.projetos.check.dto.LoginDTO;
import com.luiz.projetos.check.dto.UsuarioDTO;
import com.luiz.projetos.check.exceptions.SenhaInvalidaException;
import com.luiz.projetos.check.model.Usuario;
import com.luiz.projetos.check.model.enumeration.Roles;
import com.luiz.projetos.check.repositories.UsuarioRepository;
import com.luiz.projetos.check.security.JwtService;
import com.luiz.projetos.check.security.UserDetailsImpl;
import com.luiz.projetos.check.security.UsuarioDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.luiz.projetos.check.model.enumeration.Roles.ADMIN;
import static com.luiz.projetos.check.model.enumeration.Roles.USER;

@Service
public class UsuarioService {
    @Value("${check.admin.email}")
    private String EMAIL_ADMIN;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioDetailsServiceImpl usuarioDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    public boolean emailAlreadyExists(UsuarioDTO dto) {
        return repository.existsByEmail(dto.getEmail());
    }

    public boolean emailBelongsToAnotherUser(UsuarioDTO dto, Long id) {
        return repository.emailBelongsToAnother(dto.getEmail(), id).isEmpty();
    }

    public void save(UsuarioDTO dto) {
        Roles role = dto.getEmail().equals(EMAIL_ADMIN) ? ADMIN : USER;
        Usuario usuario = Usuario.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(role)
                .tarefas(Collections.emptyList())
                .build();
        repository.save(usuario);
    }

    public JWTToken autenticar(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.gerarToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new JWTToken("Bearer " + token);
    }


}
