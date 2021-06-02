/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Delivery;

import DAO.ClasseDAO;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aluno
 */
public class ClasseDelivery extends ClasseDAO{
    
    private int idCliente;
    private int idDelivery;
    private int idProduto;
    private String endereco;
    private String obs;
    private String telefone;
    private String descricao;
    private float totalPedido;
    private int qtdProduto;
    private float precoProduto;
    private String dataVenda;
    private DefaultTableModel itensDelivery;
    private int transf;
    private float troco;
    private String status;
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(int idDelivery) {
        this.idDelivery = idDelivery;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public float getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(float precoProduto) {
        this.precoProduto = precoProduto;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public DefaultTableModel getItensDelivery() {
        return itensDelivery;
    }

    public void setItensDelivery(DefaultTableModel itensDelivery) {
        this.itensDelivery = itensDelivery;
    }

    public float getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(float totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTransf() {
        return transf;
    }

    public void setTransf(int transf) {
        this.transf = transf;
    }

    public float getTroco() {
        return troco;
    }

    public void setTroco(float troco) {
        this.troco = troco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
