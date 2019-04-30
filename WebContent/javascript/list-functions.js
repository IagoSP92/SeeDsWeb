$(document).ready(function(){
	for(var i=0; i< lista_lista.length(); i++){
		alert("${lista_lista['i']");
	}
	
	
});

function MyMoveItem()
{
    var selected = $('.possible option:selected');
    selected.appendTo('.wishlist');
}


function RemoveItem()
{
    var selected = $('.wishlist option:selected');
    selected.appendTo('.possible');
}

function AlreadyInItem(item){
    var selected = $(item);
    selected.appendTo('.wishlist');
}