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
public class sql_Qly_Benhnhan {
    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;

    public sql_Qly_Benhnhan(){
        try {
            ketnoi = KetNoi.KNCSDL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kết nối không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<ArrayList<Object>> hienthi_Bn() {
        ArrayList<ArrayList<Object>> ht_Bn = new ArrayList<>();
        try {
            String lenh = "Select * from tt_bn";
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            
            while (docdulieu.next()){
                ArrayList<Object> ht_Bn_row = new ArrayList<>();
                ht_Bn_row.add(docdulieu.getString(1));
                ht_Bn_row.add(docdulieu.getString(2));
                ht_Bn_row.add(docdulieu.getString(3));
                ht_Bn_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(4))));
                ht_Bn_row.add(docdulieu.getString(5));
                ht_Bn_row.add(docdulieu.getString(6));
                ht_Bn_row.add(docdulieu.getString(7));
                ht_Bn_row.add(docdulieu.getString(8));
                ht_Bn_row.add(docdulieu.getString(9));
                ht_Bn.add(ht_Bn_row);
            }
            docdulieu.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return ht_Bn;
    }
    
    public void add_Bn(String hoten, String gt, Object date, String cccd, String diachi, String sdt, String bhyt, String email){
        try {
            if(!hoten.equals("") && !gt.equals("") && !date.equals("") && !cccd.equals("") && !diachi.equals("") && !sdt.equals("")){ 
                String lenh = "Insert into tt_bn Values (NULL, "
                        + "'" + hoten + "', "
                        + "'" + gt + "', "
                        + "'" + new SimpleDateFormat("yyyy/MM/dd").format(date) + "', "
                        + "'" + cccd + "', "
                        + "'" + bhyt + "', "
                        + "'" + sdt + "', "
                        + "'" + email + "', "
                        + "'" + diachi + "')";
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
                
                String mabn = "";
                lenh = "Select Max(Mabn) from tt_bn";
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);
                if (docdulieu.next()) {
                    mabn = docdulieu.getString(1);
                }
                docdulieu.close();
                
                lenh = "Insert into account Values(" + mabn + ", "
                        + "'" + mabn + "', 'BN')";
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!!!", "Thông báo", JOptionPane.OK_OPTION);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    public ArrayList<ArrayList<Object>> search_Bn(String searchBn) {
        ArrayList<ArrayList<Object>> sr_Bn = new ArrayList<>();
        try {
            if (!searchBn.equals("")) {
                String lenh = "Select * from tt_bn Where "
                                + "Mabn like '%" + searchBn + "%' Or "
                                + "HoTen like '%" + searchBn + "%' Or "
                                + "Cccd like '%" + searchBn + "%' Or "
                                + "Sdt like '%" + searchBn + "%' Or "
                                + "Email like '%" + searchBn + "%' Or "
                                + "DiaChi like '%" + searchBn + "%' Or "
                                + "Bhyt like '%" + searchBn + "%'";
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);

                if(!docdulieu.next()){
                    sr_Bn = null;
                }
                else{
                    do {
                        ArrayList<Object> sr_Bn_row = new ArrayList<>();
                        sr_Bn_row.add(docdulieu.getString(1));
                        sr_Bn_row.add(docdulieu.getString(2));
                        sr_Bn_row.add(docdulieu.getString(3));
                        sr_Bn_row.add(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(docdulieu.getString(4))));
                        sr_Bn_row.add(docdulieu.getString(5));
                        sr_Bn_row.add(docdulieu.getString(6));
                        sr_Bn_row.add(docdulieu.getString(7));
                        sr_Bn_row.add(docdulieu.getString(8));
                        sr_Bn_row.add(docdulieu.getString(9));
                        sr_Bn.add(sr_Bn_row);
                    }while (docdulieu.next());
                    docdulieu.close();    
                }        
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!!!", "Thông báo", JOptionPane.WARNING_MESSAGE); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không tìm được dữ liệu", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return sr_Bn;
    }    
  
    public void edit_Bn(String Mabn, String hoten, String gt, Object date, String cccd, String diachi, String sdt, String bhyt, String email){
        try {
            if(!hoten.equals("") && !gt.equals("") && !date.equals("") && !cccd.equals("") && !diachi.equals("") && !sdt.equals("")){ 
                String lenh = "Update tt_bn SET "
                        + "HoTen = '" + hoten + "', "
                        + "GioiTinh = '" + gt+ "', "
                        + "NgaySinh = '" + new SimpleDateFormat("yyyy/MM/dd").format(date) + "', "
                        + "Cccd = '" + cccd + "', "
                        + "Bhyt = '" + bhyt + "', "
                        + "Sdt = '" + sdt + "', "
                        + "Email = '" + email + "', "
                        + "DiaChi = '" + diachi + "' Where Mabn = " + Mabn;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!!!", "Thông báo", JOptionPane.OK_OPTION);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    public void delete_Bn(String Mabn){
        try {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thông tin này?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                ArrayList<String> maba = new ArrayList<>();
                String lenh = "Select Maba from tt_ba Where Mabn = " + Mabn;
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);
                while (docdulieu.next()) {
                    maba.add(docdulieu.getString(1));
                }
                docdulieu.close();
                if(maba != null){
                    for (int i = 0; i < maba.size(); i++) {
                        lenh = "Select Madt from tt_dt Where Maba = " + maba.get(i);
                        thaotac = ketnoi.createStatement();
                        docdulieu = thaotac.executeQuery(lenh);
                        String madt = "";
                        if (docdulieu.next()) {
                            madt = docdulieu.getString(1);
                        }
                        else{
                            continue;
                        }
                        docdulieu.close();

                        if (!madt.equals("")){
                            lenh = "Delete from tt_thuoc Where Madt = " + madt;
                            thaotac = ketnoi.createStatement();
                            thaotac.executeUpdate(lenh);
                        }

                        lenh = "Delete from tt_dt Where Maba = " + maba.get(i);
                        thaotac = ketnoi.createStatement();
                        thaotac.executeUpdate(lenh);
                    }
                }

                lenh = "Delete from tt_ba Where Mabn = " + Mabn;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);

                lenh = "Delete from tt_bn Where Mabn = " + Mabn;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);

                lenh  ="Delete from account Where user = " + Mabn;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);  
            }  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
