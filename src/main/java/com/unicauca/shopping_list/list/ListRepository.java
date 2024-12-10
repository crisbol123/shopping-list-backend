package com.unicauca.shopping_list.list;

import com.unicauca.shopping_list.usuarios.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {

    java.util.List<ListEntity> findByUsuario_Cedula(String cedula);
    ListEntity findTopByOrderByIdDesc();

}
