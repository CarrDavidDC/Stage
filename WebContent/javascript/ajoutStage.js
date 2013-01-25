function verif()
{
	// verifications des champs
	if(document.getElementById("id").value == "" ||
				document.getElementById("libelle").value == "" ||
				document.getElementById("datedebut").value == "" ||
				document.getElementById("datefin").value == "" || 
				document.getElementById("nbplaces").value == "" || 
				document.getElementById("nbinscrits").value == "")
	{ 
		alert("Merci de compléter les champs en rouge. !");		
		return false;
	}
	else
	{
		// verification des places
		var nbPlace = document.getElementById("nbplaces").value;
		var nbInscrit = document.getElementById("nbinscrits").value;
		if(parseInt(nbInscrit) > parseInt(nbPlace))
		{
			alert("Le nombre d'inscrit est supérieur au nombre de place.");
			return false;			
		}
		else
		{
			// controle de date
			var dd = document.getElementById("datedebut").value;
			var ddDecoupe = dd.split("-"); 
			var dateDebut = new Date(ddDecoupe[0], ddDecoupe[1], ddDecoupe[2], 0, 0, 0, 0); 
			var df = document.getElementById("datefin").value;
			var dfDecoupe = df.split("-"); 
			var dateFin = new Date(dfDecoupe[0], dfDecoupe[1], dfDecoupe[2], 0, 0, 0, 0); 
			if(dateFin < dateDebut)
			{
				alert("La date de fin de stage est avant la date de début.")
				return false;
			}
			else
				return true;
		}
	}
}

function Chargement()
{
	var obj = document.getElementById("id_erreur");
	if (obj.value!='')
		alert('Erreur signalée : "'+obj.value+"'");
}


function verifieChampsLibre(champs, regex, obligatoire)
{
	var maRegex = new RegExp(regex);
	if((obligatoire && champs.value.length > 0 && (regex == '' || (regex != '' && maRegex.test(champs.value))))
	|| (!obligatoire && (regex == '' || (regex != '' && (champs.value.length == 0 || (champs.value.length > 0 && maRegex.test(champs.value)) )))))
	{
		champs.className = "textbox";
		return true;
	}
	else
	{
		champs.className = "obligatoire";
		alert('Le champs est mal rempli.');
		return false;
	}
}