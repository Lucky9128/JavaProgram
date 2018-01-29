
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
	 *Inner  Class used to have data of Menu it will have a array of FoodCategory
	 */
	public class MenuList{
		private FoodCategory List[];
		private int NumberOfCateogries;
		protected void AddCategory(String CategoryName,String CategoryID) {
			List[NumberOfCateogries] = new FoodCategory(CategoryName,CategoryID);
			NumberOfCateogries ++;
		}
		
		protected List<String> getSimilarFoodItem(String abc) {
			List<String> Similar = new ArrayList<String>(0);
			Similar.add(abc);
			for(int i=0;i<NumberOfCateogries;i++) {
				for(int j=0;j<List[i].getItems();j++) {
					if(List[i].getFoodItem(j).getItemName().contains(abc)) {
						Similar.add(List[i].getFoodItem(j).getItemName());
					}
				}
				
			}
			return Similar;
		}
		
		 MenuList() { 
			try {
			 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
			 Statement st = con.createStatement();

			 ResultSet rs = st.executeQuery("SELECT COUNT(ID) FROM DEMODB.FOODCATEGORY");
			 rs.next();
			 int NumberOfCateogry = rs.getInt(1);
			 int NumberOfFoodItems;
			 this.List = new FoodCategory[NumberOfCateogry];
			 rs.close();
			 rs = st.executeQuery("SELECT * FROM DEMODB.FOODCATEGORY");
			 rs.next();
			 for(int i=1;i<=NumberOfCateogry;i++) {
				 this.AddCategory(rs.getString(2),rs.getString(1));		 
				 rs.next();
			 }
			 rs.close();
			 for(int i=0;i<NumberOfCateogry;i++) {
				 rs = st.executeQuery("SELECT COUNT(FOODITEM.FOODID) FROM DEMODB.FOODITEM INNER JOIN "
				 		+ "DEMODB.FOODCATEGORY ON DEMODB.FOODCATEGORY.ID = DEMODB.FOODITEM.CATEGORYID AND DEMODB.FOODITEM.CATEGORYID='"+this.List[i].getCategoryID()+"'");
				 rs.next();
				 NumberOfFoodItems = rs.getInt(1);
				 rs.close();
				 rs = st.executeQuery("SELECT FOODITEM.FOODID,FOODITEM.NAME,FOODITEM.PRICE FROM DEMODB.FOODITEM INNER JOIN "
					 		+ "DEMODB.FOODCATEGORY ON DEMODB.FOODCATEGORY.ID = DEMODB.FOODITEM.CATEGORYID AND DEMODB.FOODITEM.CATEGORYID='"+this.List[i].getCategoryID()+"'");
				 rs.next();
				 for(int j=0;j<NumberOfFoodItems;j++) {
					 this.List[i].addFoodItem(new FoodItem(rs.getString(1), rs.getString(2),rs.getDouble(3)));
					 rs.next();
				 }
				 rs.close();
			 }
			 rs.close();
			 st.close();
			 con.close();
			 }catch(SQLException e) {
			System.out.println(e.getMessage()); 
		 }
		}
		protected FoodCategory getFoodCategory(int index) {
			return List[index];
		}
		protected FoodCategory[] getList() {
			return List;
		}
		protected void setList(FoodCategory[] list) {
			List = list;
		}
		protected int getNumberOfCateogries() {
			return NumberOfCateogries;
		}
		protected void setNumberOfCateogries(int numberOfCateogries) {
			NumberOfCateogries = numberOfCateogries;
		}
		protected FoodItem getMyNameFood(String Name) {
			boolean flag = false;
			int CatPos=0;
			int ItemPos=0;
			
			for(int i=0;i<NumberOfCateogries;i++) {
				CatPos = i;
				
				for(int j=0;j<List[i].getItems();j++) {
					ItemPos = j;
					if(List[i].getFoodItem(j).getItemName().equals(Name)) {
						flag=true;
						i=NumberOfCateogries;
						break;
					}
				}
			}
			if(flag) {
				return List[CatPos].getFoodItem(ItemPos);
			}
			else {
				return null;
			}
		}
		protected boolean FoodItemExsist(String Name) {
			boolean flag = false;
			for(int i=0;i<NumberOfCateogries;i++) {
				if(List[i].FoodItemExsist(Name)) {
					flag=true;
					break;
				}
			}
			return flag;
		}
	}

 class FoodCategory{
		private String CategoryName;
		private String CategoryID;
		private FoodItem List[];
		private int Items=0;
		protected String getCategoryName() {
			return CategoryName;
		}
		protected void setCategoryName(String categoryName) {
			CategoryName = categoryName;
		}
		protected String getCategoryID() {
			return CategoryID;
		}
		protected void setCategoryID(String categoryID) {
			CategoryID = categoryID;
		}
		protected FoodItem[] getList() {
			return List;
		}
		protected void setList(FoodItem[] list) {
			List = list;
		}
		protected int getItems() {
			return Items;
		}
		protected void setItems(int items) {
			Items = items;
		}
		FoodCategory(String CategoryName,String CategoryID){
			List = new FoodItem[100];
			this.CategoryName = CategoryName;
			this.CategoryID = CategoryID;
		}
		protected void addFoodItem(FoodItem Item) {
			List[Items] = Item;
			Items++;
		}
		FoodCategory(FoodCategory FdCAt){
			this.CategoryName = FdCAt.CategoryName;
			this.CategoryID = FdCAt.CategoryID;
			this.List = FdCAt.List;
			this.Items = FdCAt.Items;
		}
		protected void RemoveFoodItemByName(String Name) {
			boolean flag=false;
			int counter;
			for(counter=0;counter<=this.Items;counter++) {
				if(this.List[counter].equals(Name)) {
					flag = true;
					break;
				}
			}
			if(flag) {
				RemoveFoodItemByIndex(counter);
			}
		}
		protected FoodItem getFoodItem(int index) {
			return List[index];
		}
		protected void RemoveFoodItemByIndex(int Counter) {
			for(int i=Counter;i<=Items;i++) {
				this.List[i]=List[i+1];
			}
			Items--;
		}
		protected boolean FoodItemExsist(String Name) {
			boolean flag = false;
			for(int i=0;i<Items;i++) {
				if(List[i].getItemName().equals(Name)) {
					flag = true;
					break;
				}
			}
			return flag;
		}
			
	}
 class FoodItem{
		private String ItemName;
		private String ItemID;
		private double Price;
		FoodItem(String ItemID,String ItemName,double Price){
			this.ItemID = ItemID;
			this.ItemName = ItemName;
				this.Price = Price;
			}
		protected String getItemName() {
			return ItemName;
		}
		protected void setItemName(String itemName) {
			ItemName = itemName;
		}
		protected String getItemID() {
			return ItemID;
		}
		protected void setItemID(String itemID) {
			ItemID = itemID;
		}
		protected double getPrice() {
			return Price;
		}
		protected void setPrice(double price) {
			Price = price;
		}
		FoodItem(FoodItem Item){
			this.ItemID = Item.ItemID;
			this.ItemName = Item.ItemName;
				this.Price = Item.Price;
		}
}