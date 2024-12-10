package com.unicauca.shopping_list.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
   Optional<UsuarioEntity> findByCedula(String cedula);


}
