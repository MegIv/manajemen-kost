import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Penyewa implements IFilterable<Penyewa> {
    private String id;
    private String nama;
    private String kontak;
    private Kamar kamar;
    private List<Pembayaran> riwayatPembayaran;

    public Penyewa(String id, String nama, String kontak) {
        this.id = id;
        this.nama = nama;
        this.kontak = kontak;
        this.riwayatPembayaran = new ArrayList<>();
    }

    public Penyewa(String nama) {
        this("", nama, "");
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getKontak() {
        return kontak;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public List<Pembayaran> getRiwayatPembayaran() {
        return riwayatPembayaran;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public void setKamar(Kamar kamar) {
        this.kamar = kamar;
    }

    // Method lainnya
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

    public void tampilkanInfo() {
        System.out.println("ID: " + id + ", Nama: " + nama + ", Kontak: " + kontak);
        if (kamar != null) {
            System.out.print(" -> Menyewa kamar: ");
            kamar.tampilkanInfo();
        } else {
            System.out.println(" -> Belum menyewa kamar");
        }
    }

    // Implementasi IFilterable
    @Override
    public List<Penyewa> filterDariKriteria(Map<String, Object> criteria) {
        List<Penyewa> hasil = new ArrayList<>();
        hasil.add(this); // Untuk implementasi single object
        return hasil.stream()
                .filter(p -> {
                    boolean match = true;
                    if (criteria.containsKey("nama")) {
                        match = p.getNama().toLowerCase()
                                .contains(((String) criteria.get("nama")).toLowerCase());
                    }
                    if (match && criteria.containsKey("kontak")) {
                        match = p.getKontak().contains((String) criteria.get("kontak"));
                    }
                    return match;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Penyewa searchById(String id) {
        return this.id.equalsIgnoreCase(id) ? this : null;
    }
}