import java.util.List;

public class LaporanKeuangan {

    // Hitung total pendapatan bulan tertentu
    public static double hitungPendapatanBulanan(List<Penyewa> daftarPenyewa, int bulan, int tahun) {
        return daftarPenyewa.stream()
            .flatMap(p -> p.getRiwayatPembayaran().stream())
            .filter(p -> p.isTerverifikasi())
            .filter(p -> p.getTanggal().getMonthValue() == bulan && p.getTanggal().getYear() == tahun)
            .mapToDouble(Pembayaran::getJumlah)
            .sum();
    }

    // Hitung total pendapatan tahun tertentu
    public static double hitungPendapatanTahunan(List<Penyewa> daftarPenyewa, int tahun) {
        return daftarPenyewa.stream()
            .flatMap(p -> p.getRiwayatPembayaran().stream())
            .filter(p -> p.isTerverifikasi())
            .filter(p -> p.getTanggal().getYear() == tahun)
            .mapToDouble(Pembayaran::getJumlah)
            .sum();
    }

    // Tampilkan laporan pendapatan bulanan
    public static void tampilkanLaporanBulanan(List<Penyewa> daftarPenyewa, int bulan, int tahun) {
        double total = hitungPendapatanBulanan(daftarPenyewa, bulan, tahun);
        System.out.println("=== LAPORAN BULANAN ===");
        System.out.printf("Periode: %02d/%d\n", bulan, tahun);
        System.out.printf("Total Pendapatan: Rp%.2f\n", total);
    }

    // Tampilkan laporan pendapatan tahunan
    public static void tampilkanLaporanTahunan(List<Penyewa> daftarPenyewa, int tahun) {
        double total = hitungPendapatanTahunan(daftarPenyewa, tahun);
        System.out.println("=== LAPORAN TAHUNAN ===");
        System.out.printf("Tahun: %d\n", tahun);
        System.out.printf("Total Pendapatan: Rp%.2f\n", total);
    }

    // Simulasi grafik: tampilkan pendapatan per bulan dalam satu tahun
    public static void tampilkanGrafikPendapatanTahunan(List<Penyewa> daftarPenyewa, int tahun) {
        System.out.println("=== GRAFIK PENDAPATAN TAHUNAN ===");
        for (int bulan = 1; bulan <= 12; bulan++) {
            double total = hitungPendapatanBulanan(daftarPenyewa, bulan, tahun);
            String bar = "*".repeat((int) total / 100000); // Setiap Rp100.000 = 1 '*'
            System.out.printf("%02d/%d: Rp%.2f %s\n", bulan, tahun, total, bar);
        }
    }

    // Tampilkan status pembayaran semua penyewa
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
