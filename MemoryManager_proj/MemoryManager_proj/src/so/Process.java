package so;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Process {
	private String id;
	private int sizeInMemory;
	//private List<Process> process;
	//private in timeToExecute;
	//private AddressMemory addressInMemory;
	
	public Process() {
		Random rand = new Random();
		this.id = UUID.randomUUID().toString();			
	}
	public Process(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSizeInMemory() {
		return sizeInMemory;
	}
	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}
	}