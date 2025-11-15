package id.kampus.parkir.sistem_parkir.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Kendaraan
 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String platNomor;
    private boolean statusAktif;

    @ManyToOne
    @JoinColumn(name = "pengguna_id") // membuat relasi pengguna
    private Pengguna pengguna;
}