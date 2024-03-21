package so;

import java.util.Scanner;

import so.memory.MemoryManager;
import so.memory.Strategy;
import so.Process;
import so.SystemCallType;

public class Execute {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao MemoryManager!");
        System.out.println("Selecione uma opção:");
        System.out.println("1. Inicializar");
        System.out.println("0. Sair");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                initializeMenu();
                break;
            case 0:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }

    }

    public static void initializeMenu() {
        Scanner scanner = new Scanner(System.in);
        Process p = new Process();

        System.out.println("Selecione uma estratégia de alocação:");
        System.out.println("1. First Fit");
        System.out.println("2. Worst Fit");
        System.out.println("3. Best Fit");

        int strategyOption = scanner.nextInt();
        Strategy strategy = SystemOperation.SetMemoryType(strategyOption);



        int option = -1;
        while (option != 0) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Executar processo");
            System.out.println("2. Finalizar Processo");
            System.out.println("0. Sair");
            option = scanner.nextInt();            
            switch (strategyOption) {
                case 1:
                    p = new Process();
                    
                    if(option == 1) {
	                    System.out.print("Digite o tamanho para o processo: ");
	                    p.setSizeInMemory(scanner.nextInt());
	 
	                    SystemOperation.SetMemoryType(strategyOption);
	                    SystemOperation.SystemCall(SystemCallType.CREATE_PROCESS, p, strategy);
                    } else if(option == 2) {
                    	System.out.print("Digite o id do processo: ");
	                    String id = scanner.next();
	                    p.setId(id);
	                    SystemOperation.SetMemoryType(strategyOption);
	                    SystemOperation.SystemCall(SystemCallType.DELETE_PROCESS, p, strategy);
                    }
                    
                    break;
                case 2:
                	p = new Process();
                    
                    if(option == 1) {
	                    System.out.print("Digite o tamanho para o processo: ");
	                    p.setSizeInMemory(scanner.nextInt());
	 
	                    SystemOperation.SetMemoryType(strategyOption);
	                    SystemOperation.SystemCall(SystemCallType.CREATE_PROCESS, p, strategy);
                    } else if(option == 2) {
                    	System.out.print("Digite o id do processo: ");
	                    String id = scanner.next();
	                    p.setId(id);
	                    SystemOperation.SetMemoryType(strategyOption);
	                    SystemOperation.SystemCall(SystemCallType.DELETE_PROCESS, p, strategy);
                    }
                    
                    break;
                case 3:
                	p = new Process();
                    
                    if(option == 1) {
	                    System.out.print("Digite o tamanho para o processo: ");
	                    p.setSizeInMemory(scanner.nextInt());
	 
	                    SystemOperation.SetMemoryType(strategyOption);
	                    SystemOperation.SystemCall(SystemCallType.CREATE_PROCESS, p, strategy);
                    } else if(option == 2) {
                    	System.out.print("Digite o id do processo: ");
	                    String id = scanner.next();
	                    p.setId(id);
	                    SystemOperation.SetMemoryType(strategyOption);
	                    SystemOperation.SystemCall(SystemCallType.DELETE_PROCESS, p, strategy);
                    }
                    
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }


        }
        System.out.println("Voltando ao menu principal...");
    }
}
