import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Fitur5.IFilterable;

public class ManajemenPenyewa implements IFilterable<Penyewa> {
    private ArrayList<Penyewa> daftarPenyewa = new ArrayList<>();
    private ArrayList<KontrakSewa> daftarKontrak = new ArrayList<>();
    private ManajemenKamar manajemenKamar;
    private Scanner input = new Scanner(System.in);

    public ManajemenPenyewa(ManajemenKamar manajemenKamar) {
        this.manajemenKamar = manajemenKamar;
    }

    public void tambahPenyewa() {
        System.out.print("ID Penyewa: ");
        String id = input.nextLine();
        System.out.print("Nama Penyewa: ");
        String nama = input.nextLine();
        System.out.print("Kontak: ");
        String kontak = input.nextLine();

        Penyewa penyewa = new Penyewa(id, nama, kontak);
        daftarPenyewa.add(penyewa);
        System.out.println("Penyewa berhasil ditambahkan.");
    }

    public void buatKontrakSewa() {
        System.out.print("ID Penyewa: ");
        String id = input.nextLine();
        Penyewa penyewa = PencarianPenyewa.cariById(daftarPenyewa, id);
        if (penyewa == null) {
            System.out.println("Penyewa tidak ditemukan.");
            return;
        }

        System.out.print("ID Kamar yang ingin disewa: ");
        String idKamar = input.nextLine();

        for (Kamar k : manajemenKamar.getDaftarKamar()) {
            if (k.getId().equalsIgnoreCase(idKamar) && k.isTersedia()) {
                KontrakSewa kontrak = new KontrakSewa(penyewa, k);
                daftarKontrak.add(kontrak);
                System.out.println("Kontrak sewa berhasil dibuat.");
                return;
            }
        }
        System.out.println("Kamar tidak ditemukan atau tidak tersedia.");
    }

    public void updatePenyewa() {
        System.out.print("Masukkan ID penyewa: ");
        String id = input.nextLine();
        Penyewa penyewa = PencarianPenyewa.cariById(daftarPenyewa, id);
        if (penyewa != null) {
            System.out.print("Nama baru: ");
            penyewa.setNama(input.nextLine());
            System.out.print("Kontak baru: ");
            penyewa.setKontak(input.nextLine());
            System.out.println("Data penyewa berhasil diperbarui.");
        } else {
            System.out.println("Penyewa tidak ditemukan.");
        }
    }

    public void tampilkanPenyewaAktif() {
        boolean ada = false;
        for (KontrakSewa ks : daftarKontrak) {
            ks.getPenyewa().tampilkanInfo();
            ada = true;
        }
        if (!ada) {
            System.out.println("Belum ada penyewa aktif.");
        }
    }

    public void cariPenyewa() {
        System.out.print("Cari berdasarkan (1) ID atau (2) Nama: ");
        int opsi = input.nextInt();
        input.nextLine();
        Penyewa hasil = null;
        if (opsi == 1) {
            System.out.print("Masukkan ID: ");
            hasil = PencarianPenyewa.cariById(daftarPenyewa, input.nextLine());
        } else if (opsi == 2) {
            System.out.print("Masukkan Nama: ");
            hasil = PencarianPenyewa.cariByNama(daftarPenyewa, input.nextLine());
        }

        if (hasil != null) {
            hasil.tampilkanInfo();
        } else {
            System.out.println("Penyewa tidak ditemukan.");
        }
    }

    public Penyewa cariPenyewaByNama(String nama) {
        for (Penyewa p : daftarPenyewa) {
            if (p.getNama().equalsIgnoreCase(nama)) {
                return p;
            }
        }
        return null;
    }

    public Penyewa cariPenyewaById(String id) {
        for (Penyewa penyewa : daftarPenyewa) {
            if (penyewa.getId().equalsIgnoreCase(id)) {
                return penyewa;
            }
        }
        return null;
    }

    public ArrayList<Penyewa> getDaftarPenyewa() {
        return daftarPenyewa;
    }

    // Implementasi fitur 5: IFilterable
    @Override
    public Penyewa searchById(String id) {
        for (Penyewa p : daftarPenyewa) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Penyewa> filterDariKriteria(Map<String, Object> criteria) {
        List<Penyewa> hasil = new ArrayList<>();
        for (Penyewa p : daftarPenyewa) {
            boolean cocok = true;
            for (Map.Entry<String, Object> entri : criteria.entrySet()) {
                String kunci = entri.getKey().toLowerCase();
                String nilai = entri.getValue().toString().toLowerCase();

                switch (kunci) {
                    case "id":
                        if (!p.getId().toLowerCase().contains(nilai)) cocok = false;
                        break;
                    case "nama":
                        if (!p.getNama().toLowerCase().contains(nilai)) cocok = false;
                        break;
                    case "kontak":
                        if (!p.getKontak().toLowerCase().contains(nilai)) cocok = false;
                        break;
                    default:
                        cocok = false;
                }

                if (!cocok) break;
            }
            if (cocok) {
                hasil.add(p);
            }
        }
        return hasil;
    }
}