

function init() {
	var btnResize = document.getElementById("btn-resize");
	var btnMove = document.getElementById("btn-move");
	var btnopenerClose = document.getElementById("btn-opener-close");
	
	btnResize.onclick = btnResizeClick;
	btnMove.onclick = btnMoveClick;
	btnopenerClose.onclick = btnOpenerClose;
}

function btnResizeClick() {
	opener.resizeTo(100,100);
}

function btnMoveClick() {

}

function btnOpenerClose() {
	opener.close();
}

window.onload = init;