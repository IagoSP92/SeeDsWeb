

$("#incluirButton").ready( function ()
	{
		
        var selected = $('.wishlist option').val();
        
        alert($('.wishlist').size());
        selected.appendTo('.wishlist');/*
        $(".actuallyIn").attr("data-item").appendTo('.wishlist');
        MyMoveItem(selected);*/
	}
	
);

function MyMoveItem2()
{
    $('.wishlist option').val().appendTo('.wishlist');
}

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
