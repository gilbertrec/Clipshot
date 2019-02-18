/**
 * ajax function for purchase photo @author Gilbert Recupito
 */

function acquisto(s,s1,i){
 				var xhttp =new XMLHttpRequest();
 				xhttp.onreadystatechange = function (){
 	 				if(this.readyState == 4 && this.status == 200){
 	 					//document.getElementById("test-popup2").innerHTML="<p> Foto Acquistata con Successo!</p>"
 	 					document.getElementsByName('acquisto'+i)[0].innerHTML="<p> Foto Acquistata con Successo!</p>"
 	 				}
 	 			}
 				
 				var idUtente =s;
 				var idFoto =s1;
 				xhttp.open("GET","AcquistaFoto?idUtente="+idUtente+"&idFoto="+idFoto,true);
	 			xhttp.send();
 			}