package id.kampus.parkir.sistem_parkir.controller;

import id.kampus.parkir.sistem_parkir.entity.Pengguna;
import id.kampus.parkir.sistem_parkir.repository.PenggunaRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PenggunaController {

    private final PenggunaRepo penggunaRepo;

    public PenggunaController(PenggunaRepo penggunaRepo) {
        this.penggunaRepo = penggunaRepo;
    }

    @PostMapping("/api/pengguna/tambah")
    public String tambahPengguna(
            @RequestParam String nama,
            @RequestParam String email
    ) {
        Pengguna p = new Pengguna();
        p.setNama(nama);
        p.setEmail(email);
        p.setStatusAktif(true);

        penggunaRepo.save(p);
        return "redirect:/pengguna";
    }

    @GetMapping("/api/pengguna/hapus/{id}")
    public String hapusPengguna(@PathVariable Long id) {
        penggunaRepo.deleteById(id);
        return "redirect:/pengguna";
    }
}

