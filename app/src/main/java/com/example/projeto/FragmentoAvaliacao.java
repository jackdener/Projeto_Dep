package com.example.projeto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoAvaliacao#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoAvaliacao extends Fragment {
    TextView contador, atendimento, infra, qualidade, conhecimento, geral;
    int quantidade = 0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentoAvaliacao() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoAvaliacao.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoAvaliacao newInstance(String param1, String param2) {
        FragmentoAvaliacao fragment = new FragmentoAvaliacao();
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
        View v = inflater.inflate(R.layout.fragment_fragmento_avaliacao, container, false);
        contador = v.findViewById(R.id.cont);
        atendimento = v.findViewById(R.id.mdAtendimento);
        infra = v.findViewById(R.id.mdInfraestrutura);
        qualidade = v.findViewById(R.id.mdQualidade);
        conhecimento = v.findViewById(R.id.mdConhecimento);
        geral = v.findViewById(R.id.mediaGeral);
        fazMedias();
        return v;
    }
    public void fazMedias() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("clientes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double mediaAtendimento = 0;
                double mediaInfra = 0;
                double mediaQualidade = 0;
                double mediaConhecimento = 0;
                double mediaGeral;
                for (DataSnapshot dn : snapshot.getChildren()) {
                    Cliente c = dn.getValue(Cliente.class);
                    quantidade++;
                    mediaAtendimento += c.getAtendimento();
                    mediaInfra += c.getInfraestrutura();
                    mediaQualidade += c.getQualidadeServico();
                    mediaConhecimento += c.getConhecimento();
                }
                contador.setText(quantidade + " cientes fizeram a avaliação!");
                mediaAtendimento /= quantidade;
                mediaInfra /= quantidade;
                mediaQualidade /= quantidade;
                mediaConhecimento /= quantidade;
                mediaGeral = (mediaAtendimento + mediaInfra + mediaQualidade + mediaConhecimento) / 4.0;

                atendimento.setText("Média de atendimento: " + String.format("%.2f", mediaAtendimento));
                infra.setText("Média de Infraestrutura: " + String.format("%.2f", mediaInfra));
                qualidade.setText("Média de Qualidade de Serviço: " + String.format("%.2f", mediaQualidade));
                conhecimento.setText("Média de conhecimento profissional: " + String.format("%.2f", mediaConhecimento));
                geral.setText("Média geral da loja: " + String.format("%.2f", mediaGeral));
                contador.setText(quantidade + " cientes fizeram a avaliação!");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}