package so;
import so.memory.MemoryManager;
import so.memory.Strategy;
import so.schedule.Schedule;
import so.cpu.CpuManager;
import so.Process;

public class SystemOperation {
    private static MemoryManager mm;
    private static CpuManager cm;
    private static Schedule schedule;
    
    public static Strategy SetMemoryType(int opt) {
        Strategy strategy = null;    	
        switch (opt) {
        case 1:
            strategy = Strategy.FIRST_FIT;
            return strategy;
        case 2:
            strategy = Strategy.WORST_FIT;
            return strategy;
        case 3:
            strategy = Strategy.BEST_FIT;
            return strategy;
        default:
            System.out.println("Opção inválida. Tente novamente.");
            return null;
    }
    }
 
    				
    public static Process SystemCall(SystemCallType type, Process p, Strategy strategy) {
    	
        if(type.equals(SystemCallType.WRITE_PROCESS)) {
            mm.writeProcess(p);
        } else if (type.equals(SystemCallType.DELETE_PROCESS)){
            if (mm != null) {
                mm.deleteProcess(p);
            }
        } else if (type.equals(SystemCallType.READ_PROCESS)) {
            return mm.readProcess(p.getId());
        } else if (type.equals(SystemCallType.CREATE_PROCESS)){
            if(cm == null) {
                cm = new CpuManager();
            }
            if (mm == null) {
            	if(strategy.equals(Strategy.BEST_FIT)) {            		
            		mm = new MemoryManager(Strategy.BEST_FIT);
            	}else if (strategy.equals(Strategy.WORST_FIT)) {
            		mm = new MemoryManager(Strategy.WORST_FIT);
            	}else if (strategy.equals(Strategy.FIRST_FIT)) {
            		mm = new MemoryManager(Strategy.FIRST_FIT);
            	}else {
            	    return new Process();
            	}

            }
            mm.writeProcess(p);
            return new Process();
        } 
        return null;
    }
}
