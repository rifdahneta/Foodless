package pnj.ac.id.foodless.Model;

public class Communities {

    private String nama_komunitas;
    private String jenis_kegiatan;
    private String gambar_komunitas;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    private String refId;


    public String getNama_komunitas() {
        return nama_komunitas;
    }

    public void setNama_komunitas(String nama_komunitas) {
        this.nama_komunitas = nama_komunitas;
    }

    public String getJenis_kegiatan() {
        return jenis_kegiatan;
    }

    public void setJenis_kegiatan(String jenis_kegiatan) {
        this.jenis_kegiatan = jenis_kegiatan;
    }

    public String getGambar_komunitas() {
        return gambar_komunitas;
    }

    public void setGambar_komunitas(String gambar_komunitas) {
        this.gambar_komunitas = gambar_komunitas;
    }

    public Communities(){

    }
    public Communities(String nama_komunitas, String jenis_kegiatan, String gambar_komunitas)
    {
        this.nama_komunitas = nama_komunitas;
        this.jenis_kegiatan = jenis_kegiatan;
        this.gambar_komunitas = gambar_komunitas;
    }
}
