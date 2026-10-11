package com.example.tarea5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<ItemModel> lista;
    private int layoutResId;

    public CustomAdapter(Context context, int layoutResId, List<ItemModel> lista) {
        this.context = context;
        this.layoutResId = layoutResId;
        this.lista = lista;
    }

    @Override
    public int getCount() { return lista.size(); }

    @Override
    public Object getItem(int position) { return lista.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResId, parent, false);
        }

        ItemModel item = lista.get(position);

        ImageView img = convertView.findViewById(R.id.imgElemento);
        TextView txtTitulo = convertView.findViewById(R.id.txtTitulo);
        TextView txtSubtitulo = convertView.findViewById(R.id.txtSubtitulo);

        img.setImageResource(item.getImagenResId());
        txtTitulo.setText(item.getTitulo());
        if (txtSubtitulo != null) {
            txtSubtitulo.setText(item.getDescripcion());
        }

        return convertView;
    }
}