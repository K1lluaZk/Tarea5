package com.example.tarea5;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class FragmentGridView extends Fragment {

    private GridView gridView;
    private EditText etFiltro;
    private TextView tvResultado;
    private List<ItemModel> listaAnimales;
    private CustomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gridview, container, false);

        gridView = view.findViewById(R.id.gridView);
        etFiltro = view.findViewById(R.id.etFiltro);
        tvResultado = view.findViewById(R.id.tvResultado);

        listaAnimales = new ArrayList<>();
        listaAnimales.add(new ItemModel("León", "Felino", R.drawable.img_leon));
        listaAnimales.add(new ItemModel("Águila", "Ave", R.drawable.img_aguila));
        listaAnimales.add(new ItemModel("Delfín", "Mamífero acuático", R.drawable.img_delfin));
        listaAnimales.add(new ItemModel("Lobo", "Cánido", R.drawable.img_lobo));

        adapter = new CustomAdapter(requireContext(), R.layout.item_grid, listaAnimales);
        gridView.setAdapter(adapter);

        // actualizar TextView en tiempo real según el EditText
        etFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvResultado.setText("Búsqueda actual: " + s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // evento al hacer clic en una celda de la cuadrícula
        gridView.setOnItemClickListener((parent, v, position, id) -> {
            ItemModel seleccionado = listaAnimales.get(position);
            tvResultado.setText("Seleccionado de la cuadrícula: " + seleccionado.getTitulo());
        });

        return view;
    }
}