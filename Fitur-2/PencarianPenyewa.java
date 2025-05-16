import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PencarianPenyewa extends PencarianBase<Penyewa> {
    public PencarianPenyewa(List<Penyewa> dataSource) {
        super(dataSource);
    }

    @Override
    public List<Penyewa> filterDariKriteria(Map<String, Object> criteria) {
        return dataSource.stream()
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
        return cariById(new ArrayList<>(dataSource), id);
    }

    // Metode statis yang sudah ada (untuk kompatibilitas)
    public static Penyewa cariById(ArrayList<Penyewa> daftar, String id) {
        return daftar.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public static Penyewa cariByNama(ArrayList<Penyewa> daftar, String nama) {
        return daftar.stream()
                .filter(p -> p.getNama().equalsIgnoreCase(nama))
                .findFirst()
                .orElse(null);
    }
}