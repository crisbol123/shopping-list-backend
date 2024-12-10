package com.unicauca.shopping_list.sitio;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sitio")
public class SitioRestController {
    private final SitioService sitioService;

    public SitioRestController(SitioService sitioService) {
        this.sitioService = sitioService;
    }
    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:8100")

    public SitioEntity saveSitio(@RequestBody Sitio sitio) {
        return sitioService.save(sitio);
    }
    @GetMapping("/getAll")
    @CrossOrigin(origins = "http://localhost:8100")
    public java.util.List<Sitio> getAllSitios() {
        return sitioService.getAll();
    }
}
