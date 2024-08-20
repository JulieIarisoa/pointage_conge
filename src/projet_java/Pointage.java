/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_java;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Juju
 */
public class Pointage {
    public Connection Ajouter_p(Integer num_employe, Date date_p)
            throws SQLException
    {
        Connection conn3 = null;
        try
        {
            //manova ny driver class
            Class.forName("org.postgresql.Driver");
            
            //creer objet de connection
            conn3 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        System.out.println("database open succesfully 4");
        
        PreparedStatement add_pointage ;
        try
        {
            add_pointage = conn3.prepareStatement("UPDATE pointage SET pointage='oui' WHERE num_employe=? and date_pointage=?");
            add_pointage.setInt(1, num_employe);
            add_pointage.setDate(2, date_p);
            
            add_pointage.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("database");
        return conn3;
    }
    
    
    
    
    public Connection Modifier(Integer num_pointage,Date date_pointage, Integer num_employe, String pointage)
    {
        Connection conn4 = null;
        try
        {
            //manova ny driver class
            Class.forName("org.postgresql.Driver");
            
            //creer objet de connection
            conn4 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        System.out.println("database open succesfully 4");
        
        PreparedStatement modif_pointage ;
        try
        {
            modif_pointage = conn4.prepareStatement("UPDATE pointage SET date_pointage=?,num_employe=?,pointage=? WHERE num_pointage=?");
            modif_pointage.setDate(1, date_pointage);
            modif_pointage.setInt(2, num_employe);
            modif_pointage.setString(3, pointage);
            modif_pointage.setInt(4, num_pointage);
            
            modif_pointage.executeUpdate();
            conn4.close();
        }
        catch(Exception e)
        {
        }
        return conn4;
    }
    
    public Connection Supprimer(Integer num_pointage)
    {
        Connection conn5 = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn5 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        
        PreparedStatement suppr_pointage ;
        try
        {
            suppr_pointage = conn5.prepareStatement("DELETE FROM pointage WHERE num_pointage=?");
            suppr_pointage.setInt(1, num_pointage);
            
            suppr_pointage.executeUpdate();
            conn5.close();
        }
        catch(Exception e)
        {
        }
        return conn5;
    }
    public Connection Ajouter_p1(Integer num_employe, Date date_p)
            throws SQLException
    {
        Connection conn3 = null;
        try
        {
            //manova ny driver class
            Class.forName("org.postgresql.Driver");
            
            //creer objet de connection
            conn3 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        System.out.println("database open succesfully 4");
        
        PreparedStatement add_pointage ;
        try
        {
            add_pointage = conn3.prepareStatement("INSERT INTO pointage (date_pointage,num_employe,pointage) VALUES(?,?,'non')");
            add_pointage.setDate(1, date_p);
            add_pointage.setInt(2, num_employe);
            
            add_pointage.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("database");
        return conn3;
    }
}
