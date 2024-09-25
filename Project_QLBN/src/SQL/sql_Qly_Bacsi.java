/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author duong
 */
public class sql_Qly_Bacsi {
    
    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;
    
    public sql_Qly_Bacsi(){
        try {
            ketnoi = KetNoi.KNCSDL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kết nối không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<ArrayList<Object>> hienthi_Bs(){
        ArrayList<ArrayList<Object>> ht_Bs = new ArrayList<>();
        try {
            String lenh = "Select * from tt_bs";
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            
            while (docdulieu.next()){
                ArrayList<Object> ht_Bs_row = new ArrayList<>();
                ht_Bs_row.add(docdulieu.getString(1));
                ht_Bs_row.add(docdulieu.getString(2));
                ht_Bs_row.add(docdulieu.getString(3));
                ht_Bs_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(4))));
                ht_Bs_row.add(docdulieu.getString(5));
                ht_Bs_row.add(docdulieu.getString(6));
                ht_Bs_row.add(docdulieu.getString(7));
                ht_Bs_row.add(docdulieu.getString(8));
                ht_Bs.add(ht_Bs_row);
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return ht_Bs;
    }

    public void add_Bs(String hoten, String gt, Object date, String cccd, String diachi, String sdt, String email){
        try {
            if(!hoten.equals("") && !gt.equals("") && !date.equals("") && !cccd.equals("") && !diachi.equals("") && !sdt.equals("")){ 
                String lenh = "Insert into tt_bs Values (NULL, "
                        + "'" + hoten + "', "
                        + "'" + gt + "', "
                        + "'" + new SimpleDateFormat("yyyy/MM/dd").format(date) + "', "
                        + "'" + cccd + "', "
                        + "'" + sdt + "', "
                        + "'" + email + "', "
                        + "'" + diachi + "')";
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
                
                String mabs = "";
                lenh = "Select Max(Mabs) from tt_bs";
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);
                if (docdulieu.next()) {
                    mabs = docdulieu.getString(1);
                }
                docdulieu.close();
                
                lenh = "Insert into account Values(" + mabs + ", "
                        + "'" + mabs + "', 'BS')";
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
            else{
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!!!", "Thông báo", JOptionPane.OK_OPTION);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }    
    }    
    
    public ArrayList<ArrayList<Object>> search_Bs(String searchBs) {
        ArrayList<ArrayList<Object>> sr_Bs = new ArrayList<>();
        try {
            if (!searchBs.equals("")) {
                String lenh = "Select * from tt_bs Where "
                        + "Mabs like '%" + searchBs + "%' Or "
                        + "HoTen like '%" + searchBs + "%' Or "
                        + "Cccd like '%" + searchBs + "%' Or "
                        + "Sdt like '%" + searchBs + "%' Or "
                        + "Email like '%" + searchBs+ "%' Or "
                        + "DiaChi like '%" + searchBs + "%'";
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);

                if(!docdulieu.next()){
                    sr_Bs = null;
                }
                else{
                    do {
                        ArrayList<Object> sr_Bs_row = new ArrayList<>();
                        sr_Bs_row.add(docdulieu.getString(1));
                        sr_Bs_row.add(docdulieu.getString(2));
                        sr_Bs_row.add(docdulieu.getString(3));
                        sr_Bs_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(4))));
                        sr_Bs_row.add(docdulieu.getString(5));
                        sr_Bs_row.add(docdulieu.getString(6));
                        sr_Bs_row.add(docdulieu.getString(7));
                        sr_Bs_row.add(docdulieu.getString(8));
                        sr_Bs.add(sr_Bs_row);
                    }while (docdulieu.next());
                    docdulieu.close();    
                }        
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!!!", "Thông báo", JOptionPane.WARNING_MESSAGE); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không tìm được dữ liệu", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return sr_Bs;
    } 
    
    public void edit_Bs(String Mabs, String hoten, String gt, Object date, String cccd, String diachi, String sdt, String email){
        try {
            if(!hoten.equals("") && !gt.equals("") && !date.equals("") && !cccd.equals("") && !diachi.equals("") && !sdt.equals("")){ 
                String lenh = "Update tt_bs SET "
                        + "HoTen = '" + hoten + "', "
                        + "GioiTinh = '" + gt+ "', "
                        + "NgaySinh = '" + new SimpleDateFormat("yyyy/MM/dd").format(date) + "', "
                        + "Cccd = '" + cccd + "', "
                        + "Sdt = '" + sdt + "', "
                        + "Email = '" + email + "', "
                        + "DiaChi = '" + diachi + "' Where Mabs = " + Mabs;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
            else{
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!!!", "Thông báo", JOptionPane.OK_OPTION);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    public void delete_Bs(String Mabs){
        try {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thông tin này?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                String lenh = "Delete from tt_bs Where Mabs = " + Mabs;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);

                lenh  ="Delete from account Where user = " + Mabs;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
