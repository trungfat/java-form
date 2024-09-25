/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import QlyBenhnhan.ChangePass;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author duong
 */
public class sql_Benhnhan {
    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;
    
    String mabn;
    
    public sql_Benhnhan(String mabn){
        try {
            ketnoi = KetNoi.KNCSDL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kết nối không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        this.mabn = mabn;
    }
    
    public ArrayList hienthi_Pro(){
        ArrayList<String> ht_Pro = new ArrayList<>();
        String lenh = "Select * from tt_bn Where Mabn = " + mabn;
        try {
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            if (docdulieu.next()) {
                ht_Pro.add(docdulieu.getString(2));
                ht_Pro.add(docdulieu.getString(3));
                ht_Pro.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(4))));
                ht_Pro.add(docdulieu.getString(5));
                ht_Pro.add(docdulieu.getString(6));
                ht_Pro.add(docdulieu.getString(7));
                ht_Pro.add(docdulieu.getString(8));
                ht_Pro.add(docdulieu.getString(9));
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return ht_Pro;
    }

    public ArrayList<ArrayList<Object>> hienthi_Ba(){
        ArrayList<ArrayList<Object>> ht_Ba = new ArrayList<>();
        String lenh = "Select Maba, Mabs, Ngaynv, Tongtien from tt_ba Where Mabn = " + mabn;
        try {
            thaotac = ketnoi.createStatement();
        
            docdulieu = thaotac.executeQuery(lenh);
            while (docdulieu.next()) {
                ArrayList<Object> ht_Ba_row = new ArrayList<>();
                ht_Ba_row.add(docdulieu.getString(1));
                ht_Ba_row.add(docdulieu.getString(2));
                ht_Ba_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(3))));
                ht_Ba_row.add(docdulieu.getString(4));;
                ht_Ba.add(ht_Ba_row);
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return ht_Ba;
    }    
    
    public ArrayList<ArrayList<Object>> hienthi_Dt(){
        ArrayList<ArrayList<Object>> ht_Dt = new ArrayList<>();
        String lenh = "Select Madt, Ngaynv, Thanhtien from tt_dt, tt_ba Where tt_dt.Maba = tt_ba.Maba And Mabn = " + mabn;
        try {
            thaotac = ketnoi.createStatement();
        
            docdulieu = thaotac.executeQuery(lenh);
            while (docdulieu.next()) {
                ArrayList<Object> ht_Dt_row = new ArrayList<>();
                ht_Dt_row.add(docdulieu.getString(1));
                ht_Dt_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(2))));
                ht_Dt_row.add(docdulieu.getString(3));;
                ht_Dt.add(ht_Dt_row);
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return ht_Dt;
    } 
     
    public void Save(String Hoten, Object date, String Cccd, String Bhyt, String Sdt, String Email, String Diachi){
        try {
            String lenh = "Update tt_bn Set "
               + "HoTen = '" + Hoten + "', "
               + "NgaySinh = '" + new SimpleDateFormat("yyyy/MM/dd").format(date) + "', "
               + "Cccd = '" + Cccd + "', "
               + "Bhyt = '" + Bhyt + "', "
               + "Sdt = '" + Sdt + "', "
               + "Email = '" + Email + "', "
               + "DiaChi = '" + Diachi + "' Where Mabn = " + mabn;
            if(!Hoten.equals("") && !date.equals("") && !Cccd.equals("") && !Bhyt.equals("") && !Diachi.equals("")){
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu thay đổi này?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    thaotac = ketnoi.createStatement();
                    thaotac.executeUpdate(lenh);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin!!!", "Thông báo",JOptionPane.OK_OPTION);
            }  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList click_Ba(String maba){
        ArrayList<String> click_Ba = new ArrayList<>();
        String lenh = "Select Hoten, Chandoan from tt_bs, tt_ba Where tt_bs.Mabs = tt_ba.Mabs And Maba = " + maba;
        try {
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            if(docdulieu.next()){
                    click_Ba.add(docdulieu.getString(1));
                    click_Ba.add(docdulieu.getString(2));
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return click_Ba;
    }
    
    public ArrayList<ArrayList<Object>> hienthi_SearchBa(String searchBa){
        ArrayList<ArrayList<Object>> sr_Ba = new ArrayList<>();
        String lenh = "Select Maba, Mabs, Ngaynv, Tongtien from tt_ba Where "
                    + "(Maba like '%" + searchBa + "%' Or "
                    + "Ngaynv like '%" + searchBa + "%') And "
                    + "Mabn = " + mabn;
        try {
            if (!searchBa.equals("")) {
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
                        sr_Ba_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(3))));
                        sr_Ba_row.add(docdulieu.getString(4));;
                        sr_Ba.add(sr_Ba_row);
                    } while (docdulieu.next());
                    docdulieu.close();
                }
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return sr_Ba;
    } 

    public ArrayList<ArrayList<Object>> click_Dt(String madt){
        ArrayList<ArrayList<Object>> click_Dt = new ArrayList<>();
        try {
            String lenh = "Select * from tt_thuoc Where Madt = " + madt;
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            while (docdulieu.next()) {
                ArrayList<Object> click_Dt_row = new ArrayList<>();
                click_Dt_row.add(docdulieu.getString(2));
                click_Dt_row.add(docdulieu.getString(3));
                click_Dt.add(click_Dt_row);
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return click_Dt;
    }  
    
    public ArrayList<ArrayList<Object>> hienthi_SearchDt(String searchDt){
        ArrayList<ArrayList<Object>> sr_Dt = new ArrayList<>();
        String lenh = "Select Madt, Ngaynv, Thanhtien from tt_dt, tt_ba Where tt_dt.Maba = tt_ba.Maba And "
                        + "(Madt like '%" + searchDt + "%' Or "
                        + "Ngaynv like '%" + searchDt + "%') And "
                        + "Mabn = " + mabn;
        try {
            if(!searchDt.equals("")){
                thaotac = ketnoi.createStatement();

                docdulieu = thaotac.executeQuery(lenh);
                if (!docdulieu.next()) {
                    sr_Dt = null;
                }
                else{
                    do {
                        ArrayList<Object> sr_Dt_row = new ArrayList<>();
                        sr_Dt_row.add(docdulieu.getString(1));
                        sr_Dt_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(2))));
                        sr_Dt_row.add(docdulieu.getString(3));;
                        sr_Dt.add(sr_Dt_row);
                    }while (docdulieu.next());
                    docdulieu.close();
                }
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);    
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return sr_Dt;
    } 
}
