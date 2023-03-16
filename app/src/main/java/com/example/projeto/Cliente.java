package com.example.projeto;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cliente {
    String nome, telefone, sugestoes, placa;
    int atendimento, infraestrutura, qualidadeServico, conhecimento, mes, ano;

    public Cliente(String placa, String nome, String telefone, int mes, int ano) {
        this.placa = placa;
        this.nome = nome;
        this.telefone = telefone;
        this.mes = mes;
        this.ano = ano;
    }

    public Cliente(String nome, String telefone, String sugestoes, String placa, int atendimento, int infraestrutura, int qualidadeServico, int conhecimento, int mes, int ano) {
        this.nome = nome;
        this.telefone = telefone;
        this.sugestoes = sugestoes;
        this.placa = placa;
        this.atendimento = atendimento;
        this.infraestrutura = infraestrutura;
        this.qualidadeServico = qualidadeServico;
        this.conhecimento = conhecimento;
        this.mes = mes;
        this.ano = ano;
    }

    public Cliente(String nome, String telefone, String sugestoes, String placa, int atendimento, int infraestrutura, int qualidadeServico, int conhecimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.sugestoes = sugestoes;
        this.placa = placa;
        this.atendimento = atendimento;
        this.infraestrutura = infraestrutura;
        this.qualidadeServico = qualidadeServico;
        this.conhecimento = conhecimento;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(int conhecimento) {
        this.conhecimento = conhecimento;
    }

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSugestoes() {
        return sugestoes;
    }

    public void setSugestoes(String sugestoes) {
        this.sugestoes = sugestoes;
    }

    public int getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(int atendimento) {
        this.atendimento = atendimento;
    }

    public int getInfraestrutura() {
        return infraestrutura;
    }

    public void setInfraestrutura(int infraestrutura) {
        this.infraestrutura = infraestrutura;
    }

    public int getQualidadeServico() {
        return qualidadeServico;
    }

    public void setQualidadeServico(int qualidadeServico) {
        this.qualidadeServico = qualidadeServico;
    }
    public void salvar() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("clientes").child(ano+"").child(mes+"").child(placa).setValue(this);
    }

}
