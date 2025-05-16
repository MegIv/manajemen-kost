import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManajemenKamar mk = new ManajemenKamar();
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== Menu Manajemen Kamar Kost ===");
            System.out.println("1. Tambah Kamar");
            System.out.println("2. Lihat Semua Kamar");
            System.out.println("3. Cari Kamar");
            System.out.println("4. Update Kamar");
            System.out.println("5. Hapus Kamar");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu : ");
            pilihan = sc.nextInt();
            sc.nextLine();

            switch (pilihan) {
                case 1 -> mk.tambahKamar();
                case 2 -> mk.tampilkanKamar();
                case 3 -> mk.cariKamar();
                case 4 -> mk.updateKamar();
                case 5 -> mk.hapusKamar();
                case 0 -> System.out.println("Program selesai.");
                default -> System.out.println("Pilih tidak valid.");
            }
        } while (pilihan != 0);
    }
}
