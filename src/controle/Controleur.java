package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.Connexion;
import persistance.DialogueBd;

import meserreurs.MonException;
import metier.StageOLD;
import metier.Stage;


/**
 * Servlet implementation class Controleur
 */
@WebServlet("/Controleur")
public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	private static final String SAISIE_STAGE = "saisieStage";
	private static final String AFFICHER_STAGE = "afficheStage";
	private static final String RECHERCHER_STAGE = "rechercheStage";
	private static final String CHERCHER_STAGE = "chercheStage";
	private static final String AJOUT_STAGE = "AjoutStage";
	private static final String MODIFIER_STAGE = "modifierStage";
	private static final String EDITER_STAGE = "editerStage";
	private static final String ERROR_PAGE = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controleur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			processusTraiteRequete(request, response);
		}
		catch( Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			processusTraiteRequete(request, response);
		}
		catch( Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	protected void processusTraiteRequete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, Exception,ParseException
	{ 
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		// execute l'action
		if(SAISIE_STAGE.equals(actionName))
		{
			Connexion c = Connexion.getInstance();
			c.setConnexion();
			destinationPage = "/SaisieStage.jsp";
		}
		else
		if(AFFICHER_STAGE.equals(actionName))
		{
			destinationPage = "/AfficherStage.jsp";
			List<Stage> stages;
			Stage st = new Stage();
			stages = st.rechercheLesStages();
			request.setAttribute("stages", stages);
		}
		else if(RECHERCHER_STAGE.equals(actionName))
		{
			destinationPage = "/RechercherStage.jsp";
			if(request.getParameter("champsRecherche") != null)
			{
				// on fait une recherche
				System.out.println("Recherche");
				String libel;
				Date dateDebut, dateFin;
				if(request.getParameter("rechercheLibelle") != "")
					libel = request.getParameter("rechercheLibelle").toString();	
				else
					libel = "";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(request.getParameter("rechercheDateDeb"));
				if(request.getParameter("rechercheDateDeb") != "")
				{
					String s1 = request.getParameter("rechercheDateDeb");	
					dateDebut = sdf.parse(s1);
				}
				else
					dateDebut = null;
				if(request.getParameter("rechercheDateFin") != "")
				{
					System.out.println("ici2");
					String s1 = request.getParameter("rechercheDateFin");	
					dateFin = sdf.parse(s1);
				}
				else
					dateFin = null;		
								
			//	int nbPlaces = Integer.parseInt(request.getParameter("rechercheNbPlaces").toString());
			//	int nbInscrits = Integer.parseInt(request.getParameter("rechercheNbInscrits").toString());
				
			}
		}
		else if(MODIFIER_STAGE.equals(actionName))
		{
			Stage stage = new Stage();
			destinationPage = "/modifierStage.jsp";
			int idStage = Integer.parseInt(request.getParameter("id").toString());
			stage.chercherUnStage(idStage);
			request.setAttribute("stage", stage);
		}
		else if(EDITER_STAGE.equals(actionName))
		{
			StageOLD stage = new StageOLD();
			destinationPage = "/AfficherStage.jsp";
			String lactionFormModif = request.getParameter("modifier");
			System.out.println(lactionFormModif);
			String lactionFormSupp = request.getParameter("supprimer");
			System.out.println(lactionFormSupp);
			if(lactionFormModif != null && lactionFormModif.equals("Modifier")){
				methodeModifierStage(request,response);
			}
			else
			{
				if(lactionFormSupp.equals("Supprimer")){
					Stage s = new Stage();
					s.setId(request.getParameter("identifiant"));
					s.supprimerStage();
				}
			}
		}
		else if(AJOUT_STAGE.equals(actionName))
		{			
			if(methodeAjoutStage(request,response) == 1)
				destinationPage = "/AjoutStage.jsp";
			else
				destinationPage = "/Erreur.jsp";
		}
		// Redirection vers la page jsp appropriee
		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher(destinationPage);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Methode pour ajouter un Stage à la base de données
	 * @param request
	 * @param response
	 * @throws ParseException 
	 */
	private int methodeAjoutStage(HttpServletRequest request,
			HttpServletResponse response) throws ParseException
	{
		String s1 = request.getParameter("datedebut");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = sdf.parse(s1);
		String s2 = request.getParameter("datefin");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		Date dateFin = sdf2.parse(s2);
		
		Stage st = new Stage(request.getParameter("id"),
				request.getParameter("libelle"),
				dateDebut,
				dateFin,
				Integer.parseInt(request.getParameter("nbplaces")),
				Integer.parseInt(request.getParameter("nbinscrits")));
		try {
			st.insertionStage();
			return 1;
		} catch (MonException e) {
			request.setAttribute("monexception", e);
			return 0;
		}
	}	
	
	private int methodeModifierStage(HttpServletRequest request,
			HttpServletResponse response) throws ParseException
	{
		String s1 = request.getParameter("datedebut");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = sdf.parse(s1);
		String s2 = request.getParameter("datefin");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFin = sdf2.parse(s2);
		Stage st = new Stage(request.getParameter("identifiant"),
				request.getParameter("libelle"),
				dateDebut,
				dateFin,
				Integer.parseInt(request.getParameter("nbplaces")),
				Integer.parseInt(request.getParameter("nbinscrits")));
		try {
			st.modifierStage();
			return 1;
		} catch (MonException e) {
			request.setAttribute("monexception", e);
			return 0;
		}
	}	
}
