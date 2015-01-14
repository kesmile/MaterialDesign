package com.test.kesmile.materialdesign.beans;

import android.graphics.Bitmap;

/**
 * Created by root on 14/01/15.
 */
public class BeanFragment {
    private String titulo;
    private String description;
    private Bitmap imagen;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
