import java.util.Scanner;
import java.util.List;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ManajemenKamar mk = new ManajemenKamar();
        ManajemenPenyewa mp = new ManajemenPenyewa(mk);

        int menuUtama;
        do {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Manajemen Kamar");
            System.out.println("2. Pendaftaran & Manajemen Penyewa");
            System.out.println("3. Laporan Keuangan & Pembayaran");
            System.out.println("0. Keluar");
            menuUtama = getIntInput("Pilih menu utama: ");

            switch (menuUtama) {
                case 1 -> menuManajemenKamar(mk);
                case 2 -> menuManajemenPenyewa(mp);
                case 3 -> menuLaporanKeuangan(mp);
                case 0 -> System.out.println("Terima kasih telah menggunakan aplikasi manajemen kos.");
                default -> System.out.println("⚠️ Pilihan tidak valid. Coba lagi.");
            }
        } while (menuUtama != 0);
    }

    private static void menuManajemenKamar(ManajemenKamar mk) {
        int pilihan;
        do {
            System.out.println("\n--- Manajemen Kamar ---");
            System.out.println("1. Tambah Kamar");
            System.out.println("2. Lihat Semua Kamar");
            System.out.println("3. Cari Kamar");
            System.out.println("4. Update Kamar");
            System.out.println("5. Hapus Kamar");
            System.out.println("0. Kembali");
            pilihan = getIntInput("Pilih menu: ");

            switch (pilihan) {
                case 1 -> mk.tambahKamar();
                case 2 -> mk.tampilkanKamar();
                case 3 -> mk.cariKamar();
                case 4 -> mk.updateKamar();
                case 5 -> mk.hapusKamar();
                case 0 -> System.out.println("⬅️ Kembali ke menu utama...");
                default -> System.out.println("⚠️ Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void menuManajemenPenyewa(ManajemenPenyewa mp) {
        int pilihan;
        do {
            System.out.println("\n--- Pendaftaran & Manajemen Penyewa ---");
            System.out.println("1. Tambah Penyewa");
            System.out.println("2. Buat Kontrak Sewa");
            System.out.println("3. Update Data Penyewa");
            System.out.println("4. Tampilkan Penyewa Aktif");
            System.out.println("5. Cari Penyewa");
            System.out.println("6. Tambah Pembayaran");
            System.out.println("7. Verifikasi Pembayaran");
            System.out.println("8. Lihat Riwayat Pembayaran");
            System.out.println("9. Cari Pembayaran");
            System.out.println("0. Kembali");
            pilihan = getIntInput("Pilih menu: ");

            switch (pilihan) {
                case 1 -> mp.tambahPenyewa();
                case 2 -> mp.buatKontrakSewa();
                case 3 -> mp.updatePenyewa();
                case 4 -> mp.tampilkanPenyewaAktif();
                case 5 -> mp.cariPenyewa();
                case 6 -> tambahPembayaran(mp);
                case 7 -> verifikasiPembayaran(mp);
                case 8 -> tampilkanRiwayat(mp);
                case 9 -> cariPembayaran(mp);
                case 0 -> System.out.println("⬅️ Kembali ke menu utama...");
                default -> System.out.println("⚠️ Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void menuLaporanKeuangan(ManajemenPenyewa mp) {
        int pilihan;
        do {
            System.out.println("\n--- Menu Laporan Keuangan & Pembayaran ---");
            System.out.println("1. Laporan Pendapatan Bulanan");
            System.out.println("2. Laporan Pendapatan Tahunan");
            System.out.println("3. Grafik Pendapatan Tahunan");
            System.out.println("4. Status Pembayaran Penyewa");
            System.out.println("5. Cari Pembayaran Bulanan");
            System.out.println("6. Cari Pembayaran Tahunan");
            System.out.println("7. Cari Pembayaran berdasarkan Status");
            System.out.println("0. Kembali");
            pilihan = getIntInput("Pilih menu: ");

            switch (pilihan) {
                case 1 -> {
                    int bulan = getIntInput("Masukkan bulan (1-12): ");
                    int tahun = getIntInput("Masukkan tahun: ");
                    LaporanKeuangan.tampilkanLaporanBulanan(mp.getDaftarPenyewa(), bulan, tahun);
                }
                case 2 -> {
                    int tahun = getIntInput("Masukkan tahun: ");
                    LaporanKeuangan.tampilkanLaporanTahunan(mp.getDaftarPenyewa(), tahun);
                }
                case 3 -> {
                    int tahun = getIntInput("Masukkan tahun: ");
                    LaporanKeuangan.tampilkanGrafikPendapatanTahunan(mp.getDaftarPenyewa(), tahun);
                }
                case 4 -> LaporanKeuangan.tampilkanStatusPembayaran(mp.getDaftarPenyewa());
                case 5 -> {
                    int bulan = getIntInput("Masukkan bulan (1-12): ");
                    int tahun = getIntInput("Masukkan tahun: ");
                    List<Pembayaran> hasil = PencarianLaporan.cariByBulanTahun(mp.getDaftarPenyewa(), bulan, tahun);
                    PencarianLaporan.tampilkan(hasil);
                }
                case 6 -> {
                    int tahun = getIntInput("Masukkan tahun: ");
                    List<Pembayaran> hasil = PencarianLaporan.cariByTahun(mp.getDaftarPenyewa(), tahun);
                    PencarianLaporan.tampilkan(hasil);
                }
                case 7 -> {
                    String statusStr = getStringInput("Status (lunas / belum): ").toLowerCase();
                    if (!statusStr.equals("lunas") && !statusStr.equals("belum")) {
                        System.out.println("⚠️ Status tidak valid. Gunakan 'lunas' atau 'belum'.");
                        break;
                    }
                    boolean lunas = statusStr.equals("lunas");
                    List<Pembayaran> hasil = PencarianLaporan.cariByStatus(mp.getDaftarPenyewa(), lunas);
                    PencarianLaporan.tampilkan(hasil);
                }
                case 0 -> System.out.println("⬅️ Kembali ke menu utama...");
                default -> System.out.println("⚠️ Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void tambahPembayaran(ManajemenPenyewa mp) {
        String idPenyewa = getStringInput("Masukkan ID Penyewa: ");
        Penyewa penyewa = mp.cariPenyewaById(idPenyewa); 

        if (penyewa == null) {
            System.out.println("❌ Penyewa tidak ditemukan.");
            return;
        }

        double jumlah = getDoubleInput("Jumlah pembayaran: ");
        String metode = getStringInput("Metode (Tunai / Transfer Bank): ");

        Pembayaran pembayaran = new Pembayaran(idPenyewa, jumlah, metode);
        penyewa.tambahPembayaran(pembayaran);
        System.out.println("✅ Pembayaran berhasil ditambahkan (belum diverifikasi).");
    }



    private static void verifikasiPembayaran(ManajemenPenyewa mp) {
        String idPenyewa = getStringInput("Masukkan ID Penyewa: ");
        Penyewa penyewa = mp.cariPenyewaById(idPenyewa); 

        if (penyewa == null) {
            System.out.println("❌ Penyewa tidak ditemukan.");
            return;
        }

        var riwayat = penyewa.getRiwayatPembayaran();
        for (int i = 0; i < riwayat.size(); i++) {
            System.out.println((i + 1) + ". " + riwayat.get(i));
        }

        int nomor = getIntInput("Nomor pembayaran: ");
        if (nomor < 1 || nomor > riwayat.size()) {
            System.out.println("❌ Nomor tidak valid.");
            return;
        }

        riwayat.get(nomor - 1).verifikasi();
        System.out.println("✅ Pembayaran berhasil diverifikasi.");
    }

    private static void tampilkanRiwayat(ManajemenPenyewa mp) {
        String id = getStringInput("Masukkan ID Penyewa: ");
        Penyewa penyewa = mp.cariPenyewaById(id);
        if (penyewa != null) penyewa.tampilkanRiwayatPembayaran();
        else System.out.println("❌ Penyewa tidak ditemukan.");
    }

    private static void cariPembayaran(ManajemenPenyewa mp) {
        System.out.println("Cari berdasarkan:");
        System.out.println("1. Nama Penyewa");
        System.out.println("2. Status Pembayaran");
        int pilih = getIntInput("Pilih: ");

        switch (pilih) {
            case 1 -> {
                String nama = getStringInput("Masukkan nama penyewa: ");
                var hasil = PencarianPembayaran.cariByPenyewa(mp.getDaftarPenyewa(), nama);
                PencarianPembayaran.tampilkanPembayaran(hasil);
            }
            case 2 -> {
                String statusStr = getStringInput("Status (lunas/belum): ").toLowerCase();
                boolean status = statusStr.equals("lunas");
                var hasil = PencarianPembayaran.filterByStatus(mp.getDaftarPenyewa(), status);
                PencarianPembayaran.tampilkanPembayaran(hasil);
            }
            default -> System.out.println("⚠️ Pilihan tidak valid.");
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Masukkan angka yang valid.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Masukkan angka desimal yang valid.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
