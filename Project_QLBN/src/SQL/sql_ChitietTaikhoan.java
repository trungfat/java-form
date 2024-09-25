/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import QlyBenhnhan.ChitietTaikhoan;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
public class sql_ChitietTaikhoan {
    Connection ketnoi;
    Statement thaotac;
    ResultSet docdulieu;
    
    public ArrayList hienthi(String Pq, String Ma) throws SQLException{
        ketnoi = KetNoi.KNCSDL();
        ArrayList<String> chitiet = new ArrayList<>();
        String lenh = "";
        if(Pq.equals("BN")){
            lenh = "Select HoTen, Cccd, Sdt, Email from tt_bn Where Mabn = " + Ma;      
        }
        else{
            lenh = "Select HoTen, Cccd, Sdt, Email from tt_bs Where Mabs = " + Ma;
        }
        thaotac = ketnoi.createStatement();
        docdulieu = thaotac.executeQuery(lenh);
        if (docdulieu.next()) {
            chitiet.add(docdulieu.getString(1));
            chitiet.add(docdulieu.getString(2));
            chitiet.add(docdulieu.getString(3));
            chitiet.add(docdulieu.getString(4));
        }
        docdulieu.close();
                
        lenh = "Select password from account Where user = " + Ma;
        thaotac = ketnoi.createStatement();
        docdulieu = thaotac.executeQuery(lenh);
        if (docdulieu.next()) {
            chitiet.add(docdulieu.getString(1));
        }
        docdulieu.close();
        return chitiet;
    }
}
