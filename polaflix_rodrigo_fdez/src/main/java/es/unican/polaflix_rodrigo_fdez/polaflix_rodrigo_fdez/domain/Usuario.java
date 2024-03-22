package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nombreUsuario;

    private String contrasenha;

    private List<Serie> seriesPendientes;
    private List<Serie> seriesEmpezadas;
    private List<Serie> seriesTerminadas;

    private List<CapituloVisto> capitulosVistos;

    private List<Factura> facturas;

    public Usuario(String nombreUsuario, String contrasenha) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.seriesPendientes = new ArrayList<>();
        this.seriesEmpezadas = new ArrayList<>();
        this.seriesTerminadas = new ArrayList<>();
        this.capitulosVistos = new ArrayList<>();
        this.facturas = new ArrayList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public List<Serie> getSeriesPendientes() {
        return seriesPendientes;
    }

    public void setSeriesPendientes(List<Serie> seriesPendientes) {
        this.seriesPendientes = seriesPendientes;
    }

    public List<Serie> getSeriesEmpezadas() {
        return seriesEmpezadas;
    }

    public void setSeriesEmpezadas(List<Serie> seriesEmpezadas) {
        this.seriesEmpezadas = seriesEmpezadas;
    }

    public List<Serie> getSeriesTerminadas() {
        return seriesTerminadas;
    }

    public void setSeriesTerminadas(List<Serie> seriesTerminadas) {
        this.seriesTerminadas = seriesTerminadas;
    }

    public List<CapituloVisto> getCapitulosVistos() {
        return capitulosVistos;
    }

    public void setCapitulosVistos(List<CapituloVisto> capitulosVistos) {
        this.capitulosVistos = capitulosVistos;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
