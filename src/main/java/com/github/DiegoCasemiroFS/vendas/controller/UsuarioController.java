package com.github.DiegoCasemiroFS.vendas.controller;

import com.github.DiegoCasemiroFS.vendas.controller.dto.CredentialDto;
import com.github.DiegoCasemiroFS.vendas.controller.dto.TokenDto;
import com.github.DiegoCasemiroFS.vendas.entity.Usuario;
import com.github.DiegoCasemiroFS.vendas.exception.SenhaInvalidaException;
import com.github.DiegoCasemiroFS.vendas.security.JwtService;
import com.github.DiegoCasemiroFS.vendas.service.UsuarioService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@Api("Api Usuários")
@RequiredArgsConstructor
@RequestMapping("v1/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    public Usuario include(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioService.include(usuario);
    }

    @PostMapping("/auth")
    public TokenDto authenticate(@RequestBody CredentialDto credentialDto) {
        try {
            Usuario usuario = Usuario.builder()
                    .login(credentialDto.getLogin())
                    .senha(credentialDto.getSenha())
                    .build();

            UserDetails usuarioAutenticado = usuarioService.authenticate(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDto(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}