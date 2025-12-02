package id.kampus.parkir.sistem_parkir.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class PermohonanIzinParkir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alasan;

    private boolean disetujui;

    @ManyToOne
    private Pengguna pengguna;

    @ManyToOne
    private Kendaraan kendaraan;
}

