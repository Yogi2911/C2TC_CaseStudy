package com.tnsif.oops.abstraction;

//abstract class
public abstract class Shape {
   protected float area; // 10.9f
   
   //abstract method 
	 abstract void calArea(); // return
	 
	 //concrete method
	 void show()
	 {
		 System.out.println("Area of shape is "+area);
	 }
}
	 