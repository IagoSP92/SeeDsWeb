$(document).ready();

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