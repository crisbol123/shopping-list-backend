package com.unicauca.shopping_list.element;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Element {
    private Long id;
    private String nombre;
    private Long idLista;
    private Long idSitio;
    private Boolean comprado;

}
