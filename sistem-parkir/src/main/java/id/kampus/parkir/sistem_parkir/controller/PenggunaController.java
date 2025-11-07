package id.kampus.parkir.sistem_parkir.controller;

import id.kampus.parkir.sistem_parkir.entity.Pengguna;
import id.kampus.parkir.sistem_parkir.repository.PenggunaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pengguna")
@RequiredArgsConstructor
public class PenggunaController {

    private final PenggunaRepo repo;

    @GetMapping
    public List<Pengguna> list() {
        return repo.findAll();
    }

    @PostMapping
    public Pengguna create(@RequestBody Pengguna p) {
        return repo.save(p);
    }
}
