package SistemaLavanderia;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
    
    //Scanner
        //inicializar o Scanner para ler a resposta do usuário
        Scanner scanner = new Scanner(System.in);

    //Menu
        //construir o menu com prints
        System.err.println("===== MENU_LAVANDERIA =====\n");

        //1 cadastrar um cliente (mensalista ou avulso)
        System.out.println("1 - Cadastrar cliente");

        //2 registrar novo pedido
        System.out.println("2 - Registrar pedido");

        //3 consultar cobranças pendentes
        System.out.println("3 - Consultar cobranças");

        //4 registrar pagamento / quitação de cobrança
        System.out.println("4 - Registrar pagamento");

        //5 listar clientes e pedidos
        System.out.println("5 - Listar clientes e pedidos");

        //6 sair
        System.out.println("6 - Sair");

    //Switch
        System.out.println("\nEscolha uma opção:");

        //criar o int que irá receber o valor lido 
        int opcao = scanner.nextInt();
        
        //montar o switch com as opções mostradas
        switch (scanner) {
            case 1:
                //código
                break;
        
            case 2:
                //código
                break;

            case 3:
                //codigo
                break;

            case 4:
                //codigo
                break;
            
            case 5:
                //código
                break;

            case 6:
                //código
                break;

            default:
                break;
        }

    }

}
