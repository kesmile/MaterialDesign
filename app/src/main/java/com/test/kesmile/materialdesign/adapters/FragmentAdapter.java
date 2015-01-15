package com.test.kesmile.materialdesign.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.kesmile.materialdesign.R;
import com.test.kesmile.materialdesign.beans.BeanFragment;

import java.util.ArrayList;

/**
 * Created by root on 14/01/15.
 */
public class FragmentAdapter extends BaseAdapter {
    private ArrayList<BeanFragment> listabeans;
    private LayoutInflater linflate;

    public FragmentAdapter( Context c,ArrayList<BeanFragment> listabeans) {
        this.listabeans = listabeans;
        this.linflate = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return listabeans.size();
    }

    @Override
    public Object getItem(int position) {

        return listabeans.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder holder;
            if(convertView == null){
                convertView = this.linflate.inflate(R.layout.listview_adapter_fragment_main,parent,false);
                holder = new viewholder();
                holder.titulo = (TextView) convertView.findViewById(R.id.txt_Titulo);
                holder.descripcion = (TextView) convertView.findViewById(R.id.txt_desc);
                holder.imagen = (ImageView) convertView.findViewById(R.id.imageView);
            }else{
                holder = (viewholder) convertView.getTag();
            }
                BeanFragment d = listabeans.get(position);
                holder.titulo.setText(d.getTitulo());
                holder.descripcion.setText(d.getDescription());
                holder.imagen.setImageBitmap(d.getImagen());
            return convertView;
    }
    public static class viewholder{
        TextView titulo;
        TextView descripcion;
        ImageView imagen;
    }
}
