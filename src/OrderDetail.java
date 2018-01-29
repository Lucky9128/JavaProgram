import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDetail {
	protected static int Number=0;
	private String OrderId;
	private String FoodItemId;
	private int NumberofItems;
	private ArrayList<Integer> Qty;
	private ArrayList<Integer> Half;
	private double total;
	private ArrayList<Double> Price;
	
	OrderDetail(){
		this.NumberofItems = 0;
		this.FoodItemId = new String();
		this.Qty = new ArrayList<Integer>(0);
		this.Half = new ArrayList<Integer>(0);
		this.Price = new ArrayList<Double>(0);
		GenerateOrderNumber();
	}
	OrderDetail(int TableNumber){
		this.NumberofItems = 0;
		this.FoodItemId = new String();
		this.Qty = new ArrayList<Integer>(0);
		this.Half = new ArrayList<Integer>(0);
		this.Price = new ArrayList<Double>(0);
		GenerateOrderNumber();
	}
	OrderDetail(int TableNumber,String OrderId){
		this.NumberofItems = 0;
		this.FoodItemId = new String();
		this.Qty = new ArrayList<Integer>(0);
		this.Half = new ArrayList<Integer>(0);
		this.Price = new ArrayList<Double>(0);
		this.OrderId = OrderId;
	}
	protected double getTotal() {
		return total;
	}
	protected void GenerateOrderNumber() {
		Number++;
		this.OrderId = "O"+Number;
	}
	
	protected void addFoodItem(String FoodCode, int Qty, int Half,double Price) {
		this.FoodItemId += FoodCode+" ";
		this.Qty.add(Qty);
		this.Half.add(Half);
		this.Price.add(Price);
		total+=Price*Qty;
		NumberofItems++;
	}
	
	protected String getOrderId() {
		return OrderId;
	}
	protected void setOrderId(String orderId) {
		OrderId = orderId;
	}
	protected String getFoodItemId() {
		return FoodItemId;
	}
	protected void setFoodItemId(String foodItemId) {
		FoodItemId = foodItemId;
	}
	protected void setQty(String abc) {
		String abcd[] = abc.split(" ");
		for(int i=0;i<abcd.length;i++) {
			Qty.add(Integer.parseInt(abcd[i]));
		}
	}
	protected int getNumberofItems() {
		return NumberofItems;
	}
	protected void setNumberofItems(int numberofItems) {
		NumberofItems = numberofItems;
	}
	protected String getQtyString() {
		String QtyString = new String();
		for(int i=0;i<this.Qty.size();i++)
		QtyString += String.valueOf(Qty.get(i))+" ";
		
		return QtyString;
	}
	protected void setPrice(String abc) {
		String abcd[] = abc.split(" ");
		total=0;
		for(int i=0;i<abcd.length;i++) {
			{
				Price.add(Double.parseDouble(abcd[i]));
				total+=Price.get(i);
			}
		}
	}
	protected String getNameofFoodIdByIndex(int index) {
		String abcd[] = FoodItemId.split(" ");
		return abcd[index];
	}
	protected String getPriceString() {
		String QtyString = new String();
		for(int i=0;i<this.Qty.size();i++)
		QtyString += String.valueOf(Price.get(i))+" ";
		
		return QtyString;
	}
}
