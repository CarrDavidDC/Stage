<%@page import="java.util.Date,java.text.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
<head>
<title>Recherche Stage</title>	 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="content-language" content="fr" />
<link href="css/style.css"	title="Défaut" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>	
     <div id="conteneur">
          <!--******* HEADER *******-->
 		  <div id="header">
 		  <ul id="menuhorizon">
 		      <li><a href="index.jsp" title="Ma description">Accueil</a></li>
 		      <li><a href="Controleur?action=afficheStage" title="Ma description">Liste</a></li>
 		      <li><a href="Controleur?action=rechercheStage" title="Ma description">Recherche</a></li>
 		  </ul>
 		  </div>
          <!--******* HEADER *******-->
     <!--******* DIV ENTOURANT COLONNE GAUCHE ET DROITE *******-->	     
	<div id="contenu">
         <!--******* COLENNE GAUCHE *******-->		   
         <div id="left">

               <div class="hautmenu">Menu</div><div class="fondmenu">
                    <ul class="menu"> 
                        <li><a href="Controleur?action=saisieStage">Ajout Stage</a></li>
                        <li><a href="Controleur?action=afficheStage">Liste Stage</a></li>
                        <li><a href="Controleur?action=rechercheStage">Recherche</a></li>
                    </ul>             
			 <br />
					
               </div><div class="basmenu"></div>
         </div>
         <!--******* COLONNE GAUCHE *******-->	
		 
      <!--******* COLONNE DROITE *******-->
      <div id="right">	
	  
           <div class="haut_contenu"><img src="images/puce.png" style="margin:0 5px" alt="" />Rechercher les stages</div>
		        <div class="fond_contenu">
                <form method="post" action="Controleur?action=rechercheStage" >
                	<input type="hidden" name="champsRecherche" value="champsRecherche"/>
                	<table class="monTableau">
                		<tr class="centre">
                			<td>Libellé</td>
                			<td>Date Debut</td>
                			<td>Date Fin</td>
                			<td>Nombre<br>places</td>
                			<td>Nombre<br>inscrits</td>
                			<td></td>
                		</tr>
                		<tr class="centre">
                			<td><input type="text" name="rechercheLibelle" style="width:150px;"/></td>
                			<td><input type="text" name="rechercheDateDeb" style="width:80px;"/></td>
                			<td><input type="text" name="rechercheDateFin" style="width:80px;"/></td>
                			<td><input type="text" name="rechercheNbPlaces" style="width:50px;"/></td>
                			<td><input type="text" name="rechercheNbInscrits" style="width:50px;"/></td>
                			<td><input type="submit" name="validerRecherche" value="" style="width:30px;height:35px;background:url(images/loupe.jpg) no-repeat;" /></td>
                		</tr>
                	</table>
                </form>
                <table class="monTableau">
					<tr class="centre">
						<td>Id</td>
						<td>Libellé</td>
						<td>DateDebut</td>
						<td>DateFin</td>
						<td>Nombre<br>de places</td>
						<td>Nombre<br>d'inscrits</td>
					</tr>
					<c:forEach items="${stages}" var="stage" >
						<tr onmouseover="this.className='survol';"
					        onmouseout="this.className='parti';" 
					        onclick="window.location.href = 'Controleur?action=modifierStage&id=${stage.id}'">
								<td class="centre"><c:out value="${stage.id}" /></td>
								<td><c:out value="${stage.libelle}" /></td>
								<td class="centre"><c:out value="${stage.datedebut}" /></td>
								<td class="centre"><c:out value="${stage.datefin}" /></td>
								<td class="centre"><c:out value="${stage.nbplaces}" /></td>
								<td class="centre"><c:out value="${stage.nbinscrits}" /></td>
						</tr>
					</c:forEach>
				</table>
		        </div>
		   <div class="bas_contenu"></div>  		   
      </div>
      <!--******* COLONNE DROITE *******-->	
	 <div style="clear:both"></div></div>						  
     <!--******* DIV ENTOURANT COLONNE GAUCHE ET DROITE *******-->		
     <!--******* Pied *******-->
	 <div id="pied"></div>
     <!--******* Pied *******-->
<!--******* mention de copyright Ne pas retirer sans autorisation écrite *******-->
<div class="copyright"><a href=""> Votresite.com</a> 2009 | ©Design <a href="http://www.kitgraphiquegratuit.org" onclick="window.open(this.href); return false;" title="kits Graphiques" >Kits Graphiques</a></div>
<!--******* mention de copyright Ne pas retirer sans autorisation écrite *******-->
</div>

</body>
</html>
