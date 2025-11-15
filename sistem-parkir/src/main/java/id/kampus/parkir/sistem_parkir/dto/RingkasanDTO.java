package id.kampus.parkir.sistem_parkir.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RingkasanDTO {
    private long totalPengguna;
    private long totalKendaraan;
    private long slotKosong;
}
