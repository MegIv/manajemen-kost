public class KontrakSewa {
    private Penyewa penyewa;
    private Kamar kamar;

    public KontrakSewa(Penyewa penyewa, Kamar kamar) {
        this.penyewa = penyewa;
        this.kamar = kamar;
        this.kamar.setTersedia(false);
        this.penyewa.setKamar(kamar);
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public Kamar getKamar() {
        return kamar;
    }
}
