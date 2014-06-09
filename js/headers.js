
window.onload = function(){

	var headers = document.querySelectorAll('.container h1, .container h2');

	for(var i = 0; i < headers.length; i++){
	  var currentHeader = headers[i];
	  var link = document.createElement("a");  
	  link.href = '#' + currentHeader.id;
	  currentHeader.parentNode.insertBefore(link,currentHeader);
	  link.appendChild(currentHeader);
	};

};