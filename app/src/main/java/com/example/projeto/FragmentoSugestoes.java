package com.example.projeto;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoSugestoes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoSugestoes extends Fragment {
    RecyclerView rv;
    ImageView filtro;
    static DatabaseReference reference;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentoSugestoes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoSugestoes.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoSugestoes newInstance(String param1, String param2) {
        FragmentoSugestoes fragment = new FragmentoSugestoes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragmento_sugestoes, container, false);
        rv = v.findViewById(R.id.rv);
        filtro = v.findViewById(R.id.imageCarrega);
        filtro.setOnClickListener(view -> {
            carrega();
        });
        carrega();
        return v;
    }

    public void carrega() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Cliente> lista = new ArrayList<>();
                for (DataSnapshot dn : snapshot.getChildren()) {
                    Cliente c = dn.getValue(Cliente.class);
                    lista.add(c);
                }
                RecyclerAdapter adapter = new RecyclerAdapter(getContext(), lista, c -> {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
                    alerta.setTitle(c.getPlaca());
                    String mensagem = "Nome: " + c.getNome() + "\nTelefone: " + c.getTelefone() + "\nAtendimento: " + c.getAtendimento() + "\nInfraestrutura: " +
                            c.getInfraestrutura() + "\nQualidade do serviço: "+c.getQualidadeServico()+"\nNível de conhecimento: "+c.conhecimento+"\nData :"+c.getMes()+"/"+c.getAno();
                    alerta.setMessage(mensagem);
                    alerta.show();
                });
                rv.setAdapter(adapter);
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}