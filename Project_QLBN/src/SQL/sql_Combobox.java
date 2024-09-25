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
public class sql_Combobox {
    
    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;

    public sql_Combobox(){
        try {
            ketnoi = KetNoi.KNCSDL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kết nối không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<Object> Cbx_Bn(){
        String lenh = "Select Mabn from tt_bn";
        ArrayList<Object> cbx_Bn = new ArrayList<>();
        try {
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);

            cbx_Bn.add("");
            while (docdulieu.next()) {                
                cbx_Bn.add(docdulieu.getString(1));
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return cbx_Bn;
    }
 
    public ArrayList<Object> Cbx_Bs(){
        String lenh = "Select Mabs from tt_bs";
        ArrayList<Object> cbx_Bs = new ArrayList<>();
        try {
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            cbx_Bs.add("");
            while (docdulieu.next()) {                
                cbx_Bs.add(docdulieu.getString(1));
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return cbx_Bs;
    }
    
    public ArrayList<Object> Cbx_Ba(){
        String lenh = "Select Maba from tt_ba";
        ArrayList<Object> cbx_Ba = new ArrayList<>();
        try {
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            cbx_Ba.add("");
            while (docdulieu.next()) {                
                cbx_Ba.add(docdulieu.getString(1));
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return cbx_Ba;
    }
}
