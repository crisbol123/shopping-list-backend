package com.unicauca.shopping_list.usuarios;


import com.unicauca.shopping_list.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private  final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
private final UsuarioEntityMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsServiceImp;
    private final JwtService jwtService;



    public void save(Usuario usuario) {
if(usuarioRepository.findByCedula(usuario.getCedula()).isPresent()){
    throw new RuntimeException("Usuario ya existe");
}
        UsuarioEntity entity =  mapper.aEntidad(usuario);
        entity.setContrasena(encoder.encode(usuario.getContrasena()));
        usuarioRepository.save(entity);
    }

    public AuthResponse login(LoginRequest loginRequest) {
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getCedula(), loginRequest.getContrasena()));
        UserDetails user= userDetailsServiceImp.loadUserByUsername(loginRequest.getCedula());
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }
    public Usuario getUsuario(String token){
        String cedula = jwtService.getUsernameFromToken(token);
        return usuarioRepository.findByCedula(cedula)
                .map(mapper::aUsuario)
                .orElseThrow(()-> new IllegalArgumentException("Usuario no encontrado"));
    }


    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).map(mapper::aUsuario).orElse(null);
    }
}
