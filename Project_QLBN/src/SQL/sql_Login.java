/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author duong
 */
public class sql_Login {
    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;

    public sql_Login() {
        try {
            ketnoi = KetNoi.KNCSDL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kết nối không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String login(String User, String Pass){
        String Pq = "";
        String lenh = "Select Phanquyen from account Where "
                + "user = " + User + " And password = '" + Pass + "'";
        try {
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            if (docdulieu.next()) {
                Pq = docdulieu.getString(1);
            }
            else Pq = "";
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return Pq;
    }
}
