package Entities;
public class Food{
	private int sl;
	private String name;
	private double price;
	private int quantity;
	private String expDate;
	
	
	public Food(){}
	
	public Food(int sl,String name,int quantity, double price, String expDate){
		this.sl = sl;
		this.name= name;
		this.quantity= quantity;
	    this.price= price;
		this.expDate= expDate;
		}
	
	public Food (String sl,String name,String quantity, String price, String expDate){
		
		this.sl = Integer.parseInt(sl);
		this.name= name;
		this.quantity=Integer.parseInt(quantity);
		this.price=Double.parseDouble(price);
		this.expDate= expDate;
		
	}
	
	//Setter Method
	
	public void setSl(int sl){
		this.sl=sl;
	}
	
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setExpDate(String expDate){
		this.expDate=expDate;
	}
	
	public void setPrice(double price){
		this.price=price;
	}
	
	//getter method
	
	public int getSl(){return sl;}
	public int getQuantity(){return quantity;}
	public String getName(){return name;}
	public String getExpDate(){return expDate;}
	public double getPrice(){return price;}
	
	
	public void show(){
		System.out.println("Sl: "+ sl);
		System.out.println("Name: "+ name);
		System.out.println("Quantity: "+ quantity);
		System.out.println("Price: "+ price);
		System.out.println("Expiry Date: "+ expDate);
		
	}
	
	public String getFileWriteFormat() {
    return sl +";"+ name + ";" + quantity + ";" + price + ";" + expDate + "\n";
	}

	public Object[] getTableRow(){
		return new Object[]{sl,name,quantity,price,expDate};
		}
	}
	
	
