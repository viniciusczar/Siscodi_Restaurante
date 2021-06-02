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
public class SaidaProdutos {
    private int Id;
    private int Cod_embalagem;
    private Date Validade;
    private int QuantidadeSaida;
    private Date DataSaida;
    private double Preco;
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

    public int getQuantidadeSaida() {
        return QuantidadeSaida;
    }

    public void setQuantidadeSaida(int QuantidadeSaida) {
        this.QuantidadeSaida = QuantidadeSaida;
    }

    public Date getDataSaida() {
        return DataSaida;
    }

    public void setDataSaida(Date DataSaida) {
        this.DataSaida = DataSaida;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
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
