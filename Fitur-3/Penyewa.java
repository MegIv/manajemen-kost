package projek;

import java.util.ArrayList;
import java.util.List;

public class Penyewa {
    private String nama;
    private List<Pembayaran> riwayatPembayaran;

    public Penyewa(String nama) {
        this.nama = nama;
        this.riwayatPembayaran = new ArrayList<>();
    }

    public void tambahPembayaran(Pembayaran pembayaran) {
        riwayatPembayaran.add(pembayaran);
    }

    public void tampilkanRiwayatPembayaran() {
        System.out.println("Riwayat Pembayaran untuk: " + nama);
        for (Pembayaran p : riwayatPembayaran) {
            System.out.println(p);
        }
    }

    public boolean statusPembayaranLunas(double totalTagihan) {
        double totalBayar = 0;
        for (Pembayaran p : riwayatPembayaran) {
            if (p.isTerverifikasi()) {
                totalBayar += p.getJumlah();
            }
        }
        return totalBayar >= totalTagihan;
    }

    public String getNama() {
        return nama;
    }
}

