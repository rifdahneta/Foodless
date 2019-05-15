package pnj.ac.id.foodless.Model;

public class Berita {
    private String judulBerita;
    private String isiBerita;
    private String gambarBerita;

    public Berita(String judulBerita, String isiBerita, String gambarBerita){
        this.judulBerita = judulBerita;
        this.isiBerita = isiBerita;
        this.gambarBerita = gambarBerita;
    }

    public Berita(){}

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }

    public String getGambarBerita() {
        return gambarBerita;
    }

    public void setGambarBerita(String gambarBerita) {
        this.gambarBerita = gambarBerita;
    }

}
