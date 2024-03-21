package so.memory;

import so.Process;

public class MemoryManager {
	private String[] physicMemory;
	private Strategy strategy;

	public MemoryManager(Strategy strategy) {
		this.strategy = strategy;
		this.physicMemory = new String[128];
	}

	public void writeProcess(Process p) {
		if (this.strategy.equals(Strategy.FIRST_FIT)) {
			this.writeUsingFirstFit(p);
		} else if (this.strategy.equals(Strategy.BEST_FIT)) {
			this.writeUsingBestFit(p);
		} else if (this.strategy.equals(Strategy.WORST_FIT)) {
			this.writeUsingWorstFit(p);
		}
	}

	public Process readProcess(String id) {
		for (int i = 0; i < physicMemory.length; i++) {
			if (physicMemory[i] != null && physicMemory[i].equals(id)) {
				return new Process(id);
			}
		}
		return null;
	}

	public void deleteProcess(Process p) {
		for (int i = 0; i < physicMemory.length; i++) {
			if (physicMemory[i] != null && physicMemory[i].equals(p.getId())) {
				physicMemory[i] = null;
			}
		}
		printStatusMemory();
	}

	public void clearMemory() {
		for (int i = 0; i < physicMemory.length; i++) {
			physicMemory[i] = null;
		}
		System.out.println("Memória limpa.");
	}


	private void writeUsingWorstFit(Process p) {
		int requiredMemory = p.getSizeInMemory();
		int worstFitInit = -1;
		int worstFitSize = -1;
		int currentStart = -1;
		int currentSize = 0;

		for (int i = 0; i < physicMemory.length; i++) {
			if (physicMemory[i] == null) {
				if (currentStart == -1) {
					currentStart = i;
				}
				currentSize++;
			} else {
				if (currentSize >= requiredMemory && currentSize > worstFitSize) {
					worstFitInit = currentStart;
					worstFitSize = currentSize;
				}
				currentStart = -1;
				currentSize = 0;
			}
		}

		if (currentSize >= requiredMemory && currentSize > worstFitSize) {
			worstFitInit = currentStart;
			worstFitSize = currentSize;
		}

		if (worstFitInit != -1 && worstFitSize >= requiredMemory) {
			for (int i = worstFitInit; i < worstFitInit + requiredMemory; i++) {
				physicMemory[i] = p.getId();
			}
			printStatusMemory();
			System.out.println("Processo alocado com sucesso!");
		} else {
			System.out.println("Estouro de memória!");
		}
	}

	private void writeUsingBestFit(Process p) {
		int bestFitInit = -1;
		int bestFitSize = Integer.MAX_VALUE;
		int currentStart = -1;
		int currentSize = 0;

	    for (int i = 0; i < physicMemory.length; i++) {
	        if (physicMemory[i] == null) {
	            if (currentStart == -1) {
	                currentStart = i;
	            }
	            currentSize++;
	        } else {
	            if (currentSize >= p.getSizeInMemory() && currentSize < bestFitSize) {
	                bestFitInit = currentStart;
	                bestFitSize = currentSize;
	            }
	            currentStart = -1;
	            currentSize = 0;
	        }
	    }
	    
	    if (currentSize >= p.getSizeInMemory() && currentSize < bestFitSize) {
	        bestFitInit = currentStart;
	        bestFitSize = currentSize;
	    }

	    if (bestFitInit != -1) {
	        for (int i = bestFitInit; i < bestFitInit + p.getSizeInMemory(); i++) {
	            physicMemory[i] = p.getId();
	        }
	        printStatusMemory();
	        System.out.println("Processo alocado com sucesso!");
	    } else {
	        System.out.println("Estouro de memória!");
	    }
	}

	private void writeUsingFirstFit(Process p) {
		int requiredMemory = p.getSizeInMemory();
		int freeMemoryCount = 0;
		int startBlockIndex = -1;

		for (int i = 0; i < physicMemory.length; i++) {
			if (physicMemory[i] == null) {
				freeMemoryCount++;
				if (freeMemoryCount == requiredMemory) {
					startBlockIndex = i - requiredMemory + 1;
					break;
				}
			} else {
				freeMemoryCount = 0;
			}
		}

		if (startBlockIndex != -1) {
			for (int i = startBlockIndex; i < startBlockIndex + requiredMemory; i++) {
				physicMemory[i] = p.getId();
			}
			printStatusMemory();
			System.out.println("Processo alocado com sucesso!");
		} else {
			System.out.println("Estouro de memória!");
		}
	}

	void printStatusMemory() {
		for (int i = 0; i < physicMemory.length; i++) {
			System.out.print(physicMemory[i] + " | \n");
		}
	}
}