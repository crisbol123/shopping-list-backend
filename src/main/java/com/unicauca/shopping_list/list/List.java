package com.unicauca.shopping_list.list;

import com.unicauca.shopping_list.usuarios.UsuarioEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class List {
    private Long id;
    private String nombre;
    private String fecha;
}
