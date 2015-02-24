function init() {
	var btnInnerOpen = document.getElementById("btn-inner-open");
	
	btnInnerOpen.onclick = openDoc;
}

function openDoc() {
	window.frames[0].location.href = "http://www.newlecture.com";
}
window.onload = init;
