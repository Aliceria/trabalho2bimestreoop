package com.mycompany.trabalho2bimestre;

public class Pedido {

    private int id;
    private int idCliente;
    private double total;
    private String status;

    public Pedido() {
    }

    public Pedido(int idCliente, double total, String status) {
        this.idCliente = idCliente;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}