package com.example.tarea5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class FragmentListView extends Fragment {

    private Spinner spinnerCategorias;
    private ListView listView;
    private CustomAdapter adapter;
    private List<ItemModel> datosMostrados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);

        spinnerCategorias = view.findViewById(R.id.spinnerCategorias);
        listView = view.findViewById(R.id.listView);
        datosMostrados = new ArrayList<>();

        // configuración del Spinner
        String[] categorias = {"Todos", "Personajes", "Destinos"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, categorias);
        spinnerCategorias.setAdapter(spinnerAdapter);

        // adaptador para la lista
        adapter = new CustomAdapter(requireContext(), R.layout.item_list, datosMostrados);
        listView.setAdapter(adapter);

        // evento Spinner
        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                cargarDatos(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // evento click en elemento de la lista
        listView.setOnItemClickListener((parent, v, position, id) -> {
            ItemModel seleccionado = datosMostrados.get(position);
            Toast.makeText(getContext(), "Seleccionado: " + seleccionado.getTitulo(), Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void cargarDatos(int categoriaIndex) {
        datosMostrados.clear();
        if (categoriaIndex == 0 || categoriaIndex == 1) {
            datosMostrados.add(new ItemModel("Héroe", "Guerrero principal", R.drawable.img_heroe));
            datosMostrados.add(new ItemModel("Mago", "Dominante del fuego", R.drawable.img_mago));
        }
        if (categoriaIndex == 0 || categoriaIndex == 2) {
            datosMostrados.add(new ItemModel("Playa Paraíso", "Caribe tropical", R.drawable.img_playa));
            datosMostrados.add(new ItemModel("Montaña Alta", "Senderismo y nieve", R.drawable.img_montana));
        }
        adapter.notifyDataSetChanged();
    }
}