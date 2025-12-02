package id.kampus.parkir.sistem_parkir.controller;

import id.kampus.parkir.sistem_parkir.dto.RingkasanDTO;
import id.kampus.parkir.sistem_parkir.entity.Pengguna;
import id.kampus.parkir.sistem_parkir.entity.Kendaraan;
import id.kampus.parkir.sistem_parkir.repository.PenggunaRepo;
import id.kampus.parkir.sistem_parkir.repository.PermohonanIzinParkirRepo;
import id.kampus.parkir.sistem_parkir.repository.KendaraanRepo;
import id.kampus.parkir.sistem_parkir.repository.SlotParkirRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import id.kampus.parkir.sistem_parkir.entity.SlotParkir;

@Controller
@RequiredArgsConstructor
public class UiController {

    private final PenggunaRepo penggunaRepo;
    private final KendaraanRepo kendaraanRepo;
    private final SlotParkirRepo slotParkirRepo;
    private final PermohonanIzinParkirRepo izinRepo;
    @GetMapping("/")
    public String dashboard(Model model) {
        long totalPengguna = penggunaRepo.count();
        long totalKendaraan = kendaraanRepo.count();
        long slotKosong = slotParkirRepo.countByTersedia(true);

        RingkasanDTO ringkasan = new RingkasanDTO(
                totalPengguna,
                totalKendaraan,
                slotKosong
        );

        model.addAttribute("ringkasan", ringkasan);
        return "index"; // templates/index.html
    }

    @GetMapping("/pengguna")
    public String halamanPengguna(Model model) {
        model.addAttribute("penggunaList", penggunaRepo.findAll());
        return "pengguna";
    }

    @GetMapping("/kendaraan")
    public String halamanKendaraan(Model model) {
        model.addAttribute("kendaraanList", kendaraanRepo.findAll());
        model.addAttribute("penggunaList", penggunaRepo.findAll()); // untuk dropdown
        return "kendaraan";
    }
    
    @PostMapping("/kendaraan/tambah")
    public String tambahKendaraan(
            @RequestParam String platNomor,
            @RequestParam Long penggunaId
    ) {
        Pengguna pemilik = penggunaRepo.findById(penggunaId).orElse(null);

        Kendaraan k = new Kendaraan();
        k.setPlatNomor(platNomor);
        k.setStatusAktif(true);
        k.setPengguna(pemilik);

        kendaraanRepo.save(k);

        // setelah simpan, balik lagi ke halaman kendaraan
        return "redirect:/kendaraan";
    }

    @GetMapping("/kendaraan/hapus/{id}")
    public String hapusKendaraan(@PathVariable Long id) {
        kendaraanRepo.deleteById(id);
        return "redirect:/kendaraan";
    }
    
    @PostMapping("/kendaraan/edit")
        public String editKendaraan(
                @RequestParam Long id,
                @RequestParam String platNomor,
                @RequestParam Long penggunaId
        ) {
            Kendaraan k = kendaraanRepo.findById(id).orElse(null);
            Pengguna pemilik = penggunaRepo.findById(penggunaId).orElse(null);

            if (k != null) {
                k.setPlatNomor(platNomor);
                k.setPengguna(pemilik);
                kendaraanRepo.save(k);
            }

            return "redirect:/kendaraan";
        }
    
    @PostMapping("/kendaraan/update")
    public String updateKendaraan(
            @RequestParam Long id,
            @RequestParam String platNomor,
            @RequestParam Long penggunaId
    ) {
        Kendaraan k = kendaraanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Kendaraan tidak ditemukan"));

        k.setPlatNomor(platNomor);
        k.setStatusAktif(true);

        Pengguna p = penggunaRepo.findById(penggunaId)
                .orElse(null);
        k.setPengguna(p);

        kendaraanRepo.save(k);

        return "redirect:/kendaraan";
    }

    // HALAMAN SLOT PARKIR
    @GetMapping("/slot")
    public String halamanSlot(Model model) {
        model.addAttribute("slotList", slotParkirRepo.findAll());
        return "slot"; // templates/slot.html
    }

    // UBAH STATUS SLOT (KOSONG <-> TERISI)
    @GetMapping("/slot/toggle/{id}")
    public String toggleSlot(@PathVariable Long id) {
        SlotParkir slot = slotParkirRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot tidak ditemukan"));

        // jika sebelumnya true (tersedia), jadi false (terisi), dan sebaliknya
        slot.setTersedia(!slot.isTersedia());
        slotParkirRepo.save(slot);

        return "redirect:/slot";
    }

    @GetMapping("/izin")
    public String halamanIzin(Model model) {
        model.addAttribute("penggunaList", penggunaRepo.findAll());
        model.addAttribute("kendaraanList", kendaraanRepo.findAll());
        model.addAttribute("izinList", izinRepo.findAll());
        return "izin";
    }

}