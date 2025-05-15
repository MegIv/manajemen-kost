import java.util.ArrayList;
import java.util.Scanner;

public class ManajemenKamar extends BaseManajemen implements Filterable {
    private ArrayList<Kamar> daftarKamar = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void tambahKamar() {
        System.out.print("ID Kamar: ");
        String id = input.nextLine();
        System.out.print("Jenis Kamar: ");
        String jenis = input.nextLine();
        System.out.print("Harga Kamar: ");
        int harga = input.nextInt();
        System.out.print("Tersedia? (True/False): ");
        boolean tersedia = input.nextBoolean();
        input.nextLine();

        Kamar kamar = new Kamar(id, jenis, harga, tersedia);
        daftarKamar.add(kamar);
        System.out.println("Kamar berhasil ditambahkan");
    }

    public void updateKamar() {
        System.out.print("Masukkan ID Kamar yang ingin diperbarui: ");
        String id = input.nextLine();
        for (Kamar k : daftarKamar) {
            if (k.getId().equalsIgnoreCase(id)) {
                System.out.println("Harga baru: ");
                k.setHarga(input.nextInt());
                System.out.println("Tersedia? (True/False): ");
                k.setTersedia(input.nextBoolean());
                input.nextLine();
                System.out.println("Data kamar berhasil diperbarui");
                return;
            }
        }
        System.out.println("Kamar tidak ditemukan");
    }

    public void hapusKamar() {
        System.out.print("Masukkan ID Kamar yang ingin dihapus: ");
        String id = input.nextLine();
        daftarKamar.removeIf(k -> k.getId().equalsIgnoreCase(id));
        System.out.println("Jika ditemukan, Kamar berhasil dihapus");
    }

    @Override
    public void tampilkanKamar() {
        if (daftarKamar.isEmpty()) {
            System.out.println("Belum ada data kamar");
        } else {
            for (Kamar k : daftarKamar) {
                k.tampilkanInfo();
            }
        }
    }

    @Override
    public void cariKamar() {
        System.out.print("Masukkan harga maksimal: ");
        int max = input.nextInt();
        input.nextLine();
        boolean ditemukan = false;
        for (Kamar k : daftarKamar) {
            if (k.getHarga() <= max && k.isTersedia()) {
                k.tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Tidak ada kamar yang sesuai");
    }

    public ArrayList<Kamar> getDaftarKamar() {
        return daftarKamar;
    }
}
