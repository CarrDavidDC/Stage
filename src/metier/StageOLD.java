package metier;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import meserreurs.MonException;

import persistance.DialogueBd;

public class StageOLD {
	
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
		
	public StageOLD(String id, String libelle, Date datedebut, Date datefin,
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
	
	public StageOLD() {
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
		
	public List<StageOLD> rechercheLesStages( ) throws MonException, ParseException
		{			
			String req = "Select * from stages;";
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			try {
				List<Object> listeStage = new ArrayList<Object>();
				listeStage = unDialogueBd.lecture(req);
				List<StageOLD> laListeStage = new ArrayList<StageOLD>();
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date dateDebutT = null;
				for(int i = 0; i < listeStage.size();i++)
				{
					List l = (List) listeStage.get(i);
					Date dt = (java.sql.Date)l.get(2);
					String dd= formatter.format(dt);
					System.out.println("dd " + dd);
					Date d = formatter.parse(dd);
					System.out.println("d " + d);
					Date e = isDateValid(dd);
					StageOLD s = new StageOLD(l.get(0).toString(),l.get(1).toString(),d,e,Integer.parseInt(l.get(4).toString()),Integer.parseInt(l.get(5).toString()));
					laListeStage.add(s);
				}
				return laListeStage;
			} catch (MonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new MonException(e.getMessage());
			}
			
		}
	
    public Date isDateValid(String jDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy" );
        Date d = dateFormat.parse(jDate);
		return d;
	}
	
	public void chercherUnStage(int identifiant) throws MonException, NumberFormatException, ParseException
	{
		String req = "Select * from stages where id = " + identifiant + ";";
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			List<Object> listeStage = new ArrayList<Object>();
			listeStage = unDialogueBd.lecture(req);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("listeStage.size() " + listeStage.size());
			List l = (List) listeStage.get(0);
			this.setId(l.get(0).toString());
			this.setLibelle(l.get(1).toString());
			String da = formatter.format(l.get(2));
			this.datedebut = (java.sql.Date)(l.get(2));
			String dd= formatter.format(this.datedebut);
			System.out.println("dd " + dd);
			this.datedebut = formatter.parse(dd);
			this.setDatefin(formatter.parse(l.get(3).toString()));
			this.setNbinscrits(Integer.parseInt(l.get(4).toString()));
			this.setNbplaces(Integer.parseInt(l.get(5).toString()));
			
		} catch (MonException e) {
			// TODO Auto-generated catch block
			throw new MonException(e.getMessage());
		}
	}
}
