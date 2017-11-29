package com.mobway.pelemedicalcenter.services;

/**
 * Created by arthur.stapassoli on 29/11/2017.
 */

public class ScheduleRequest {

    private String codAgenda;
    private String codProcedimento;
    private String codCliente;

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
}
