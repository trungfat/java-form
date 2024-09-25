/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QlyBenhnhan;

import java.sql.SQLException;
import javax.swing.*;
import javax.swing.UIManager;

/**
 *
 * @author duong
 */
public class main {
    public static void main(String[] args) throws SQLException {
        try {
            // Set the Nimbus look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Login().setVisible(true);
    }
}
