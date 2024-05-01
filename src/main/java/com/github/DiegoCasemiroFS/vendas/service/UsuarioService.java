package com.github.DiegoCasemiroFS.vendas.service;

import com.github.DiegoCasemiroFS.vendas.entity.Usuario;
import com.github.DiegoCasemiroFS.vendas.exception.SenhaInvalidaException;
import com.github.DiegoCasemiroFS.vendas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario include(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public UserDetails authenticate(Usuario usuario){
        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean senhaOk = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());

        if (senhaOk){
            return userDetails;
        }

        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
