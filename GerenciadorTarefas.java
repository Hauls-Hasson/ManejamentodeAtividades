package activiteManagement;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class GerenciadorTarefas implements OperacoesCRUD<Tarefa> {
    private List<Tarefa> listaTarefas;
    private BancoDeDados bancodedados;
    public GerenciadorTarefas() {
        this.listaTarefas = new ArrayList<>();
        bancodedados.carregarTarefasDoBanco();
    }
    
    public List<Tarefa> getListaTarefas(){
   	 return listaTarefas;
    }
    

    @Override
    public void adicionar(Tarefa tarefa) {
        listaTarefas.add(tarefa);
        bancodedados.adicionar(tarefa);
    }

    @Override
    public void listar() {
    	listarTarefasComFiltros();
    }

    @Override
    public void editar(int indice, Tarefa novaTarefa) {
        if (indice >= 0 && indice < listaTarefas.size()) {
            listaTarefas.set(indice, novaTarefa);
            bancodedados.editar(indice, novaTarefa);
            System.out.println("Tarefa editada com sucesso!");
        } else {
            System.out.println("Índice inválido. Não foi possível editar a tarefa.");
        }
    }

    
 public void removerTarefa(int indice) {
     if (indice >= 0 && indice < listaTarefas.size()) {
         Tarefa tarefaRemovida = listaTarefas.remove(indice);
         bancodedados.removerTarefa(tarefaRemovida);
         System.out.println("Tarefa removida com sucesso!");
     } else {
         System.out.println("Índice inválido. Não foi possível remover a tarefa.");
     }
 } 

 private void listarTarefasComFiltros() {
     Scanner scanner = new Scanner(System.in);

     System.out.println("\n=== Opções de Filtro ===");
     System.out.println("1. Filtrar por Data de Início");
     System.out.println("2. Filtrar por Data de Conclusão");
     System.out.println("3. Filtrar por Status (Concluídas/Não Concluídas)");
     System.out.println("4. Listar Todas as Tarefas");
     System.out.print("Escolha uma opção: ");

     int escolha = scanner.nextInt();
     scanner.nextLine();

     switch (escolha) {
         case 1:
             System.out.print("Digite a data de início desejada (formato: dd/MM/yyyy): ");
             String dataInicio = scanner.nextLine();
             filtrarPorDataInicio(dataInicio);
             break;
         case 2:
             System.out.print("Digite a data de conclusão desejada (formato: dd/MM/yyyy): ");
             String dataConclusao = scanner.nextLine();
             filtrarPorDataConclusao(dataConclusao);
             break;
         case 3:
             System.out.print("Digite o status desejado (1 para concluídas, 0 para não concluídas): ");
             int status = scanner.nextInt();
             filtrarPorStatus(status == 1);
             break;
         case 4:
             exibirTodasTarefas();
             break;
         default:
             System.out.println("Opção inválida. Listando todas as tarefas.");
             exibirTodasTarefas();
     }
 }
 
 private void filtrarPorDataInicio(String dataInicio) {
     System.out.println("\n=== Tarefas filtradas por Data de Início ===");

     for (Tarefa tarefa : listaTarefas) {
         if (tarefa.getDatainicio().equals(dataInicio)) {
             exibirDetalhesTarefa(tarefa);
         }
     }
 }

 
 private void filtrarPorDataConclusao(String dataConclusao) {
     System.out.println("\n=== Tarefas filtradas por Data de Conclusão ===");

     for (Tarefa tarefa : listaTarefas) {
         if (tarefa.getDataconclusao().equals(dataConclusao)) {
             exibirDetalhesTarefa(tarefa);
         }
     }
 }
 
 private void filtrarPorStatus(boolean status) {
     System.out.println("\n=== Tarefas filtradas por Status ===");

     for (Tarefa tarefa : listaTarefas) {
         if (tarefa.isStatus() == status) {
             exibirDetalhesTarefa(tarefa);
         }
     }
 }

 private void exibirDetalhesTarefa(Tarefa tarefa) {
     System.out.println(tarefa);
     System.out.println("------------------------");
 }
 
 
     private void exibirTodasTarefas() {
         System.out.println("\n=== Lista de Todas as Tarefas ===");
         for (Tarefa tarefa : listaTarefas) {
             System.out.println(tarefa);
             System.out.println("------------------------");
         }
 }
}

