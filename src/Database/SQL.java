package Database;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author nafi
 */
import java.awt.Font;
import java.awt.Label;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.*;
import javafx.fxml.FXML;


public class SQL {

    @FXML
    private static Label spLBL;


    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */

    private    Connection con;
    private ResultSet resultSet;
    private  Statement  statement;
    PreparedStatement preparedStatement;


    public boolean tryLogin(String username, String password) throws Exception
    {
        try {
            con= DBConnection.ConnectDB();

            preparedStatement = con.prepareStatement("SELECT * FROM AdminInfo WHERE UserId = ? and Password= ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet=preparedStatement.executeQuery();
            con.close();
            if(resultSet.next()){
                return  true;
            }
            return false;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  false;
    }
    public ResultSet Query(String q) throws Exception
    {
        con = DBConnection.ConnectDB();
        con.createStatement();    // newConnection can createstament.
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(q);

        return  resultSet;

    }

}
