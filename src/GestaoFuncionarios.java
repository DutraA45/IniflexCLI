import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.math.BigDecimal;
import java.util.Scanner;

public class GestaoFuncionarios {

    private final Scanner scanner;
    private final LinkedHashMap<String, Funcionario> funcionarios;

    public GestaoFuncionarios() {
        this.scanner = new Scanner(System.in);
        this.funcionarios = new LinkedHashMap<>();
    }

    public void cadastrarFuncionarios() {
        try (BufferedReader buffRead = new BufferedReader(new FileReader("src/base.txt"))) {

            String linha = buffRead.readLine();

            while (linha != null) {

                String[] dados = linha.split(";");

                if (dados.length == 4) {
                    String nome = dados[0];
                    String dataNascimento = dados[1];
                    BigDecimal salario = new BigDecimal(dados[2]);
                    String funcao = dados[3];

                    Funcionario funcionario = new Funcionario(nome, dataNascimento, salario, funcao);

                    funcionarios.put(nome, funcionario);

                    System.out.println("Funcionário adicionado: " + funcionario);
                } else {
                    System.err.println("Linha inválida: " + linha);
                }

                linha = buffRead.readLine();
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter salário: " + e.getMessage());
        }
    }

    public LinkedHashMap<String, Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void removerFuncionario() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados para remover.");
            return;
        }

        System.out.print("\nDigite o nome do funcionário a ser removido: ");
        String nome = scanner.nextLine().trim();

        if (funcionarios.containsKey(nome)) {
            Funcionario funcionarioRemovido = funcionarios.remove(nome);
            System.out.println("Funcionário removido com sucesso: " + funcionarioRemovido);
        } else {
            System.out.println("Funcionário não encontrado: '" + nome + "'");
            System.out.println("Verifique se o nome está correto e tente novamente.");
        }
    }

    public void addBonus() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado para adicionar bônus.");
            return;
        }

        for (Funcionario funcionario : funcionarios.values()) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal novoSalario = salarioAtual.multiply(new BigDecimal("1.1"));
            funcionario.setSalario(novoSalario);
        }

        System.out.println("\nBônus de 10% aplicado para todos os funcionário(s).");
    }
}
