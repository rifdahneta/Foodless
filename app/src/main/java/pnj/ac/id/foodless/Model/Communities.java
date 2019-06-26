package pnj.ac.id.foodless.Model;

public class Communities {

    private String nama_komunitas;
    private String jenis_kegiatan;
    private String gambar_komunitas;
    private String notelp_komunitas;
    private String alamat_komunitas;
    private String email_komunitas;
    private String password_komunitas;
    private String jmlmember_komunitas;

    public Communities(String namaKomunitas, String alamatKomunitas, String notelpKomunitas, String emailKomunitas, String passKomunitas, String jmlMemberKomunitas) {
    }



    public String getNama_komunitas() {

        return nama_komunitas;
    }

    public void setNama_komunitas(String nama_komunitas) {

        this.nama_komunitas = nama_komunitas;
    }

    public String getJenis_kegiatan()
    {
        return jenis_kegiatan;
    }

    public void setJenis_kegiatan(String jenis_kegiatan)
    {
        this.jenis_kegiatan = jenis_kegiatan;
    }

    public String getGambar_komunitas() {

        return gambar_komunitas;
    }

    public void setGambar_komunitas(String gambar_komunitas) {

        this.gambar_komunitas = gambar_komunitas;
    }

    public String getnotelp_komunitas() {

        return notelp_komunitas;
    }

    public void setnotelp_komunitas(String notelp_komunitas) {

        this.notelp_komunitas = notelp_komunitas;
    }

    public String getAlamat_komunitas() {

        return alamat_komunitas;
    }

    public void setAlamat_komunitas(String alamat_komunitas) {
        this.alamat_komunitas = alamat_komunitas;
    }

    public String getEmail_komunitas() {

        return email_komunitas;
    }

    public void setEmail_komunitas(String email_komunitas) {
        this.email_komunitas = email_komunitas;
    }

    public String getPassword_komunitas() {
        return password_komunitas;
    }

    public void setPassword_komunitas(String password_komunitas) {
        this.password_komunitas = password_komunitas;
    }

    public String getJmlmember_komunitas() {
        return jmlmember_komunitas;
    }

    public void setJmlmember_komunitas(String jmlmember_komunitas) {
        this.jmlmember_komunitas = jmlmember_komunitas;
    }

//    public Communities(){
//
//    }
//
//
//    public Communities(String nama_komunitas, String jenis_kegiatan, String gambar_komunitas,
//                       String alamat_komunitas, String notelp_komunitas, String email_komunitas,
//                       String jmlmember_komunitas, String password_komunitas)
//    {

    public Communities(){

    }

    public Communities(String nama_komunitas, String jenis_kegiatan, String gambar_komunitas, String notelp_komunitas, String alamat_komunitas, String email_komunitas, String password_komunitas, String jumlah_member) {

        this.nama_komunitas = nama_komunitas;
        this.jenis_kegiatan = jenis_kegiatan;
        this.gambar_komunitas = gambar_komunitas;
        this.notelp_komunitas = notelp_komunitas;
        this.jmlmember_komunitas = jmlmember_komunitas;
        this.password_komunitas = password_komunitas;
        this.alamat_komunitas = alamat_komunitas;
        this.email_komunitas = email_komunitas;
        this.password_komunitas = password_komunitas;
    }

}
