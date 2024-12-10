package com.unicauca.shopping_list.sitio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@Service
public class SitioService {
    private final SitioRepository sitioRepository;

    public SitioEntity save(Sitio sitio) {
        SitioEntity sitioEntity = new SitioEntity();
        sitioEntity.setNombre(sitio.getNombre());
        return sitioRepository.save(sitioEntity);
    }
    public java.util.List<Sitio> getAll() {
        return sitioRepository.findAll().stream()
                .map(sitioEntity -> new Sitio(sitioEntity.getId(), sitioEntity.getNombre()))
                .collect(java.util.stream.Collectors.toList());
    }
}
