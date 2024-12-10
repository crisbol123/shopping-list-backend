package com.unicauca.shopping_list.element;

import com.unicauca.shopping_list.list.ListEntity;
import com.unicauca.shopping_list.sitio.SitioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "element")
public class ElementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lista_id")
    @OnDelete(action = OnDeleteAction.CASCADE)

    private ListEntity lista;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "sitio_id")
    private SitioEntity sitio;

   private Boolean comprado;

}
