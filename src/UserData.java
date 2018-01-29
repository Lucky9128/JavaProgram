import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 class UserData{
	 private TableData Tables[];
	 protected TableData[] getTables() {
		return Tables;
	}
	protected void setTables(TableData[] tables) {
		Tables = tables;
	}
	protected int getNumberOfTables() {
		return NumberOfTables;
	}
	protected void setNumberOfTables(int numberOfTables) {
		NumberOfTables = numberOfTables;
	}
	protected TableData getTableData(int index) {
		return Tables[index];
	}
	private int NumberOfTables;
	 UserData(){
		 try {
			 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
			 Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery("Select Count(TABLENUMBER) from DEMODB.TABLEDATA");
			 rs.next();
			 NumberOfTables = rs.getInt(1);
			 Tables = new TableData[NumberOfTables];
			 rs.close();
			 rs = st.executeQuery("SELECT * FROM DEMODB.TABLEDATA");
			 rs.next();
			 int TabelNumber, TableStatus;
			 for(int i=0;i<NumberOfTables;i++) {
				 TabelNumber = rs.getInt(1);
				 TableStatus = rs.getInt(2);
				 Tables[i] = new TableData(TabelNumber, TableStatus);
				 rs.next();
			 }
			 rs.close();
			 st.close();
			 con.close();
		 }catch(SQLException e) {
			System.out.println(e.getMessage()); 
		 }
	 }
	 protected int getTableIndexByName(String Name) {
			int index =-1;
			for(int i=0;i<NumberOfTables;i++) {
				if(String.valueOf(Tables[i].getTableNumber()).equals(Name)) {
					index = i;
					break;
				}
			}
			return index;
		 } 
	 protected TableData getTableDataByName(String Name) {
				int index =-1;
				for(int i=0;i<NumberOfTables;i++) {
					if(String.valueOf(Tables[i].getTableNumber()).equals(Name)) {
						index = i;
						break;
					}
				}
				return getTableData(index);
			 }
}

 class TableData{
		private int TabelNumber;
		private int TableStatus;   //0=>Available  1=> Booked  2=> Reserved
		private String OwnerName;
		private int Capacity;
		protected OrderDetail getOrder() {
			return Order;
		}
		protected void setOrderFromDatabase() {
			try {

				 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
				 Statement st = con.createStatement();
				 ResultSet rs = st.executeQuery("Select Count(ID) from DEMODB.ORDERDETAIL");
				 rs.next();
				 for(int i=0;i<rs.getInt(1);i++) {
					 rs = st.executeQuery("SELECT TABLEDATA.ORDERID FROM DEMODB.TABLEDATA WHERE TABLEDATA.TABLENUMBER = "+TabelNumber);
					 rs.next();
					 String Orderid = rs.getString(1);
					 int num =Integer.parseInt(Orderid.substring(Orderid.indexOf("O")+1,Orderid.length()));
					 OrderDetail.Number = OrderDetail.Number>num?OrderDetail.Number:num;
					 Order = new OrderDetail();
					 Order.setOrderId(rs.getString(1));
					 rs = st.executeQuery("SELECT ORDERDETAIL.ITEMS, ORDERDETAIL.FOODID, ORDERDETAIL.QTY, ORDERDETAIL.PRICE FROM DEMODB.ORDERDETAIL WHERE ORDERDETAIL.ID ='"+Order.getOrderId()+"'");
					 rs.next();
					 Order.setNumberofItems(1);
					 Order.setQty(rs.getString(3));
					 Order.setFoodItemId(rs.getString(2));
					 Order.setPrice(rs.getString(3));
				 }
				 rs.close();
				 st.close();
				 con.close();
			}catch(Exception e) {
				System.out.println("Error in setOrderFromDatabase "+e.getMessage());
			}
		}
		protected void setOrder(OrderDetail order) {
			Order = order;
		}
		private OrderDetail Order;
		protected int getCapacity() {
			return Capacity;
		}
		protected void setCapacity(int capacity) {
			Capacity = capacity;
		}
		TableData(int Num,int Status){
			this.TabelNumber = Num;
			this.TableStatus = Status;
			//getData();
		}
		protected void CreateNewOrder() {
			Order = new OrderDetail();
		}
		TableData(int Num,int Status,String OrderId){
			this.TabelNumber = Num;
			this.TableStatus = Status;
			this.Order = new OrderDetail(Num);
			//getData();
		}
		protected void setTableStatus(int Status) {
			this.TableStatus = Status;
		}
		protected void setTableNumber(int Num) {
			this.TabelNumber = Num;
		}
		protected void setTableOwner(String Owner) {
			this.OwnerName = Owner;
		}
		protected int getTableStatus() {
			return this.TableStatus;
		}
		protected int getTableNumber() {
			return this.TabelNumber;
		}
		protected String getOwnerName() {
			return this.OwnerName;
		}
		protected void Emptytable() {
			this.TableStatus =0;
			this.OwnerName = null;
		}
		 protected void Update() {
			 
			 try {
					Connection TabelCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
					Statement TableStat = TabelCon.createStatement();
					
					int abbc = TableStat.executeUpdate("Insert into DEMODB.ORDERDETAIL values ('"+Order.getOrderId()+"',"+Order.getNumberofItems()+
							","+TabelNumber+",'"+Order.getFoodItemId()+"','"+Order.getQtyString()+"','"+Order.getPriceString()+"')");
					
					int TabelResult = TableStat.executeUpdate("Update DEMODB.TABLEDATA SET TABLEDATA.STATUS = "+TableStatus+
							", TABLEDATA.ORDERID = '"+Order.getOrderId()+"'"+
							" WHERE TABLEDATA.TABLENUMBER = "+this.getTableNumber());
					TableStat.close();
					TabelCon.close();
				}catch(SQLException slq) {
					System.out.println(slq.getMessage());
				}
		 }
		private void getData() {
			switch(this.TableStatus) {
			case 0:
				try {
					Connection TabelCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
					Statement TableStat = TabelCon.createStatement();
					ResultSet TabelResult = TableStat.executeQuery("SELECT CAPACITY FROM DEMODB.TABLEDETAILS INNER JOIN"
							+ " DEMODB.TABLEDATA ON DEMODB.TABLEDATA.TABLENUMBER = DEMODB.TABLEDETAILS.TABLENUMBER"
							+ " AND DEMODB.TABLEDETAILS.TABLENUMBER ="+this.TabelNumber);
					TabelResult.next();
					this.setCapacity(TabelResult.getInt(1));
					TabelResult.close();
					TableStat.close();
					TabelCon.close();
				}catch(SQLException slq) {
					System.out.println(slq.getMessage());
				}
			break;
			case 1:
				try {
					Connection TabelCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
					Statement TableStat = TabelCon.createStatement();
					ResultSet TabelResult = TableStat.executeQuery("SELECT TABLEDETAILS.OWNER,TABLEDETAILS.CAPACITY FROM DEMODB.TABLEDETAILS INNER JOIN"
							+ " DEMODB.TABLEDATA ON DEMODB.TABLEDATA.TABLENUMBER = DEMODB.TABLEDETAILS.TABLENUMBER"
							+ " AND DEMODB.TABLEDETAILS.TABLENUMBER ="+this.TabelNumber);
					TabelResult.next();
					this.setCapacity(TabelResult.getInt(2));
					this.setTableOwner(TabelResult.getString(1));
					TabelResult.close();
					TableStat.close();
					TabelCon.close();
				}catch(SQLException slq) {
					System.out.println(slq.getMessage());
				}
			break;
			case 2:
				try {
					Connection TabelCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
					Statement TableStat = TabelCon.createStatement();
					ResultSet TabelResult = TableStat.executeQuery("SELECT TABLEDETAILS.OWNER,TABLEDETAILS.CAPACITY FROM DEMODB.TABLEDETAILS INNER JOIN"
							+ " DEMODB.TABLEDATA ON DEMODB.TABLEDATA.TABLENUMBER = DEMODB.TABLEDETAILS.TABLENUMBER"
							+ " AND DEMODB.TABLEDETAILS.TABLENUMBER ="+this.TabelNumber);
					TabelResult.next();
					this.setCapacity(TabelResult.getInt(2));
					this.setTableOwner(TabelResult.getString(1));
					TabelResult.close();
					TableStat.close();
					TabelCon.close();
				}catch(SQLException slq) {
					System.out.println(slq.getMessage());
				}
			break;
			
			}
		}
	}