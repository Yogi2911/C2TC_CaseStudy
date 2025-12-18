package com.tnsif.oops.inheritance;


	class A{  //superclass
		void display() {
			System.out.println("hello world");
		}	
	}
	class B extends A{
		
	}
	class c extends A{
		
	}
	public class InheritanceDemo {
	public static void main(String[] args) {
		c obj = new c();
		obj.display();
		
	}

}
