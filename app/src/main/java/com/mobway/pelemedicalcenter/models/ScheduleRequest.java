package com.mobway.pelemedicalcenter.models;

import java.io.Serializable;

/**
 * Created by arthur.stapassoli on 29/11/2017.
 */

public class ScheduleRequest implements Serializable {

    private String codAgenda;
    private String codProcedimento;
    private String codCliente;
    private String turno;
    private String data;

    public String getCodAgenda() {
        return codAgenda;
    }

    public void setCodAgenda(String codAgenda) {
        this.codAgenda = codAgenda;
    }

    public String getCodProcedimento() {
        return codProcedimento;
    }

    public void setCodProcedimento(String codProcedimento) {
        this.codProcedimento = codProcedimento;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
