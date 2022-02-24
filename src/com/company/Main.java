package com.company;
import controller.Controller;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Controller usuario1 = new Controller();
       //  usuario1.Create();
        //usuario1.Update();
        //usuario1.Delete();
         //usuario1.GetAll();
        usuario1.GetAllByEmailOrName();
    }
}
