/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Almoxa;

import java.util.Date;

/**
 *
 * @author Capitao
 */
public class Estoque {
    private int Id;
    private String Nome;
    private String Marca;
    private int Cod_embalagem;
    private Date Validade;
    private int Quantidade;
    private double Preco;
    private Date Data_ins;
    private int CodigoBarra;
    private String Obs1;
    private String Obs2;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public int getCod_embalagem() {
        return Cod_embalagem;
    }

    public void setCod_embalagem(int Cod_embalagem) {
        this.Cod_embalagem = Cod_embalagem;
    }

    public Date getValidade() {
        return Validade;
    }

    public void setValidade(Date Validade) {
        this.Validade = Validade;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }

    public Date getData_ins() {
        return Data_ins;
    }

    public void setData_ins(Date Data_ins) {
        this.Data_ins = Data_ins;
    }

    public int getCodigoBarra() {
        return CodigoBarra;
    }

    public void setCodigoBarra(int CodigoBarra) {
        this.CodigoBarra = CodigoBarra;
    }

    public String getObs1() {
        return Obs1;
    }

    public void setObs1(String Obs1) {
        this.Obs1 = Obs1;
    }

    public String getObs2() {
        return Obs2;
    }

    public void setObs2(String Obs2) {
        this.Obs2 = Obs2;
    }


    
}
