package projek;

import java.time.LocalDate;

public class Pembayaran {
    private double jumlah;
    private String metode; // Tunai / Transfer Bank
    private LocalDate tanggal;
    private boolean terverifikasi;

    public Pembayaran(double jumlah, String metode) {
        this.jumlah = jumlah;
        this.metode = metode;
        this.tanggal = LocalDate.now();
        this.terverifikasi = false;
    }

    public void verifikasi() {
        this.terverifikasi = true;
    }

    public boolean isTerverifikasi() {
        return terverifikasi;
    }

    public double getJumlah() {
        return jumlah;
    }

    public String getMetode() {
        return metode;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    @Override
    public String toString() {
        return "Tanggal: " + tanggal + ", Jumlah: " + jumlah + ", Metode: " + metode + ", Terverifikasi: " + terverifikasi;
    }
}

