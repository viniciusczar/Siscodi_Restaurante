/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Delivery;

import DAO.ClasseDAO;

/**
 *
 * @author Caique
 */
public class ClasseVendaDelivery extends ClasseDAO {
    private int idDelivery;
    private float totalPedido;
    private String dataVenda;
    private int transf;
    private String status;

    public int getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(int idDelivery) {
        this.idDelivery = idDelivery;
    }

    public float getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(float totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getTransf() {
        return transf;
    }

    public void setTransf(int transf) {
        this.transf = transf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
