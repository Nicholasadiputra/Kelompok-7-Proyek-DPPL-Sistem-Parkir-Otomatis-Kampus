package id.kampus.parkir.sistem_parkir.controller;

import id.kampus.parkir.sistem_parkir.entity.PermohonanIzinParkir;
import id.kampus.parkir.sistem_parkir.service.PermohonanIzinParkirService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/izin")
public class PermohonanIzinParkirController {

    private final PermohonanIzinParkirService service;

    public PermohonanIzinParkirController(PermohonanIzinParkirService service) {
        this.service = service;
    }

    @GetMapping
    public List<PermohonanIzinParkir> getAll() {
        return service.findAll();
    }

    @PostMapping
    public PermohonanIzinParkir create(@RequestBody PermohonanIzinParkir p) {
        return service.save(p);
    }
}
