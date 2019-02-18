	/*
	 *  ajax function for like action @author Gilbert Recupito
	 */
 			function like(s,s1,s2,i){
 				var xhttp =new XMLHttpRequest();
 				xhttp.onreadystatechange = function (){
 	 				if(this.readyState == 4 && this.status == 200){
 	 					document.getElementById("like"+i).src="png/heart.png";
 	 				}
 	 			}
 				
 				var idUtente =s;
 				var idPost =s1;
 				var idUtentePost =s2;
 				xhttp.open("GET","AggiungiLike?idUtente="+idUtente+"&idPost="+idPost+"&idUtentePost="+idUtentePost,true);
	 			xhttp.send();
 			}