package com.unicauca.shopping_list.element;

import com.unicauca.shopping_list.list.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementRepository extends JpaRepository<ElementEntity, Long> {
    List<ElementEntity> findByLista_Id(Long id);
}
