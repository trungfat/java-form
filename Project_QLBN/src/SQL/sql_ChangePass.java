/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import QlyBenhnhan.ChangePass;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author duong
 */
public class sql_ChangePass {
    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;
    
    public boolean Save(String oldPass1, String newPassString, String confirmPass, String mabn){
        try {
            ketnoi = KetNoi.KNCSDL();
            if(!oldPass1.equals("") && !newPassString.equals("") && !confirmPass.equals("")){
                String lenh = "Select password from account Where user = " + mabn;
                String oldPass2 = "";
                thaotac = ketnoi.createStatement();
                docdulieu = thaotac.executeQuery(lenh);
                if (docdulieu.next()) {
                    oldPass2 = docdulieu.getString(1);
                }
                docdulieu.close();
                if(oldPass1.equals(oldPass2) && newPassString.equals(confirmPass)){
                    lenh = "Update account Set "
                            + "password = '" + confirmPass + "' "
                            + "Where user = " + mabn;
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu thay đổi này?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                        thaotac = ketnoi.createStatement();
                        thaotac.executeUpdate(lenh);
                        return true;
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Mật khẩu không đúng!!!", "Thông báo", JOptionPane.OK_OPTION);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!!!", "Thông báo", JOptionPane.OK_OPTION);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa dữ liệu không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
