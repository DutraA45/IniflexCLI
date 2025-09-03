import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class MenuList {

    private final Scanner scanner;
    private final GestaoFuncionarios gestaoFuncionarios;

    public MenuList(GestaoFuncionarios gestaoFuncionarios) {
        this.scanner = new Scanner(System.in);
        this.gestaoFuncionarios = gestaoFuncionarios;
    }

    public void exibirMenuListagem() {
        int opcao;
        do {
            System.out.println("\n########################### Projedata - Iniflex ############################");
            System.out.println("# Opções de listagem:                                                      #");
            System.out.println("# 1 - Listagem completa de funcionários                                    #");
            System.out.println("# 2 - Listagem de funcionários por Funcao                                  #");
            System.out.println("# 3 - Listagem de funcionários por Ordem Alfabética                        #");
            System.out.println("# 4 - Listar funcionários que fazem aniversário no mês 10 e 12             #");
            System.out.println("# 5 - Listar funcionário com maior idade                                   #");
            System.out.println("# 6 - Listar o total dos salários dos funcionários                         #");
            System.out.println("# 7 - Listar quantos salários mínimos cada funcionário ganha               #");
            System.out.println("# 9 - Voltar                                                               #");
            System.out.println("############################################################################");
            System.out.printf("Digite a opção desejada: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            processarOpcao(opcao);

        } while (opcao != 9);
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> listarTodosFuncionarios();
            case 2 -> listarFuncionarioPorFuncao();
            case 3 -> listarFuncionarioPorOrdemAlfabetica();
            case 4 -> listarFuncionarioPorAniversario();
            case 5 -> listarFuncionarioPorIdade();
            case 6 -> listarTotalSalario();
            case 7 -> listarSalarioMininoPorFuncionario();
            case 9 -> System.out.println("Voltando...");
            default -> System.out.println("Opção inválida!");
        }
    }

    private void listarTodosFuncionarios() {
        LinkedHashMap<String, Funcionario> funcionarios = gestaoFuncionarios.getFuncionarios();

        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n LISTA COMPLETA DE FUNCIONÁRIOS ");
        for (Funcionario funcionario : funcionarios.values()) {
            System.out.println(funcionario);
        }
    }

    private void listarFuncionarioPorFuncao() {
        LinkedHashMap<String, Funcionario> funcionarios = gestaoFuncionarios.getFuncionarios();
        ArrayList<String> funcoes = new ArrayList<>();

        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        for (Funcionario funcionario : funcionarios.values()) {
            funcoes.add(funcionario.getFuncao());
        }

        HashSet<String> funcoesClear = new HashSet<>(funcoes);
        funcoes.clear();
        funcoes.addAll(funcoesClear);

        for (int i = 0; i < funcoes.size(); i++) {
            System.out.println("\nFunção: " + funcoes.get(i));
            for (Funcionario funcionario : funcionarios.values()){
                if (funcoes.get(i).equals(funcionario.getFuncao())){
                    System.out.println(funcionario);
                }
            }
        }
    }

    private void listarFuncionarioPorOrdemAlfabetica() {
        LinkedHashMap<String, Funcionario> funcionarios = gestaoFuncionarios.getFuncionarios();
        TreeMap<String, Funcionario> funcionariosPorOrdemAlfabetica = new TreeMap<>();

        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n LISTA COMPLETA DE FUNCIONÁRIOS ");
        for (Funcionario funcionario : funcionarios.values()) {
            funcionariosPorOrdemAlfabetica.put(funcionario.getNome(), funcionario);
        }

        System.out.println("\n LISTA DE FUNCIONÁRIOS POR ORDEM ALFABÉTICA");
        for (Funcionario funcionario : funcionariosPorOrdemAlfabetica.values()) {
            System.out.println(funcionario);
        }
    }

    private void listarFuncionarioPorAniversario() {
        LinkedHashMap<String, Funcionario> funcionarios = gestaoFuncionarios.getFuncionarios();

        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        for (Funcionario funcionario : funcionarios.values()) {
            if (funcionario.getMesNascimento()==10 || funcionario.getMesNascimento()==12) {
                System.out.println(funcionario);
            }
        }
    }

    private void listarFuncionarioPorIdade() {
        Funcionario funcionarioMaisVelho;

        LinkedHashMap<String, Funcionario> funcionarios = gestaoFuncionarios.getFuncionarios();
        TreeMap<Integer, Funcionario> funcionariosPorIdade = new TreeMap<>();

        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        for (Funcionario funcionario : funcionarios.values()) {
            funcionariosPorIdade.put(funcionario.getIdade(), funcionario);
        }

        funcionarioMaisVelho = funcionariosPorIdade.lastEntry().getValue();

        System.out.println("\nNome: " + funcionarioMaisVelho.getNome() + ", Idade: " + funcionarioMaisVelho.getIdade() + " anos");

    }

    private void listarTotalSalario() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        BigDecimal totalSalario = new BigDecimal(0);

        LinkedHashMap<String, Funcionario> funcionarios = gestaoFuncionarios.getFuncionarios();

        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        for (Funcionario funcionario : funcionarios.values()) {
            totalSalario = totalSalario.add(funcionario.getSalario());
        }

        System.out.println("\nTotal dos salários dos funcionários: " + numberFormat.format(totalSalario));
    }

    private void listarSalarioMininoPorFuncionario() {
        LinkedHashMap<String, Funcionario> funcionarios = gestaoFuncionarios.getFuncionarios();

        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        for (Funcionario funcionario : funcionarios.values()) {
            System.out.println("Nome: " + funcionario.getNome() + ", " + funcionario.getQtdSalarioMin() + " sálarios mínimos.");
        }
    }


}
