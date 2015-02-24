function init() {
	var textbtn = document.getElementById("textbtn");
	var imgbtn = document.getElementById("imgbtn");
	var removebtn = document.getElementById("removebtn");
	var clonebtn = document.getElementById("clonebtn");
	var changebtn = document.getElementById("changebtn");

	textbtn.onclick = textClick;
	imgbtn.onclick = imgClick;
	removebtn.onclick = removeClick;
	clonebtn.onclick = cloneClick;
	changebtn.onclick = changeClick;
}
function textClick() {
	var append = document.createTextNode(":)");
	var div1 = document.getElementById("div1");
	div1.appendChild(append);
}
function imgClick() {
	var img = document.createElement("img");
	var div1 = document.getElementById("div1");
	img.src = "1.jpg";

	div1.appendChild(img);
}
function removeClick() {
	var div1 = document.getElementById("div1");
	var last = div1.lastChild;

	div1.removeChild(last);
}
function cloneClick() {
	var div1 = document.getElementById("div1");
	var clone = div1.cloneNode(true);
	var target = document.body;
	target.appendChild(clone);
}

function changeClick() {
	var div1 = document.getElementById("div1");
}
window.onload = init;