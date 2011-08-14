package com.junjie;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jbu
 */
public class DatabaseTest extends BaseTest {

  @Test
  public void testConnectByJDBC() throws Exception {
    Class.forName("org.hsqldb.jdbcDriver");
    String url = "jdbc:hsqldb:hsql://localhost/xdb";
//      String url = "jdbc:hsqldb:file:src/main/resources/db/mydbname";
    String username = "sa";
    String password = "";

    Connection connection = DriverManager.getConnection(url, username,
      password);

    Statement statement = connection.createStatement();
    try {
      statement.executeQuery("select * from sec_edgar_filer;");
    } catch (SQLException ex) { // if no table then create one.
      statement
        .execute("CREATE TABLE sec_edgar_filer ( cik VARCHAR(255) NOT NULL);");
    }

    try {
      String cur = "" + System.currentTimeMillis();
      String sql = "insert into sec_edgar_filer values(?)";
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, "Hello World! Now is " + System.currentTimeMillis());
//            statement.execute("insert into sec_edgar_filer values ( 'Hello World! "
//                    + cur + "' )");
      ps.execute();
      ResultSet resultset = statement
        .executeQuery("select * from sec_edgar_filer;");

      int count = 0;
      while (resultset.next()) {
        System.out.println(resultset.getString(1));
        count++;
      }
      System.out.println("total rows returned: " + count);
    } catch (Exception e) {
      //    statement.execute("drop table sec_edgar_filer;");
    } finally {

      //     try {statement.execute("SHUTDOWN;");} catch (Exception ex) {}
      try {
        connection.close();
      } catch (Exception ex) {
      }
    }
  }
}
