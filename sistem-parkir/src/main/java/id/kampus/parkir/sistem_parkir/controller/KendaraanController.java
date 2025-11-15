package id.kampus.parkir.sistem_parkir.controller;

import id.kampus.parkir.sistem_parkir.entity.Kendaraan;
import id.kampus.parkir.sistem_parkir.entity.Pengguna;
import id.kampus.parkir.sistem_parkir.repository.KendaraanRepo;
import id.kampus.parkir.sistem_parkir.repository.PenggunaRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kendaraan")
@RequiredArgsConstructor
public class KendaraanController {

    private final KendaraanRepo kendaraanRepo;
    private final PenggunaRepo penggunaRepo;

    // GET ALL
    @GetMapping
    public List<Kendaraan> getAll() {
        return kendaraanRepo.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Kendaraan getOne(@PathVariable Long id) {
        return kendaraanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Kendaraan tidak ditemukan"));
    }

    // CREATE
    @PostMapping
    public Kendaraan create(@RequestBody Kendaraan k) {
        // pastikan pemilik valid
        if (k.getPengguna() != null) {
            Optional<Pengguna> pemilik = penggunaRepo.findById(k.getPengguna().getId());
            pemilik.ifPresent(k::setPengguna);
        }
        return kendaraanRepo.save(k);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Kendaraan update(@PathVariable Long id, @RequestBody Kendaraan data) {
        Kendaraan k = kendaraanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Kendaraan tidak ditemukan"));

        k.setPlatNomor(data.getPlatNomor());
        k.setStatusAktif(data.isStatusAktif());

        if (data.getPengguna() != null) {
            Optional<Pengguna> pemilik = penggunaRepo.findById(data.getPengguna().getId());
            pemilik.ifPresent(k::setPengguna);
        }

        return kendaraanRepo.save(k);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        kendaraanRepo.deleteById(id);
        return "Kendaraan berhasil dihapus";
    }
}
