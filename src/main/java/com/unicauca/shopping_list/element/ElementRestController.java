package com.unicauca.shopping_list.element;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/element")
public class ElementRestController {
    private final ElementService elementService;
    @PostMapping("/add")
  @CrossOrigin(origins = "http://localhost:8100")

    public void addElement(@RequestBody Element element) {
        elementService.addElement(element);
    }
    @PostMapping("/update")
    @CrossOrigin(origins = "http://localhost:8100")
    public void updateElement(@RequestBody Element element) {
        elementService.updateElement(element);
    }

    @GetMapping("/get/{id}")
    @CrossOrigin(origins = "http://localhost:8100")
    public List<ElementResponse> getElements(
            @PathVariable Long id) {
        return elementService.getElements(id);

    }
    @GetMapping("/getListaId")
    @CrossOrigin(origins = "http://localhost:8100")
    public Long getListId() {

return elementService.getListId();
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new IllegalArgumentException("Token JWT no válido o ausente");
        }
    }

}
