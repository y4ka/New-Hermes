var bombPlanted = false;
var bombExplosed = false;
var pylonAmorced = '';
var terroristWin = false;
var counterTerroristWin = false;
var bombTimer = 15;
var ROUND_DURATION = 30;
 
function inputButton(idPylon, colorButton)
{
	//Si la bombe n'est pas encore posée, appuyer sur le bouton la pose:
    if (bombPlanted == false)
  	{
		bombPlanted = true;
		pylonAmorced = idPylon;
		
		return "Bombe posée sur le Pylone "+idPylon;
	}
 
  	//Si la bombe est déja posée, appuyer sur le bouton la desamorce:
  	else
  	{
		//On vérifie qu'on defuse le bon pylone (celui qui a la bombe):
		if (idPylon == pylonAmorced)
		{
			//Bombe désamorcée:
			counterTerroristWin = true;
			
			return "Bombe désamorcée, CT WIN";
    	}
    	else
    	{
    		return "On tente de désamorcer le mauvais Pylone";
    	}
  	}
}

function inputTarget(idPylon)
{
	return "Unused function";
}

function inputKeyboard(idPylon, keyboardString)
{
	return "Unused function - "+keyboardString;
}
 
function tick(nbTick)
{
	//Si la bombe a été posée:
	if (bombPlanted == true)
  	{
		//On diminue le timer de la bombe:
		bombTimer--;
   
    	//Si la bombe arrive a 0, elle explose, les Ts ont gagné:
		if(bombTimer <= 0)
    	{
			bombExploded = true;
      		terroristWin = true;
      		
      		return "BOOM, EXPLOSION";
    	}
    	else
    	{
    		return "Bombe explose dans "+bombTimer+" secondes";
    	}
  	}
 
	//Si on atteint la fin du temps reglementaire, les CTs ont gagné:
	if(nbTick >= ROUND_DURATION)
  	{
		counterTerroristWin = true;
		return "Temps ecoule !";
  	}
  	else
  	{
  		var remainingTime = ROUND_DURATION - nbTick;
  		return "Temps restant: "+remainingTime;
  	}
  	
  	return endGame;
}
 
function checkVictory()
{
	if(terroristWin == true || counterTerroristWin == true)
  	{
  		//TEST LINKS
		messageSender.allumerSirene(1,2,3,4);
		messageSender.allumerLED(1, "RED");
		//END TEST LINKS
	
		return true;
  	}
  	else
 	{
		return false;
  	}
}