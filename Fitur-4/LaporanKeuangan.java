import Fitur5.IFilterable;
import Fitur5.PencarianBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LaporanKeuangan extends PencarianBase<Pembayaran> implements IFilterable<Pembayaran> {

    private List<Penyewa> daftarPenyewa;

    public LaporanKeuangan(List<Penyewa> daftarPenyewa) {
        super(new ArrayList<>()); // dataSource akan diisi dari riwayat pembayaran
        this.daftarPenyewa = daftarPenyewa;
        for (Penyewa p : daftarPenyewa) {
            this.dataSource.addAll(p.getRiwayatPembayaran());
        }
    }

    // ===================== Implementasi PencarianBase =======================

    @Override
    public List<Pembayaran> filterDariKriteria(Map<String, Object> criteria) {
        List<Pembayaran> hasil = new ArrayList<>();

        for (Penyewa penyewa : daftarPenyewa) {
            for (Pembayaran pembayaran : penyewa.getRiwayatPembayaran()) {
                boolean match = true;

                if (criteria.containsKey("bulan") &&
                        pembayaran.getTanggal().getMonthValue() != (int) criteria.get("bulan")) {
                    match = false;
                }

                if (criteria.containsKey("tahun") &&
                        pembayaran.getTanggal().getYear() != (int) criteria.get("tahun")) {
                    match = false;
                }

                if (criteria.containsKey("verifikasi") &&
                        pembayaran.isTerverifikasi() != (boolean) criteria.get("verifikasi")) {
                    match = false;
                }

                if (match) {
                    hasil.add(pembayaran);
                }
            }
        }

        return hasil;
    }

    @Override
    public Pembayaran searchById(String id) {
        for (Penyewa penyewa : daftarPenyewa) {
            for (Pembayaran pembayaran : penyewa.getRiwayatPembayaran()) {
                if (pembayaran.getId().equalsIgnoreCase(id)) {
                    return pembayaran;
                }
            }
        }
        return null;
    }

    // ===================== Fitur Laporan Asli =======================

    public static double hitungPendapatanBulanan(List<Penyewa> daftarPenyewa, int bulan, int tahun) {
        return daftarPenyewa.stream()
            .flatMap(p -> p.getRiwayatPembayaran().stream())
            .filter(Pembayaran::isTerverifikasi)
            .filter(p -> p.getTanggal().getMonthValue() == bulan && p.getTanggal().getYear() == tahun)
            .mapToDouble(Pembayaran::getJumlah)
            .sum();
    }

    public static double hitungPendapatanTahunan(List<Penyewa> daftarPenyewa, int tahun) {
        return daftarPenyewa.stream()
            .flatMap(p -> p.getRiwayatPembayaran().stream())
            .filter(Pembayaran::isTerverifikasi)
            .filter(p -> p.getTanggal().getYear() == tahun)
            .mapToDouble(Pembayaran::getJumlah)
            .sum();
    }

    public static void tampilkanLaporanBulanan(List<Penyewa> daftarPenyewa, int bulan, int tahun) {
        double total = hitungPendapatanBulanan(daftarPenyewa, bulan, tahun);
        System.out.println("=== LAPORAN BULANAN ===");
        System.out.printf("Periode: %02d/%d\n", bulan, tahun);
        System.out.printf("Total Pendapatan: Rp%.2f\n", total);
    }

    public static void tampilkanLaporanTahunan(List<Penyewa> daftarPenyewa, int tahun) {
        double total = hitungPendapatanTahunan(daftarPenyewa, tahun);
        System.out.println("=== LAPORAN TAHUNAN ===");
        System.out.printf("Tahun: %d\n", tahun);
        System.out.printf("Total Pendapatan: Rp%.2f\n", total);
    }

    public static void tampilkanGrafikPendapatanTahunan(List<Penyewa> daftarPenyewa, int tahun) {
        System.out.println("=== GRAFIK PENDAPATAN TAHUNAN ===");
        for (int bulan = 1; bulan <= 12; bulan++) {
            double total = hitungPendapatanBulanan(daftarPenyewa, bulan, tahun);
            String bar = "*".repeat((int) total / 100000);
            System.out.printf("%02d/%d: Rp%.2f %s\n", bulan, tahun, total, bar);
        }
    }

    public static void tampilkanStatusPembayaran(List<Penyewa> daftarPenyewa) {
        System.out.println("\n=== STATUS PEMBAYARAN PENYEWA ===");
        for (Penyewa p : daftarPenyewa) {
            double totalTerbayar = p.getRiwayatPembayaran().stream()
                    .filter(Pembayaran::isTerverifikasi)
                    .mapToDouble(Pembayaran::getJumlah)
                    .sum();

            double hargaKamar = (p.getKamar() != null) ? p.getKamar().getHarga() : 0;
            String status = (totalTerbayar >= hargaKamar) ? "Lunas" : "Belum Lunas";
            double sisa = (totalTerbayar >= hargaKamar) ? 0 : hargaKamar - totalTerbayar;

            System.out.printf("Nama: %s | Harga Kamar: Rp%.2f | Dibayar: Rp%.2f | Status: %s",
                    p.getNama(), hargaKamar, totalTerbayar, status);
            if (sisa > 0) {
                System.out.printf(" | Sisa: Rp%.2f", sisa);
            }
            System.out.println();
        }
    }
}
