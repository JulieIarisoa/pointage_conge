/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Juju
 */
public class Employe {
    public Connection Ajouter_e(String nom, String prenom, String poste, Integer salaire) 
            throws SQLException
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        PreparedStatement add_employe ;
        try
        {
           add_employe = conn.prepareStatement("INSERT INTO employe(nom,prenom,poste,salaire) VALUES(?,?,?,?)");
           add_employe.setString(1, nom);
           add_employe.setString(2, prenom);
           add_employe.setString(3, poste);
           add_employe.setInt(4, salaire);
           
           add_employe.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return conn;
    }
    
    
    
    
    public Connection Modifier(Integer num_employe, String nom, String prenom, String poste, Integer salaire)
    {
        Connection conn1 = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        PreparedStatement modif_employe ;
        try
        {
           modif_employe = conn1.prepareStatement("UPDATE employe SET nom=?, prenom=?, poste=?, salaire=? WHERE num_employe=?");
           modif_employe.setString(1, nom);
           modif_employe.setString(2, prenom);
           modif_employe.setString(3, poste);
           modif_employe.setInt(4, salaire);
           modif_employe.setInt(5, num_employe);
           
           modif_employe.executeUpdate();
        }
        catch(Exception e)
        {}
        return null;
    }
    
    
    public Connection Supprimer(Integer num_employe)
    {
        Connection conn2 = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        PreparedStatement suppr_employe ;
        try
        {
           suppr_employe = conn2.prepareStatement("DELETE FROM employe WHERE num_employe = ?");
           suppr_employe.setInt(1, num_employe);
           
           suppr_employe.executeUpdate();
        }
        catch(Exception e)
        {}
        return null;
    }
    /*public Connection Modifier_e(Integer num_employe)
    {
        Connection conn1 = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        PreparedStatement modif_employe ;
        try
        {
           modif_employe = conn1.prepareStatement("UPDATE employe SET nb_a = nb_a+1 WHERE num_employe=?");
           modif_employe.setInt(1, num_employe);
           
           modif_employe.executeUpdate();
        }
        catch(Exception e)
        {}
        return null;
    }
    public Connection Modifier_p(Integer num_employe)
    {
        Connection conn1 = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projet","postgres","041004");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        PreparedStatement modif_employe ;
        try
        {
           modif_employe = conn1.prepareStatement("UPDATE employe SET nb_a = nb_a-1 WHERE num_employe=?");
           modif_employe.setInt(1, num_employe);
           
           modif_employe.executeUpdate();
        }
        catch(Exception e)
        {}
        return null;
    }*/
}
