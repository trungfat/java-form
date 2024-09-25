/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author duong
 */
public class sql_Qly_Dtbn {
    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;

    public sql_Qly_Dtbn() {
        try {
            ketnoi = KetNoi.KNCSDL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kết nối không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ArrayList<ArrayList<Object>> hienthi_Dtbn(String maba){
        ArrayList<ArrayList<Object>> ht_Dtbn = new ArrayList<>();
        try {
            String lenh = "Select * From tt_thuoc, tt_dt Where tt_thuoc.Madt = tt_dt.Madt and tt_dt.Maba = " + maba;
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            
            while (docdulieu.next()) {
                ArrayList<Object> ht_Dtbn_row = new ArrayList<>();
                ht_Dtbn_row.add(docdulieu.getString(2));
                ht_Dtbn_row.add(docdulieu.getString(3));
                ht_Dtbn_row.add(docdulieu.getString(4));
                ht_Dtbn_row.add(docdulieu.getString(5));
                ht_Dtbn.add(ht_Dtbn_row);
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return ht_Dtbn;
    }
    
    public ArrayList<Object> hienthi_tt_ma(String maba){
        ArrayList<Object> ht_tt_ma = new ArrayList<>();
        try {
            String lenh = "Select Madt, Thanhtien From tt_dt Where Maba = " + maba;
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            
            if (docdulieu.next()) {
                ht_tt_ma.add(docdulieu.getString(1)) ;
                ht_tt_ma.add(docdulieu.getString(2));
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return ht_tt_ma;
    }
    
    public void add_dtbn(String maba, String madt, String Tenthuoc, String Soluong, String Dongia){
        try {
            
            if(!Tenthuoc.equals("") && !Soluong.equals("") && !Dongia.equals("")){
                int Thanhtien = Integer.parseInt(Soluong) * Integer.parseInt(Dongia);
                String lenh = "Insert into tt_thuoc Values("
                        + madt + ", '"
                        + Tenthuoc + "', "
                        + Soluong + ", "
                        + Dongia + ", "
                        + Thanhtien + ")";
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);

                lenh = "Update tt_dt Set Thanhtien = Thanhtien + " + Thanhtien + " Where Madt = " + madt;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
                
                lenh = "Update tt_ba Set Tongtien = Tongtien + (Select Thanhtien From tt_dt Where Maba = " + maba + ") Where Maba = " + maba;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin trước khi thêm!!!", "Thông báo", JOptionPane.OK_OPTION);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ArrayList<ArrayList<Object>> Search_Dtbn(String maba, String searchdt){
        ArrayList<ArrayList<Object>> sr_Dtbn = new ArrayList<>();
        try {
            String lenh = "Select * From tt_thuoc, tt_dt Where tt_thuoc.Madt = tt_dt.Madt and tt_dt.Maba = " + maba
                        + " and Tenthuoc like '%" + searchdt + "%'";
            if (!searchdt.equals("")) {
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);

                if (!docdulieu.next()) {
                    sr_Dtbn = null;
                }
                else{
                    do {
                        ArrayList<Object> sr_Dtbn_row = new ArrayList<>();
                        sr_Dtbn_row.add(docdulieu.getString(2));
                        sr_Dtbn_row.add(docdulieu.getString(3));
                        sr_Dtbn_row.add(docdulieu.getString(4));
                        sr_Dtbn_row.add(docdulieu.getString(5));
                        sr_Dtbn.add(sr_Dtbn_row);
                    } while(docdulieu.next());
                    docdulieu.close();   
                }
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!!!", "Thông báo", JOptionPane.WARNING_MESSAGE); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Tìm kiếm dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return sr_Dtbn;
    }  
    
    public void edit_dtbn(String madt, String Tenthuoc, String Soluong, String Dongia, String DongiaOld){
        try {
            if(!Tenthuoc.equals("") && !Soluong.equals("") && !Dongia.equals("")){
                int Thanhtien = Integer.parseInt(Soluong) * Integer.parseInt(Dongia);
                int Thanhtienold = Integer.parseInt((String) DongiaOld);
                String lenh = "Update tt_thuoc Set "
                        + "Soluong = " + Soluong + ", "
                        + "Dongia = " + Dongia + ", "
                        + "Thanhtien = " + Thanhtien + " Where Tenthuoc = '" + Tenthuoc + "'";
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);

                lenh = "Update tt_dt Set Thanhtien = Thanhtien + " + Thanhtien + " - " + Thanhtienold + " Where Madt = " + madt;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin trước khi sửa!!!", "Thông báo", JOptionPane.OK_OPTION);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void delete_dtbn(String maba, String madt, String Tenthuoc, String DongiaOld){
        try {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thông tin này?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                int Thanhtienold = Integer.parseInt((String) DongiaOld);
                String lenh = "Delete from tt_thuoc Where Tenthuoc = '" + Tenthuoc + "'";
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);

                lenh = "Update tt_dt Set Thanhtien = Thanhtien - " + Thanhtienold + " Where Madt = " + madt;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);

                lenh = "Update tt_ba Set Tongtien = Tongtien + (Select Thanhtien From tt_dt Where Maba = " + maba + ") - " + Thanhtienold + " Where Maba = " + maba;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
}
