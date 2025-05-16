import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Fitur5.PencarianBase;
import Fitur5.IFilterable;

public class ManajemenKamar extends PencarianBase<Kamar> implements Filterable, IFilterable<Kamar> {
    private ArrayList<Kamar> daftarKamar = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public ManajemenKamar() {
        super(new ArrayList<>());
        this.dataSource = daftarKamar;
    }

    public void tambahKamar() {
        System.out.print("ID Kamar: ");
        String id = input.nextLine();

        String jenis = pilihJenisKamar();

        System.out.print("Harga Kamar: ");
        int harga = input.nextInt();
        input.nextLine();

        String pilihan;
        do {
            System.out.print("Tersedia? (y/n): ");
            pilihan = input.nextLine().trim().toLowerCase();
        } while (!pilihan.equals("y") && !pilihan.equals("n"));
        boolean tersedia = pilihan.equals("y");

        Kamar kamar = new Kamar(id, jenis, harga, tersedia);
        daftarKamar.add(kamar);
        System.out.println("✅ Kamar berhasil ditambahkan");
    }

    public void updateKamar() {
        System.out.print("Masukkan ID Kamar yang ingin diperbarui: ");
        String id = input.nextLine();
        for (Kamar k : daftarKamar) {
            if (k.getId().equalsIgnoreCase(id)) {
                System.out.print("Harga baru: ");
                k.setHarga(input.nextInt());
                input.nextLine();

                String pilihan;
                do {
                    System.out.print("Tersedia? (y/n): ");
                    pilihan = input.nextLine().trim().toLowerCase();
                } while (!pilihan.equals("y") && !pilihan.equals("n"));
                k.setTersedia(pilihan.equals("y"));

                System.out.println("✅ Data kamar berhasil diperbarui");
                return;
            }
        }
        System.out.println("❌ Kamar tidak ditemukan");
    }

    public void hapusKamar() {
        System.out.print("Masukkan ID Kamar yang ingin dihapus: ");
        String id = input.nextLine();
        boolean removed = daftarKamar.removeIf(k -> k.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("✅ Kamar berhasil dihapus");
        } else {
            System.out.println("❌ Kamar tidak ditemukan");
        }
    }

    @Override
    public void tampilkanKamar() {
        if (daftarKamar.isEmpty()) {
            System.out.println("⚠️ Belum ada data kamar");
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
        if (!ditemukan) System.out.println("❌ Tidak ada kamar yang sesuai");
    }

    // ========== Pilihan Jenis Kamar ==========
    private String pilihJenisKamar() {
        while (true) {
            System.out.println("Pilih Jenis Kamar:");
            System.out.println("1. Besar");
            System.out.println("2. Sedang");
            System.out.println("3. Kecil");
            System.out.print("Pilihan (1-3): ");
            String inputJenis = input.nextLine().trim();

            switch (inputJenis) {
                case "1":
                    return "Besar";
                case "2":
                    return "Sedang";
                case "3":
                    return "Kecil";
                default:
                    System.out.println("⚠️ Pilihan tidak valid. Silakan pilih 1, 2, atau 3.");
            }
        }
    }

    // ===================== IFilterable =====================
    @Override
    public List<Kamar> filterDariKriteria(Map<String, Object> criteria) {
        List<Kamar> hasil = new ArrayList<>();
        for (Kamar kamar : daftarKamar) {
            boolean cocok = true;
            for (String key : criteria.keySet()) {
                Object value = criteria.get(key);
                if (key.equalsIgnoreCase("jenis") && !kamar.getJenis().equalsIgnoreCase((String) value)) {
                    cocok = false;
                    break;
                }
                if (key.equalsIgnoreCase("harga") && kamar.getHarga() > (int) value) {
                    cocok = false;
                    break;
                }
                if (key.equalsIgnoreCase("tersedia") && kamar.isTersedia() != (boolean) value) {
                    cocok = false;
                    break;
                }
            }
            if (cocok) {
                hasil.add(kamar);
            }
        }
        return hasil;
    }

    @Override
    public Kamar searchById(String id) {
        for (Kamar kamar : daftarKamar) {
            if (kamar.getId().equalsIgnoreCase(id)) {
                return kamar;
            }
        }
        return null;
    }

    public ArrayList<Kamar> getDaftarKamar() {
        return daftarKamar;
    }
}
