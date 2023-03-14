package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Sugestoes extends AppCompatActivity {
    static Cliente cliente;
    EditText campo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestoes);
        getSupportActionBar().hide();
        campo = findViewById(R.id.sugestao);
    }
    @Override
    public void onBackPressed() {
        String mensagem = "<h1><big>Você precisa finalizar a pesquisa de avaliação!</big></h1>";
        Toast toast = Toast.makeText(this, HtmlCompat.fromHtml(mensagem,HtmlCompat.FROM_HTML_MODE_COMPACT), Toast.LENGTH_LONG);
        toast.getView().setBackgroundColor(Color.GREEN);
        toast.show();
    }
    public void envia(View v){
        String texto = campo.getText().toString();
        cliente.setSugestoes(texto);
        campo.setText("");
        cliente.salvar();
        TelaPesquisa.avaliando = false;
        super.onBackPressed();
    }
}