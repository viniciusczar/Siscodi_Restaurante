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
public class EntradaProdutos {
    private int Id;
    private int Cod_embalagem;
    private Date Validade;
    private double Preco;
    private Date DataEntrada;
    private String Obs1;
    private String Obs2;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }

    public Date getDataEntrada() {
        return DataEntrada;
    }

    public void setDataEntrada(Date DataEntrada) {
        this.DataEntrada = DataEntrada;
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
