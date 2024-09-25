/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package SQL;

import java.sql.*;
/**
 *
 * @author duong
 */
public class KetNoi {

    /**
     * @param args the command line arguments
     */
    public static Connection KNCSDL() throws SQLException {
        // TODO code application logic here
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/qlbn", "root", "");
        return cn;
    }
    
}
