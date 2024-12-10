package com.unicauca.shopping_list.element;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ElementResponse {
    private Long id;
    private String nombre;
    private Long idLista;
    private String sitio;
    private Boolean comprado;
}
