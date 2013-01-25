<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modification d'un stage </title>
<script  src="javascript/ajoutStage.js" type="text/javascript"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
<link href="css/style.css"	title="D�faut" rel="stylesheet" type="text/css" media="screen" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
 <script>
$(function() {
$( "#datedebut" ).datepicker();
$( "#datedebut" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
$("#datedebut").datepicker('setDate', "${stage.datedebut}");
$( "#datefin" ).datepicker();
$( "#datefin" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
$("#datefin").datepicker('setDate', "${stage.datefin}");
});
</script>
</head>
<body onLoad="Chargement();">
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
         <div id="right">	
	  
           <div class="haut_contenu"><img src="images/puce.png" style="margin:0 5px" alt="" />Modification d'un stage</div>
		        <div class="fond_contenu">
                <br>
<input type ="hidden" name="uneErreur" value="${MesErreurs}" id ="id_erreur" >
<form method="post" action="Controleur?action=editerStage" onsubmit="return verif();">
<input type="hidden" name="type" value="ajout" id="type"/>
<input type="hidden" name="action" value="sauverStage" />
<input type="hidden" name="identifiant" value="${stage.id}" />
<table>
<!-- input fields -->
<tr>
<td>Num�ro</td>
<td><input type="text" name="id" value="${stage.id}" id="id" disabled=true/></td>
</tr>
<tr>
<td>Libell�</td>
<td><input type="text" name="libelle" value="${stage.libelle}" id="libelle"/></td>
</tr>
<tr>
<td>Date de d�but du stage</td>
<td><input type="text" id="datedebut" name="datedebut" value="${stage.datedebut}" /></td>
</tr>
<tr>
<td>Date de fin de stage</td>
<td><input type="text" id="datefin" name="datefin" value="${stage.datefin}" /></td>
</tr>
<tr>
<td>Nombre de places </td>
<td><input type="text" name="nbplaces" value="${stage.nbplaces}" id="nbplaces"/></td>
</tr>
<tr>
<td>Nombre d'inscrits </td>
<td><input type="text" name="nbinscrits" value="${stage.nbinscrits}" id="nbinscrits"/></td>
</tr>
<!-- Boutons Ajouter/Reset -->
<tr>
<td colspan="2">
<input type="submit" name="modifier" value="Modifier"/>
&nbsp;&nbsp;
<input type="submit" name="supprimer" value="Supprimer" />
</td>
</tr>
</table>
</form>
		        </div>
		   <div class="bas_contenu"></div>  		   
      </div>
<!--******* COLONNE DROITE *******-->	
	 <div style="clear:both"></div></div>						  
     <!--******* DIV ENTOURANT COLONNE GAUCHE ET DROITE *******-->		
     <!--******* Pied *******-->
	 <div id="pied"></div>
     <!--******* Pied *******-->
<!--******* mention de copyright Ne pas retirer sans autorisation �crite *******-->
<div class="copyright"><a href=""> Votresite.com</a> 2009 | �Design <a href="http://www.kitgraphiquegratuit.org" onclick="window.open(this.href); return false;" title="kits Graphiques" >Kits Graphiques</a></div>
<!--******* mention de copyright Ne pas retirer sans autorisation �crite *******-->
</div>
</body>
</html>