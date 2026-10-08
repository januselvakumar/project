import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnection {
   static String url = "jdbc:mysql://localhost:3306/collegecoursedb";
   static String username = "root";
   static String password = "Janukumar2007";

   public dbconnection() {
   }

   public static Connection getconnection() {
      Connection var0 = null;

      try {
         var0 = DriverManager.getConnection(url, username, password);
         System.out.println("Database Connected successfully!");
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      return var0;
   }
}