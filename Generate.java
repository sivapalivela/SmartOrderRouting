import java.util.*;
import java.io.*;
public class Generate {
    public static void main(String[] args){
       BufferedReader brCompany = null;
       BufferedReader brExchange = null;
       BufferedReader brUsernames = null;
       BufferedWriter brOrders = null;
       try{
           Random rand = new Random(); 
           brCompany = new BufferedReader(new FileReader("/home/shiva/Downloads/Companies.txt"));
           brExchange = new BufferedReader(new FileReader("/home/shiva/Downloads/Exchanges.txt"));
           brUsernames = new BufferedReader(new FileReader("/home/shiva/Downloads/usernames.txt"));
           brOrders = new BufferedWriter(new FileWriter("/home/shiva/Downloads/OrderData.txt"));
           String[] orderType = {"BuyOrder","SellOrder"};
           List<String> companyCode = new ArrayList<>();
           List<String> exchangeCode = new ArrayList<>();
           List<String> usernameCode = new ArrayList<>(); 

           String line = "";
           while((line = brCompany.readLine())!=null){
               String[] arr = line.split(" ");
               companyCode.add(arr[0]);
           }
           line = "";
           while((line = brExchange.readLine())!=null){
               String[] arr = line.split(" ");
               exchangeCode.add(arr[0]);
           }
           line = "";
           while((line = brUsernames.readLine())!=null){
            String[] arr = line.split(" ");
            usernameCode.add(arr[0]);
           }

           for(int i=0;i<1000;i++){
               String l = "";
               l += (rand.nextInt(250) + " " + 
                     exchangeCode.get(rand.nextInt(exchangeCode.size())) + " " + 
                     orderType[rand.nextInt(2)] + " " + 
                     rand.nextInt(600) + " " + 
                     companyCode.get(rand.nextInt(companyCode.size())) + " " +
                     usernameCode.get(rand.nextInt(usernameCode.size())) + "\n");
                brOrders.write(l);
           }
       } 
       catch(Exception e){
           System.out.println(e.getMessage());
       }
       finally{
           try{
               brCompany.close();
               brExchange.close();
               brOrders.close();
           }
           catch(IOException e){
               System.out.println(e.getMessage());
           }
       }
    }
}