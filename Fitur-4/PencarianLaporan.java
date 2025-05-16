import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Fitur5.IFilterable;
import Fitur5.PencarianBase;

public class PencarianLaporan extends PencarianBase<Pembayaran> implements IFilterable<Pembayaran> {

    private List<Penyewa> daftarPenyewa;

    public PencarianLaporan(List<Penyewa> daftarPenyewa) {
        super(new ArrayList<>()); // dataSource kosong, pencarian langsung lewat daftarPenyewa
        this.daftarPenyewa = daftarPenyewa;
        for (Penyewa p : daftarPenyewa) {
            this.dataSource.addAll(p.getRiwayatPembayaran());
        }
    }

    // ===================== Pencarian Berdasarkan Kriteria =======================

    @Override
    public List<Pembayaran> filterDariKriteria(Map<String, Object> criteria) {
        List<Pembayaran> hasil = new ArrayList<>();

        for (Penyewa penyewa : daftarPenyewa) {
            for (Pembayaran pembayaran : penyewa.getRiwayatPembayaran()) {
                boolean cocok = true;

                if (criteria.containsKey("tahun") &&
                    pembayaran.getTanggal().getYear() != (int) criteria.get("tahun")) {
                    cocok = false;
                }

                if (criteria.containsKey("bulan") &&
                    pembayaran.getTanggal().getMonthValue() != (int) criteria.get("bulan")) {
                    cocok = false;
                }

                if (criteria.containsKey("verifikasi") &&
                    pembayaran.isTerverifikasi() != (boolean) criteria.get("verifikasi")) {
                    cocok = false;
                }

                if (cocok) {
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

    // ===================== Metode Pencarian Spesifik (asli) =======================

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
                hasil.addAll(p.getRiwayatPembayaran()); // atau bisa tambahkan hanya yang terverifikasi
            }
        }
        return hasil;
    }

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
