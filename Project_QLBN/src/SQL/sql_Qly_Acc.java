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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duong
 */
public class sql_Qly_Acc {
    
    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;
    
    public sql_Qly_Acc() {
        try {
            ketnoi = KetNoi.KNCSDL();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kết nối không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<ArrayList<Object>> hienthi_Acc(){
        ArrayList<ArrayList<Object>> ht_acc = new ArrayList<>();
        try {
            String lenh = "Select user, phanquyen From account";
            thaotac = ketnoi.createStatement();
            docdulieu = thaotac.executeQuery(lenh);
            
            while (docdulieu.next()) {
                ArrayList<Object> ht_acc_row = new ArrayList<>();
                ht_acc_row.add(docdulieu.getString(1));
                ht_acc_row.add(docdulieu.getString(2));
                ht_acc.add(ht_acc_row);
            }
            docdulieu.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lấy dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return ht_acc;
    }
    
    public ArrayList<ArrayList<Object>> search_Acc(String searchacc){
        ArrayList<ArrayList<Object>> sr_acc = new ArrayList<>();
        try {
            if(!searchacc.equals("")){
                String lenh = "Select user, phanquyen From account Where "
                        + "user like '%" + searchacc + "%' or "
                        + "phanquyen like '%" + searchacc + "%'";
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);

                if (!docdulieu.next()) {
                    sr_acc = null;
                }
                else{
                    do {
                        ArrayList<Object> sr_acc_row = new ArrayList<>();
                        sr_acc_row.add(docdulieu.getString(1));
                        sr_acc_row.add(docdulieu.getString(2));
                        sr_acc.add(sr_acc_row);
                    }while (docdulieu.next());
                    docdulieu.close();   
                }
            }
            else JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Tìm kiêm dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } 
        return sr_acc;
    }
    
    public void reset_pass(String User){
        try {
            String lenh = "Update account Set password = " + User + " Where user = " + User;
            thaotac = ketnoi.createStatement();
            thaotac.executeUpdate(lenh);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void edit_acc(String User, String Pq){
        try {
            
            String lenh = "Update account Set phanquyen = '" + Pq + "' Where user = " + User;
            thaotac = ketnoi.createStatement();
            thaotac.executeUpdate(lenh);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void delete_acc(String User, String Pq){
        try {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thông tin này?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                String lenh = "";
                if (Pq.equals("BN")){
                    ArrayList<String> maba = new ArrayList<>();

                    lenh = "Select Maba from tt_ba Where Mabn = " + User;
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

                    lenh = "Delete from tt_ba Where Mabn = " + User;
                    thaotac = ketnoi.createStatement();
                    thaotac.executeUpdate(lenh);

                    lenh = "Delete from tt_bn Where Mabn = " + User;
                    thaotac = ketnoi.createStatement();
                    thaotac.executeUpdate(lenh);
                }
                else if (Pq.equals("BS")){
                    lenh = "Delete from tt_bs Where Mabs = " + User;
                    thaotac = ketnoi.createStatement();
                    thaotac.executeUpdate(lenh);
                }

                lenh  ="Delete from account Where user = " + User;
                thaotac = ketnoi.createStatement();
                thaotac.executeUpdate(lenh);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
