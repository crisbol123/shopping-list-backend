package com.unicauca.shopping_list.list;

import com.unicauca.shopping_list.JwtService;
import com.unicauca.shopping_list.element.Element;
import com.unicauca.shopping_list.usuarios.UsuarioEntity;
import com.unicauca.shopping_list.usuarios.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ListService {
    private final ListRepository listRepository;
private final UsuarioRepository usuarioRepository;
private final JwtService jwtService;
public ListEntity saveList(List list, String token) {
        ListEntity listEntity = new ListEntity();
        listEntity.setNombre(list.getNombre());
        usuarioRepository.findByCedula(jwtService.getUsernameFromToken(token))
                .ifPresentOrElse(listEntity::setUsuario,
                        () -> {
                            throw new IllegalArgumentException("Usuario no encontrado");
                        });


        return listRepository.save(listEntity);
}
public java.util.List<List> getLists(String token) {
        java.util.List<ListEntity> listEntities= listRepository.findByUsuario_Cedula(jwtService.getUsernameFromToken(token));
return listEntities.stream().map(listEntity -> {
            List list = new List();
            list.setNombre(listEntity.getNombre());
            list.setFecha(listEntity.getFecha().format(DateTimeFormatter.ISO_DATE_TIME));
    list.setId(listEntity.getId());
            return list;
        }).collect(Collectors.toList());
    }

    public void deleteList(Long id, String token) {
        listRepository.findById(id)
                .ifPresentOrElse(listEntity -> {
                    if (listEntity.getUsuario().getCedula().equals(jwtService.getUsernameFromToken(token))) {
                        listRepository.delete(listEntity);
                    } else {
                        throw new IllegalArgumentException("Usuario no autorizado");
                    }
                }, () -> {
                    throw new IllegalArgumentException("Lista no encontrada");
                });
    }



    public String getUsernameFromToken(String token) {
        return jwtService.getUsernameFromToken(token);
    }
}



