package com.luna;
import java.util.HashMap;

//import lombok.Data;

//@Data
public class Customer {

	private int id;
	private String name;

	Customer(int id){
		this.id = id;
	}

	
public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


@Override
public int hashCode() {
	int h = id + name.hashCode();
	return h;
}

@Override
public boolean equals(Object obj) {
	Customer cu = (Customer)obj;
    return (cu.getName().equals(this.name)
    		&& cu.getId()== this.id); 
}

public static void main(String[] args) {
    HashMap<Customer, String> map = new HashMap<Customer, String>();
    Customer a1 = new Customer(1);
    a1.setName("A1");
    Customer a2 = new Customer(2);
    a2.setName("A2");
    map.put(a1, a1.getName());
    map.put(a2, a2.getName());
    a1.setName("TEST");
    a2.setName("TEST2");
    System.out.println(map.get(a1)); 
    System.out.println(map.get(a2)); 
    Customer a3 = new Customer(1);
    a3.setName("A3");
    System.out.println(map.get(a3));
}
}
