package com.unicauca.shopping_list.list;

import com.unicauca.shopping_list.element.Element;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/list")
@AllArgsConstructor
public class ListRestController {
    private final ListService listService;
    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:8100")
    public ListEntity addList(@RequestBody List list,
                              @RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);
        return listService.saveList(list, token);
    }
    @GetMapping("/getAll")
    @CrossOrigin(origins = "http://localhost:8100")
    public java.util.List<List> getAllLists(
            @RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);
        return listService.getLists(token);
    }
    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:8100")
    public ResponseEntity<?> deleteList(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long id) {
        String token = extractTokenFromHeader(authorizationHeader);
        listService.deleteList(id, token);
        return ResponseEntity.ok().build();
    }


    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new IllegalArgumentException("Token JWT no válido o ausente");
        }
    }
}
