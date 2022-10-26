package or.example.rdesmarket_v10;

import android.widget.ImageView;

public class RubroObj {
    private int imgRubro;
    private String tv_titulo;

    public RubroObj(int imgRubro, String tv_titulo) {
        this.imgRubro = imgRubro;
        this.tv_titulo = tv_titulo;
    }

    public int getImgRubro() {
        return imgRubro;
    }

    public void setImgRubro(int imgRubro) {
        this.imgRubro = imgRubro;
    }

    public String getTv_titulo() {
        return tv_titulo;
    }

    public void setTv_titulo(String tv_titulo) {
        this.tv_titulo = tv_titulo;
    }
}
