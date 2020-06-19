import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class JDBCFileforInsertingData{
    public static void main(String[] args){
        BufferedReader brCompany = null;
        BufferedReader brExchange = null;
        BufferedReader brOrders = null;
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SORT","root","");
            Statement st = con.createStatement();
            )
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            brCompany = new BufferedReader(new FileReader("/home/shiva/Downloads/Companies.txt"));
            brExchange = new BufferedReader(new FileReader("/home/shiva/Downloads/Exchanges.txt"));
            brOrders = new BufferedReader(new FileReader("/home/shiva/Downloads/OrderData.txt")); 
            System.out.println("Connected");
            String line = "";
            while((line = brCompany.readLine())!=null){
               String[] arr = line.split(" ");
               PreparedStatement ps = con.prepareStatement("insert into trading_companies values(?,?,?)");
               ps.setString(1, arr[0]);
               ps.setString(2, arr[1]);
               ps.setString(3, arr[2]);
               ps.executeUpdate();
            }
            System.out.println("Connected1");
            line = "";
            while((line = brExchange.readLine())!=null){
               String[] arr = line.split(" ");
               PreparedStatement ps = con.prepareStatement("insert into exchange values(?,?,?,?,?)");
               ps.setString(1, arr[0]);
               ps.setInt(2, Integer.parseInt(arr[3]));
               ps.setString(3, arr[1]);
               ps.setInt(4, Integer.parseInt(arr[2]));
               ps.setInt(5,Integer.parseInt(arr[4]));
               ps.executeUpdate();
            }
            line = "";
            int i = 1;
            Random rand = new Random();
            while((line = brOrders.readLine())!=null){
                String[] arr = line.split(" ");
                String status = "Incomplete " + String.valueOf(rand.nextInt(100));
                PreparedStatement ps = con.prepareStatement("insert into order_stock values(?,?,?,?,?,?,?,?,?,?)");
                ps.setInt(1,i);
                ps.setInt(2,Integer.parseInt(arr[0]));
                ps.setString(3,arr[1]);
                ps.setString(4, status);
                ps.setInt(5,Integer.parseInt(arr[3]));
                ps.setString(6, java.time.LocalDate.now().toString());
                ps.setString(7,arr[2]);
                ps.setString(8,arr[4]);
                ps.setString(9,arr[5]);
                ps.setString(10,arr[1]);
                int k = ps.executeUpdate();
                i++;
            }

        }
        catch(SQLException | IOException | ClassNotFoundException e){}
    }
}
