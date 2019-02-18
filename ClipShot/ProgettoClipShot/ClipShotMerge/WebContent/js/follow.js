/**
 *  ajax function for follow action @author Gilbert Recupito
 */	
 			function follow(s,s1){
 				var xhttp =new XMLHttpRequest();
 				xhttp.onreadystatechange = function (){
 	 				if(this.readyState == 4 && this.status == 200){
 	 					document.getElementById("follow_img").src="png/heart.png";
 	 				}
 	 			}
 				
 				var idFollower =s;
 				var idFollowing =s1;
 				xhttp.open("GET","AggiungiSegui?idFollower="+idFollower+"&idFollowing="+idFollowing,true);
	 			xhttp.send();
 			}