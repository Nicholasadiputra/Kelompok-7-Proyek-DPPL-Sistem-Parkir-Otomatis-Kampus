package id.kampus.parkir.sistem_parkir.controller;

import id.kampus.parkir.sistem_parkir.entity.Kendaraan;
import id.kampus.parkir.sistem_parkir.entity.Pengguna;
import id.kampus.parkir.sistem_parkir.entity.PermohonanIzinParkir;
import id.kampus.parkir.sistem_parkir.repository.PermohonanIzinParkirRepo;
import id.kampus.parkir.sistem_parkir.repository.PenggunaRepo;
import id.kampus.parkir.sistem_parkir.repository.KendaraanRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PermohonanIzinParkirController {

    private final PermohonanIzinParkirRepo izinRepo;
    private final PenggunaRepo penggunaRepo;
    private final KendaraanRepo kendaraanRepo;

    @GetMapping("/api/izin")
    public List<PermohonanIzinParkir> getAll() {
        return izinRepo.findAll();
    }

    @PostMapping("/izin/tambah")
    public String tambahIzin(
            @RequestParam Long penggunaId,
            @RequestParam Long kendaraanId
    ) {
        PermohonanIzinParkir izin = new PermohonanIzinParkir();

        izin.setPengguna(penggunaRepo.findById(penggunaId).orElse(null));
        izin.setKendaraan(kendaraanRepo.findById(kendaraanId).orElse(null));

        izinRepo.save(izin);

        return "redirect:/izin";
    }

    @GetMapping("/izin/hapus/{id}")
    public String hapus(@PathVariable Long id) {
        izinRepo.deleteById(id);
        return "redirect:/izin";
    }

    @GetMapping("/izin/setuju/{id}")
    public String setujuiIzin(@PathVariable Long id) {
        PermohonanIzinParkir izin = izinRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Izin tidak ditemukan"));

        izin.setDisetujui(true);
        izinRepo.save(izin);

        return "redirect:/izin";
    }

    @PostMapping("/izin/ajukan")
    public String ajukanIzin(
            @RequestParam Long penggunaId,
            @RequestParam Long kendaraanId,
            @RequestParam(required = false) String alasan
    ) {
        // ambil data pengguna
        Pengguna pengguna = penggunaRepo.findById(penggunaId)
                .orElseThrow(() -> new RuntimeException("Pengguna tidak ditemukan"));

        // ambil data kendaraan
        Kendaraan kendaraan = kendaraanRepo.findById(kendaraanId)
                .orElseThrow(() -> new RuntimeException("Kendaraan tidak ditemukan"));

        // buat objek izin baru
        PermohonanIzinParkir izin = new PermohonanIzinParkir();
        izin.setPengguna(pengguna);
        izin.setKendaraan(kendaraan);
        izin.setAlasan(alasan == null ? "-" : alasan);
        izin.setDisetujui(false);  

        izinRepo.save(izin);

        return "redirect:/izin";   // kembali ke halaman izin
    }

}
