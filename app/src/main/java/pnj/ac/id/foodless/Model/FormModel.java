package pnj.ac.id.foodless.Model;

public class FormModel {
    private String jenis;
    private String jenisMakanan;
    private String jumlahPorsi;
    private String kadaluarsa;
    private String keterangan;
    private String nama;
    private String telepon;
    private String email;
    private String alamat;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getJenisMakanan() {
        return jenisMakanan;
    }

    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }

    public String getJumlahPorsi() {
        return jumlahPorsi;
    }

    public void setJumlahPorsi(String jumlahPorsi) {
        this.jumlahPorsi = jumlahPorsi;
    }

    public String getKadaluarsa() {
        return kadaluarsa;
    }

    public void setKadaluarsa(String kadaluarsa) {
        this.kadaluarsa = kadaluarsa;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public FormModel(){

    }

    public FormModel(String jenis, String jenisMakanan, String jumlahPorsi, String kadaluarsa, String keterangan, String nama, String telepon, String email, String alamat){
        this.jenis = jenis;
        this.jenisMakanan = jenisMakanan;
        this.jumlahPorsi = jumlahPorsi;
        this.kadaluarsa = kadaluarsa;
        this.keterangan = keterangan;
        this.nama = nama;
        this.telepon = telepon;
        this.email = email;
        this.alamat = alamat;
    }

}
