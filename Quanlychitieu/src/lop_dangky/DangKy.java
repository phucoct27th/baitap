package lop_dangky;


public class DangKy {
    String tenDangnhap;
    String matKhau;
    String reMatkhau;
    public DangKy() {
        super();
    }
    public DangKy( String tenDangnhap, String matKhau,
                   String reMatkhau) {
        super();
        this.tenDangnhap = tenDangnhap;
        this.matKhau = matKhau;
        this.reMatkhau = reMatkhau;
    }

    public String getTenDangnhap() {
        return tenDangnhap;
    }
    public void setTenDangnhap(String tenDangnhap) {
        this.tenDangnhap = tenDangnhap;
    }
    public String getMatKhau() {
        return matKhau;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    public String getReMatkhau() {
        return reMatkhau;
    }
    public void setReMatkhau(String reMatkhau) {
        this.reMatkhau = reMatkhau;
    }
    @Override
    public String toString() {
        return "DangKy [ tenDangnhap=" + tenDangnhap
                + ", matKhau=" + matKhau + ", reMatkhau=" + reMatkhau + "]";
    }
}
