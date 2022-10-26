package or.example.rdesmarket_v10;

import java.io.Serializable;

public class ProductoObj implements Serializable {
    private String precio;
    private String nombre;
    private String marca;
    private String rubro;
    private String subrubro;
    private int local;
    private String ean;



    public ProductoObj() {

    }

    public ProductoObj(String precio, String nombre, String marca, String rubro, String subrubro, int local, String ean) {
        this.precio = precio;
        this.nombre = nombre;
        this.marca = marca;
        this.rubro = rubro;
        this.subrubro = subrubro;
        this.local = local;
        this.ean = ean;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getSubrubro() {
        return subrubro;
    }

    public void setSubrubro(String subrubro) {
        this.subrubro = subrubro;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
}