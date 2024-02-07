package activiteManagement;

import java.util.ArrayList;
import java.util.List;

class Tarefa {
    private String titulo;
    private String descricao;
    private String datainicio;
    private String dataconclusao;
    private int status; 

    
    public Tarefa(String titulo, String descricao, String dataInicio, String dataConclusaoPrevista) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.datainicio = dataInicio;
        this.dataconclusao = dataConclusaoPrevista;
        this.status = 0; 
        }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(String datainicio) {
        this.datainicio = datainicio;
    }

    public String getDataconclusao() {
        return dataconclusao;
    }

    public void setDataconclusao(String dataconclusao) {
        this.dataconclusao = dataconclusao;
    }

    public boolean isStatus() {
        return status == 1; 
    }

    public void setStatus(boolean status) {
        this.status = status ? 1 : 0; 
    }
    public String toString() {
        return "Tarefa: " + titulo + "\nDescrição: " + descricao + "\nStatus: " + (isStatus() ? "Concluída" : "Não concluída");
    }
}