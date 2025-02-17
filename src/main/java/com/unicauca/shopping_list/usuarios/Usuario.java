package com.unicauca.shopping_list.usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    private String correo;
    private String contrasena;
    private String telefono;
    private String nombre;
    private String rol;
    private String cedula;

}
