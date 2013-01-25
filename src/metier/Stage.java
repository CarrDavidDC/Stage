package metier;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import meserreurs.MonException;
import persistance.DialogueBd;

public class Stage {
	private String id;
	private String libelle;
	private Date datedebut;
	private Date datefin;
	private int nbplaces;
	private int nbinscrits;
	
	public int getNbplaces() {
		return nbplaces;
	}
	
	public void setNbplaces(int nbplaces) {
		this.nbplaces = nbplaces;
	}
	
	public int getNbinscrits() {
		return nbinscrits;
	}
	
	public void setNbinscrits(int nbinscrits) {
		this.nbinscrits = nbinscrits;
	}
		
	public Stage(String id, String libelle, Date datedebut, Date datefin,
		int nbplaces, int nbinscrits)
	{
		this.id = id;
		this.libelle = libelle;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.nbplaces = nbplaces;
		this.nbinscrits = nbinscrits;
	}
	
	public String getId() {
		return id;
	}
	
	public Stage() {
	// TODO Auto-generated constructor stub
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public Date getDatedebut() {
		return datedebut;
	}
	
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	
	public Date getDatefin() {
		return datefin;
	}
	
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	
	public List<Stage> rechercheLesStages( ) throws MonException, ParseException
	{			
		String req = "Select * from stages;";
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			List<Object> listeStage = new ArrayList<Object>();
			listeStage = unDialogueBd.lecture(req);
			List<Stage> laListeStage = new ArrayList<Stage>();
			//DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			for(int i = 0; i < listeStage.size();i++)
			{
				List<?> l = (List<?>) listeStage.get(i);
				Date dateDebut = (java.sql.Date)l.get(2);
				Date dateFin = (java.sql.Date)l.get(3);
				Stage s = new Stage(l.get(0).toString(),l.get(1).toString(),dateDebut,dateFin,Integer.parseInt(l.get(4).toString()),Integer.parseInt(l.get(5).toString()));
				laListeStage.add(s);
			}
			return laListeStage;
		} catch (MonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MonException(e.getMessage());
		}
		
	}
	
	public void chercherUnStage(int identifiant) throws MonException, NumberFormatException, ParseException
	{
		String req = "Select * from stages where id = " + identifiant + ";";
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			List<Object> listeStage = new ArrayList<Object>();
			listeStage = unDialogueBd.lecture(req);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			List<?> l = (List<?>) listeStage.get(0);
			this.setId(l.get(0).toString());
			this.setLibelle(l.get(1).toString());
			Date dateDebut = (java.sql.Date)l.get(2);
			Date dateFin = (java.sql.Date)l.get(3);
			this.setDatedebut(dateDebut);
			this.setDatefin(dateFin);
			this.setNbinscrits(Integer.parseInt(l.get(4).toString()));
			this.setNbplaces(Integer.parseInt(l.get(5).toString()));
			
		} catch (MonException e) {
			// TODO Auto-generated catch block
			throw new MonException(e.getMessage());
		}
	}
	
	/* traitements métier */
	public void insertionStage ( ) throws MonException
	{
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try
		{
			String mysql="";
			DateFormat dateFormatpers = new SimpleDateFormat("yyyy-MM-dd");
			String dd = dateFormatpers.format(this.getDatedebut());
			String df = dateFormatpers.format(this.getDatefin());
			mysql = "INSERT INTO stages (id, libelle, datedebut ,";
			mysql = mysql + " datefin, nbplaces, nbinscrits) ";
			mysql = mysql + " VALUES ( \'" + this.getId() + "\', \'" + this.getLibelle() + "\', ";
			mysql = mysql + "\'" + dd + "\', " + "\'" + df + "\', ";
			mysql = mysql + this.getNbplaces() + ", " + this.getNbinscrits() + " )";
			unDialogueBd.insertionBD(mysql);
		}
		catch(MonException e)
		{ 
			throw e;
		}
	}
	
	public void modifierStage ( ) throws MonException
	{
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try
		{
			String mysql="";
			DateFormat dateFormatpers = new SimpleDateFormat("yyyy-MM-dd");
			String dd = dateFormatpers.format(this.getDatedebut());
			String df = dateFormatpers.format(this.getDatefin());
			mysql = "UPDATE stages set ";
			mysql = mysql + " libelle = \'" + this.getLibelle() + "\', ";
			mysql = mysql + " datedebut = \'" + dd + "\', " + " datefin = \'" + df + "\', ";
			mysql = mysql + " nbplaces = "+this.getNbplaces() + ", nbinscrits = " + this.getNbinscrits() + " where id = "+ this.getId() + ";";
			System.out.println(mysql);
			unDialogueBd.maj_Donnees(mysql);
		}
		catch(MonException e)
		{ 
			throw e;
		}
	}
	
	public void supprimerStage(){
		DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql="delete from stages where id = "+this.getId()+";";
			System.out.println(mysql);
			try {
				unDialogueBd.suppression(mysql);
			} catch (MonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
