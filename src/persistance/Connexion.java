package persistance;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import meserreurs.MonException;

import java.sql.Connection;
import java.sql.SQLException;

public class Connexion {

	private Connection conn = null;
	  private static Connexion instance =null;
	
	 // On utilise un singleton  
	 public static Connexion getInstance()
     {
         if (instance ==null)
             instance = new Connexion();

         return instance;

     }
	 
	public void  setConnexion( )throws MonException ,SQLException
	  {    
		try 
	       {  
              // On lit les properties à partir 
			// de la classe abstraite 
			   Context ctxt=JBossContext.getInitialContext();	
			   if(ctxt!=null){
	           DataSource ds = (DataSource) ctxt.lookup("java:/DSMesStages");
	           this.conn= ds.getConnection();
			   }
	       
	       }
		catch( SQLException e )  {
	          throw new MonException(e.getMessage());
	       }
		catch (NamingException e) {
		 throw new MonException(e.getMessage());
            }
	     catch ( Exception e )      {
	          throw new MonException(e.getMessage());
	       }
	} 
	
	public Connection getConnexion ()
	{
		return this.conn;
	}

}
