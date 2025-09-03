import java.util.Scanner;

public class Menu {
    private final GestaoFuncionarios gestaoFuncionarios;
    private final MenuList menuList;
    private final Scanner scanner;

    public Menu() {
        this.gestaoFuncionarios = new GestaoFuncionarios();
        this.menuList = new MenuList(gestaoFuncionarios);
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n######### Projedata - Iniflex ##########");
            System.out.println("# Opções:                              #");
            System.out.println("# 1 - Cadastrar funcionários (Base)    #");
            System.out.println("# 2 - Remover funcionário              #");
            System.out.println("# 3 - Listar funcionários              #");
            System.out.println("# 4 - Adicionar aumento (10%)          #");
            System.out.println("# 9 - Sair                             #");
            System.out.println("########################################");
            System.out.printf("Digite a opção desejada: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            processarOpcao(opcao);

        } while (opcao != 9);
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> cadastrarFuncionario();
            case 2 -> removerFuncionario();
            case 3 -> listarFuncionarios();
            case 4 -> aplicarAumento();
            case 9 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida!");
        }
    }

    private void cadastrarFuncionario() {
        gestaoFuncionarios.cadastrarFuncionarios();
    }

    private void removerFuncionario() {
        gestaoFuncionarios.removerFuncionario();
    }

    private void listarFuncionarios() {
        menuList.exibirMenuListagem();
    }

    private void aplicarAumento() {
        gestaoFuncionarios.addBonus();
    }
}
