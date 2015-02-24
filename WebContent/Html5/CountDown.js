var count = 60;
var timer = null;
function CountDown() {
	var lblCount = document.getElementById("lbl-count");
	lblCount.innerText = --count;
	if (count > 0)
		setTimeout(CountDown, 1000);
}

function init() {
	var btncnt = document.getElementById("btn-countdown");
	btncnt.onclick = btnCountDownClick;
}

function btnCountDownClick() {
	if (timer == null)
		timer = setTimeout(CountDown, 1000);
}

window.onload = init;
