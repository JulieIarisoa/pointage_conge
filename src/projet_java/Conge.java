/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_java;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Juju
 */
public class Conge {
    public Connection Ajouter_c(Integer num_employe, String motif,Date date_d,Date date_r,Integer nb_absence)
            throws SQLException
    {
        Connection conn3 = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn3 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        
        PreparedStatement add_conge ;
        try
        {
            add_conge = conn3.prepareStatement("INSERT INTO conge (num_employe,motif,date_demande,date_retour,nb_absence) VALUES(?,?,?,?,?)");
            add_conge.setInt(1, num_employe);
            add_conge.setString(2, motif);
            add_conge.setDate(3, date_d);
            add_conge.setDate(4, date_r);
            add_conge.setInt(5, nb_absence);
            add_conge.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return conn3;
    }
    
    
    
    
    
    public Connection Modifier(Integer num_conge,Integer num_employe, String motif, Integer nb_jour,Date date_demande, Date date_retour)
    {
        Connection conn7 = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn7 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        System.out.println("database open succesfully 7");
        PreparedStatement modif_conge;
        try
        {
            modif_conge = conn7.prepareStatement("UPDATE conge SET num_employe=?,motif=?,nb_absence=?,date_demande=?,date_retour=? WHERE num_conge=?");
            modif_conge.setInt(1, num_employe);
            modif_conge.setString(2, motif);
            modif_conge.setInt(3, nb_jour);
            modif_conge.setDate(4, date_demande);
            modif_conge.setDate(5, date_retour);
            modif_conge.setInt(6, num_conge);
            modif_conge.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    
    public Connection Supprimer(Integer num_conge)
    {
        Connection conn8 = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn8 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        PreparedStatement suppr_conge ;
        try
        {
            suppr_conge = conn8.prepareStatement("DELETE FROM conge WHERE num_conge=?");
            suppr_conge.setInt(1, num_conge);
            
            suppr_conge.executeUpdate();
        }
        catch(Exception e)
        {
        }
        return null;
    }
    
    private int nombredejourrestant(String num_emp)
    {
        int nb_conge=0;
        try{
            Connection con = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        
        ResultSet res;
        Statement stat = con.createStatement();
        res = stat.executeQuery("SELECT * FROM conge WHERE num_employe ='"+num_emp+"'");
        int nombre;
        while(res.next())
        {
            nombre = res.getInt(4);
            nb_conge+=nombre;
        }
        }
        catch(SQLException e){}
        return nb_conge;
    }
}
