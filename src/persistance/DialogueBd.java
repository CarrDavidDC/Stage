package persistance;
import java.sql.*;
import java.util.*;


import meserreurs.MonException;


public class DialogueBd {
	
	private static Connexion uneconnexion = null;
	private static DialogueBd instance =null;

	public static DialogueBd getInstance()
    {
        if (instance ==null)
        {
            instance = new DialogueBd();
            uneconnexion = Connexion.getInstance();  
        }

        return instance;

    }
	 
	public DialogueBd() {
		super();
		// TODO Auto-generated constructor stub
		
		
	}

	public  void insertionBD (String mysql)   throws MonException
	{   
		Connection cnx=null;
		try 
	      { 
			uneconnexion.setConnexion();
			cnx = uneconnexion.getConnexion();
			
		    Statement unstatement = cnx.createStatement();
	        unstatement.execute(mysql);
	        System.out.println("Je suis passé");
	        // on ferme la connexion
	        cnx.close();
	      }
	       catch(SQLException e)	       
	      {
	    	   throw new MonException(e.getMessage());
	       }       
	} 

	
 public  List<Object> lecture(String req )  throws  MonException   
	  {    
		 Connection cnx = null;
		  Statement stmt;
	      ResultSet  rs;
	      List<Object> mesRes = new ArrayList<Object>();
	      int i;
	      int nbCols;
	       
	            try
	            {
	            	uneconnexion.setConnexion();
	            	cnx = uneconnexion.getConnexion();
	            	 stmt = cnx.createStatement(); 
	                 // Execution de la requete 
	                rs= stmt.executeQuery(req);
	                // on retrouve le nombre de colonnes de la requête 
	                ResultSetMetaData rsmd = rs.getMetaData();
	                nbCols = rsmd.getColumnCount();
	                i=1;
	                // on balaie toutes les lignes
	               while ( rs.next())
	               {  
	                   // On balaie les colonnes
	                   i=1;
	                   List<Object> unObjet = new ArrayList<Object>();
	                    while (i <= nbCols)
	                     {
	                    	 unObjet.add(rs.getObject(i));	                         
	                         i++;
	                     }
	                    mesRes.add(unObjet);
	               }
	                cnx.close();
	                // Retourner la table
	                return (mesRes);
	            }
	           catch (SQLException e)
	            {
	        	   System.out.println(e.getMessage());
	        	   throw new MonException(e.getMessage());
	            }
	            finally
	            {
	                // S'il y a eu un problème, la connexion
	                // peut être encore ouverte, dans ce cas
	                // il faut la fermer.                 
	               
	                if (cnx != null)
	                  try { 
	                     cnx.close();
	                    }
	                   catch (SQLException e)
	                     {
	                	   throw new MonException(e.getMessage());
	                     }
	             }
	  }
	  
 
 public void suppression(String req ) throws MonException
	{
	 Connection cnx=null;
		try
		{
			uneconnexion.setConnexion();
			cnx = uneconnexion.getConnexion();
			Statement unstatement = cnx.createStatement();
			int i = unstatement.executeUpdate(req);
			System.out.println("Je suis passé");
			if (i == 0)
				throw new MonException("Ligne déjà supprimée");
			cnx.close();

		} catch (SQLException e)
		{
			throw new MonException(e.getMessage());
		}
	}

 public  void maj_Donnees(String req) throws MonException
 {
  Connection cnx = null;
  try
    {   
	  	uneconnexion.setConnexion();
		cnx = uneconnexion.getConnexion();
		Statement stmt = cnx.createStatement();        
         // Execution de la requete 
         stmt.executeUpdate(req); 
         cnx.commit();
         stmt.close(); 
         cnx.close();
         }
         catch (SQLException e)
         {
        	 throw new MonException(e.getMessage());
         }
 } 
}
