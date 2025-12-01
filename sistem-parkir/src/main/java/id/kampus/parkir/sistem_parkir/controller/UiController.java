package id.kampus.parkir.sistem_parkir.controller;

import id.kampus.parkir.sistem_parkir.dto.RingkasanDTO;
import id.kampus.parkir.sistem_parkir.entity.Pengguna;
import id.kampus.parkir.sistem_parkir.entity.Kendaraan;
import id.kampus.parkir.sistem_parkir.repository.PenggunaRepo;
import id.kampus.parkir.sistem_parkir.repository.KendaraanRepo;
import id.kampus.parkir.sistem_parkir.repository.SlotParkirRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UiController {

    private final PenggunaRepo penggunaRepo;
    private final KendaraanRepo kendaraanRepo;
    private final SlotParkirRepo slotParkirRepo;

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
        List<Kendaraan> list = kendaraanRepo.findAll();
        model.addAttribute("kendaraanList", list);
        return "kendaraan"; // templates/kendaraan.html
    }
}
