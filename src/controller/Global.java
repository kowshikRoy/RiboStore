package controller;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by matrixcode on 12/8/16.
 */
public class Global {
    public static int user=-1;
    public static long customerId;
    public static Connection con ;
    public static Statement statement;
    public static int getUser(){
        return user;
    }
    public static void setUser(int u) { user = u;  }

    public Global() {
        user = -1;
        customerId = -1L;
    }

    public static void setCustomerId(long customerId) {

        Global.customerId = customerId;
    }

    public static long getCustomerId() {

        return customerId;
//        return 999;
    }
}
