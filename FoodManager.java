package FileManagement;
import Entities.Food;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class FoodManager{
	String filePath;
	public FoodManager(){
		filePath = "DataFiles/data.txt";
	}
	
	public void writeFood(Food s, boolean append){
		File f = new File(filePath);
		try{
			
			FileWriter writer = new FileWriter(f, append);
			writer.write(s.getFileWriteFormat());
			writer.flush();
			writer.close();
			
			}catch(Exception ex){}
	}
	
	public Food[] getAllFoods(){
		File f = new File(filePath);
		Food[] foods = null;
		try{
			Scanner sc = new Scanner(f);
			Scanner sc2 = new Scanner(f);
			int count=0;
			while(sc2.hasNextLine()){
				String temp = sc2.nextLine();
				count++;
			}
			
			foods = new Food[count];
			count=0;
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String[] data = line.split(";");
				Food s = new Food(data[0], data[1], data[2], data[3], data[4]);
				foods[count]=s;
				count++;
			}
		}catch(Exception ex){
			
		} return foods;
	}
	public Food searchFood(int sl){
		Food[] foods = getAllFoods();
		for(int i=0;i<foods.length;i++){
			if(foods[i].getSl()== sl){
				return foods[i];
			}
		}
		return null;
	}
	public void deleteFood(int sl){
		
		Food[] foods = getAllFoods();
		for(int i=0;i<foods.length;i++){
			if(foods[i].getSl() == sl){
				foods[i]=null;
			}
		}
		for(int i=0;i<foods.length;i++){
			if(foods[i] != null){
				if(i==0)writeFood(foods[i], false);
				else writeFood(foods[i],true);
			}
		}
	}
	
	
	
	public void updateFood(Food s){
		Food[] foods = getAllFoods();
		for(int i=0;i<foods.length;i++){
			if(foods[i].getSl()== s.getSl()){
				foods[i].setName(s.getName());
				foods[i].setQuantity(s.getQuantity());
				foods[i].setPrice(s.getPrice());
				foods[i].setExpDate(s.getExpDate());
				
			}
		}
		
		for(int i=0;i<foods.length;i++){
			if(i==0)writeFood(foods[i],false);
			else writeFood(foods[i], true);
		}
	}

}
