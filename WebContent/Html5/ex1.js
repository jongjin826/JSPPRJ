/*
 function printResult() {
 var x,y;
 x = prompt("x 값을 입력하세요",0);
 y = prompt("y 값을 입력하세요",0);
 x = parseInt(x);
 y = parseInt(y);
 var btnPrint = document.getElementById("btn-print");
 btnPrint.value=x+y; 
 }
 
function init() {
	var btnPrint = document.getElementById("btn-print");
	btnPrint.onclick = printResult;
}
window.onload = init;
*/
function init() {
	var btnSum = document.getElementById("btn-sum");
	btnSum.onclick = btnSumClick;
}

function btnSumClick() {
	var txtX = document.getElementById("txt-x");
	var txtY = document.getElementById("txt-y");
	var txtSum = document.getElementById("txt-sum");
	
	txtSum.value = parseInt(txtX.value) + parseInt(txtY.value);
}

window.onload = init;
