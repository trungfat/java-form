/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author duong
 */
public class sql_Qly_Benhan {

    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;
    
    public sql_Qly_Benhan(){
        try {
            ketnoi = KetNoi.KNCSDL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kết nối không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ArrayList<ArrayList<Object>> hienthi_Ba(){
        ArrayList<ArrayList<Object>> ht_Ba = new ArrayList<>();
        try {
            String lenh = "Select tt_ba.Maba, tt_ba.Mabn, tt_bn.HoTen, tt_bn.NgaySinh, tt_ba.Mabs, tt_ba.Ngaynv, tt_ba.Ngayxv, tt_ba.Chandoan, tt_ba.Tongtien From tt_ba, tt_bn, tt_bs "
                    + "WHERE tt_ba.Mabn = tt_bn.Mabn and tt_ba.Mabs = tt_bs.Mabs";
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            
            while (docdulieu.next()) {  
                ArrayList<Object> ht_Ba_row = new ArrayList<>();
                ht_Ba_row.add(docdulieu.getString(1));
                ht_Ba_row.add(docdulieu.getString(2));
                ht_Ba_row.add(docdulieu.getString(3));
                ht_Ba_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(4))));
                ht_Ba_row.add(docdulieu.getString(5));
                ht_Ba_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(6))));
                ht_Ba_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(7))));
                ht_Ba_row.add(docdulieu.getString(8));
                ht_Ba_row.add(docdulieu.getString(9));
                ht_Ba.add(ht_Ba_row);
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return ht_Ba;
    }
    
    public boolean check_Bhyt(String Mabn){
        try {
            String lenh = "Select Bhyt From tt_bn Where Mabn = " + Mabn;
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            
            if(docdulieu.next()){
                return true;
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }  
        return false;
    }
    
    public String cbx_Bn_click(String Mabn){
        String ht = "";
        try {
            String lenh = "Select HoTen From tt_bn WHERE Mabn = " + Mabn;
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            if (docdulieu.next()) {
                ht  = docdulieu.getString(1);
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return ht;
    }
   
    public String cbx_Bs_click(String Mabs){
        String ht = "";
        try {
            String lenh = "Select Hoten From tt_bs WHERE Mabs = " + Mabs;
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            if (docdulieu.next()) {
                ht  = docdulieu.getString(1);
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return ht;
    }
    
    public void add_Ba(String Mabn, String Mabs, Object DateNhap, Object DateXuat, String Chandoan, String Vienphi){
        try {
            if(!Mabn.equals("") && !Mabs.equals("") && !DateNhap.equals("") && !DateXuat.equals("") && !Chandoan.equals("")){ 
                String lenh = "Insert into tt_ba Values (NULL, "
                        + Mabn + ", "
                        + Mabs + ", "
                        + "'" + new SimpleDateFormat("yyyy/MM/dd").format(DateNhap) + "', "
                        + "'" + new SimpleDateFormat("yyyy/MM/dd").format(DateXuat) + "', "
                        + "'" + Chandoan + "', ";
                
                if (check_Bhyt(Mabn)){
                    lenh = lenh + "0)" ;
                }
                else lenh = lenh + Vienphi + ")";
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
                
                String Maba = "";
                lenh = "Select MAX(Maba) from tt_ba";
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);
                if (docdulieu.next()) {
                    Maba = docdulieu.getString(1);
                }
                docdulieu.close();
                
                lenh = "Insert into tt_dt Values(NULL, " + Maba + ", 0)";
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
            else{
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!!!", "Thông báo", JOptionPane.OK_OPTION);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ArrayList<ArrayList<Object>> Search_Ba(String searchBa){
        ArrayList<ArrayList<Object>> sr_Ba = new ArrayList<>();
        try {
            if(!searchBa.equals("")){
                String lenh = "Select Maba, tt_ba.Mabn, tt_bn.HoTen, tt_bn.NgaySinh, tt_ba.Mabs, Ngaynv, Ngayxv, Chandoan, Tongtien, tt_bs.Hoten From tt_ba, tt_bn, tt_bs "
                            + "WHERE tt_ba.Mabn = tt_bn.Mabn and tt_ba.Mabs = tt_bs.Mabs and "
                            + "(tt_ba.Maba like '%" + searchBa + "%' or "
                            + "tt_ba.Mabn like '%" + searchBa + "%')";
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);
                
                if (!docdulieu.next()) {
                    sr_Ba = null;
                }
                else{
                    do {  
                        ArrayList<Object> sr_Ba_row = new ArrayList<>();
                        sr_Ba_row.add(docdulieu.getString(1));
                        sr_Ba_row.add(docdulieu.getString(2));
                        sr_Ba_row.add(docdulieu.getString(3));
                        sr_Ba_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(4))));
                        sr_Ba_row.add(docdulieu.getString(5));
                        sr_Ba_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(6))));
                        sr_Ba_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(7))));
                        sr_Ba_row.add(docdulieu.getString(8));
                        sr_Ba_row.add(docdulieu.getString(9));
                        sr_Ba.add(sr_Ba_row);
                    }while (docdulieu.next());
                    docdulieu.close();
                }
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Tìm kiếm dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return sr_Ba;
    }
    
    public String Click_Ba(String Maba){
        String Thanhtien = "";
        try {
            String lenh = "Select Thanhtien From tt_dt Where Maba = " + Maba;
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            if(docdulieu.next()){
                    Thanhtien = docdulieu.getString(1);
                }
            else Thanhtien = "0";
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return Thanhtien;
    }
    
    public void Edit_Ba(String maba,String mabn, String mabs, Object datenhap, Object datexuat, String chandoan){
        try {
            if(!mabn.equals("") && !mabs.equals("") && !datenhap.equals("") && !datexuat.equals("") && !chandoan.equals("")){ 
                String lenh = "Update tt_ba Set "
                        + "Ngaynv = '" + new SimpleDateFormat("yyyy/MM/dd").format(datenhap) + "', "
                        + "Ngayxv = '" + new SimpleDateFormat("yyyy/MM/dd").format(datexuat) + "', "
                        + "Chandoan = '" + chandoan + "' Where Maba = " + maba;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
            else{
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!!!", "Thông báo", JOptionPane.OK_OPTION);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void Delete_Ba(String maba){
        try {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thông tin này?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                String Madt = "";
                String lenh = "Select Madt from tt_dt Where Maba = " + maba;
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);
                if (docdulieu.next()) {
                    Madt = docdulieu.getString(1);
                }
                docdulieu.close();

                if(!Madt.equals("")){
                    lenh = "Delete from tt_thuoc Where Madt = " + Madt;
                    thaotac = ketnoi.createStatement();
                    thaotac.executeUpdate(lenh);
                }

                lenh  ="Delete from tt_dt Where Maba = " + maba;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);

                lenh  ="Delete from tt_ba Where Maba = " + maba;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
                hienthi_Ba();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }   
    }
}
