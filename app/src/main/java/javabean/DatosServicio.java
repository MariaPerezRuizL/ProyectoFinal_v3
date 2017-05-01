package javabean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class DatosServicio implements Serializable {


    private boolean activo;
    private int telf;
    private String categoria, fecha;
    private int dni;
    private String nombre, direccion;
    private double puntuacion;

    public DatosServicio(boolean activo, int telf, String categoria,
                         String fecha, int dni, String nombre,
                         String direccion, double puntuacion) {
        this.activo = activo;
        this.telf = telf;
        this.categoria = categoria;
        this.fecha = fecha;
        this.dni = dni;
        this.nombre = nombre;

        this.direccion = direccion;
        this.puntuacion = puntuacion;
    }
    public boolean isActivo() {
        return activo;
    }

    public int getTelf() {
        return telf;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFecha() {
        return fecha;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPuntuacion (double puntuacion) {
        this.puntuacion = puntuacion;
    }


    public JSONObject toJSONObject (DatosServicio serv) {
        JSONObject job = new JSONObject();
        try {
            job.put("activo", serv.isActivo());
            job.put("telf", serv.getTelf());
            job.put("categoria", serv.getCategoria());
            job.put("fecha", serv.getFecha());
            job.put("dni", serv.getDni());
            job.put("nombre", serv.getNombre());
            job.put("direccion", serv.getDireccion());
            job.put("puntuacion", serv.getPuntuacion());
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return job;
    }

    public DatosServicio fromJSONObject (JSONObject job) {
        DatosServicio serv = null;
        try {
            serv = new DatosServicio(job.getBoolean("activo"),
                    job.getInt("telf"),
                    job.getString("categoria"),
                    job.getString("fecha"),
                    job.getInt("dni"),
                    job.getString("nombre"),
                    job.getString("direccion"),
                    job.getDouble("puntuacion"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return serv;
    }

    public String toString(){
        return this.isActivo()+"/"+this.getTelf()+"/"+this.getCategoria()+"/"+
                this.getFecha()+"/"+this.getDni()+"/"+this.getNombre()+"/"+this.getDireccion()+"/"+this.getPuntuacion();
    }

/*    public String[] toString(DatosServicio serv){
        String[] servicio = {""+serv.isActivo(),""+serv.getTelf(),""+serv.getCategoria(),""+
                serv.getFecha(),""+serv.getDni(),""+serv.getNombre(),""+serv.getDireccion(),""+serv.getPuntuacion()};
        return servicio;
    }
    public DatosServicio fromString (String[] serv){
        DatosServicio servicio = new DatosServicio(Boolean.parseBoolean(serv[0]),Integer.parseInt(serv[1]),
                serv[2],serv[3],Integer.parseInt(serv[4]),serv[5],serv[6],Double.parseDouble(serv[7]));
        return servicio;
    }
    */
}

