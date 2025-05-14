import java.util.ArrayList;
import java.util.List;

public class PencarianLaporan {

    // Cari semua pembayaran dari semua penyewa berdasarkan tahun
    public static List<Pembayaran> cariByTahun(List<Penyewa> daftarPenyewa, int tahun) {
        List<Pembayaran> hasil = new ArrayList<>();
        for (Penyewa penyewa : daftarPenyewa) {
            for (Pembayaran pembayaran : penyewa.getRiwayatPembayaran()) {
                if (pembayaran.getTanggal().getYear() == tahun) {
                    hasil.add(pembayaran);
                }
            }
        }
        return hasil;
    }

    // Cari semua pembayaran dari semua penyewa berdasarkan bulan dan tahun
    public static List<Pembayaran> cariByBulanTahun(List<Penyewa> daftarPenyewa, int bulan, int tahun) {
        List<Pembayaran> hasil = new ArrayList<>();
        for (Penyewa penyewa : daftarPenyewa) {
            for (Pembayaran pembayaran : penyewa.getRiwayatPembayaran()) {
                if (pembayaran.getTanggal().getYear() == tahun &&
                    pembayaran.getTanggal().getMonthValue() == bulan) {
                    hasil.add(pembayaran);
                }
            }
        }
        return hasil;
    }

    // Cari semua pembayaran dari semua penyewa berdasarkan status verifikasi (lunas / belum)
    public static List<Pembayaran> cariByStatus(List<Penyewa> daftarPenyewa, boolean lunas) {
        List<Pembayaran> hasil = new ArrayList<>();
        for (Penyewa p : daftarPenyewa) {
            double total = 0;
            for (Pembayaran bayar : p.getRiwayatPembayaran()) {
                if (bayar.isTerverifikasi()) {
                    total += bayar.getJumlah();
                }
            }

            double hargaKamar = (p.getKamar() != null) ? p.getKamar().getHarga() : 0;
            boolean status = (total >= hargaKamar);
            if (status == lunas) {
                hasil.addAll(p.getRiwayatPembayaran()); // atau tambahkan hanya yang terverifikasi
            }
            
        }
        return hasil;
    }

    // Menampilkan hasil laporan
    public static void tampilkan(List<Pembayaran> laporan) {
        if (laporan == null || laporan.isEmpty()) {
            System.out.println("Tidak ada data ditemukan untuk kriteria tersebut.");
            return;
        }
        for (Pembayaran pembayaran : laporan) {
            System.out.println(pembayaran);
        }
    }
}

