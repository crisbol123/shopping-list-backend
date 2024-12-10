package com.unicauca.shopping_list.element;

import com.unicauca.shopping_list.list.ListRepository;
import com.unicauca.shopping_list.sitio.SitioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ElementService {
    private final ElementRepository elementRepository;
 private final ListRepository listRepository;
 private final SitioRepository sitioRepository;
    public void addElement(Element element) {
        ElementEntity elementEntity = new ElementEntity();
        elementEntity.setNombre(element.getNombre());
        elementEntity.setComprado(element.getComprado());
   sitioRepository.findById(element.getIdSitio())
                .ifPresentOrElse(elementEntity::setSitio,
                        () -> {
                            throw new IllegalArgumentException("Sitio no encontrado");
                        });
        listRepository.findById(element.getIdLista())
                .ifPresentOrElse(elementEntity::setLista,
                        () -> {
                            throw new IllegalArgumentException("Lista no encontrada");
                        });

        elementRepository.save(elementEntity);

    }

    public List<ElementResponse> getElements(Long id) {
        return elementRepository.findByLista_Id(id).stream()
                .map(elementEntity -> new ElementResponse(elementEntity.getId(), elementEntity.getNombre(), elementEntity.getLista().getId(), elementEntity.getSitio().getNombre(), elementEntity.getComprado()))
                .collect(Collectors.toList());
    }

    public Long getListId() {
        return listRepository.findTopByOrderByIdDesc().getId();
    }

    public void updateElement(Element element) {
        elementRepository.findById(element.getId())
                .ifPresentOrElse(elementEntity -> {
                    elementEntity.setComprado(element.getComprado());
                    elementRepository.save(elementEntity);
                }, () -> {
                    throw new IllegalArgumentException("Elemento no encontrado");
                });
    }
}
