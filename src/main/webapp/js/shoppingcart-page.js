$(".delete").click(function(){
	$(this).closest("tr").remove();
	calculateTotal();
	var id = $(this).closest('tr').attr('id');
	var regex = new RegExp(id, 'g');
	sessionStorage.setItem("item", sessionStorage.getItem("item").replace(regex, ""));
	
});

$('.edit').click(function(){
	var subtotal = 0;
	var getPrice = 30.00;
	var amount = $(".amount",$(this).parent().parent()).val();
	var id = $(this).closest('tr').attr('id');
	subtotal = amount * getPrice;
	$(".subtotal",$(this).parent().parent()).text(subtotal);
	subtotal = 0;
	calculateTotal(); 
	var regex = new RegExp(id, 'g');
	console.log(id);
	console.log(amount);
	sessionStorage.setItem("item", sessionStorage.getItem("item").replace(regex, ""));
	var currentList = sessionStorage.getItem("item");
	$(amount).each(function(){
		$(currentList).append(id);
		console.log(currentList);
	});
	sessionStorage.setItem("item", currentList);
});

function calculateTotal(){
	var total = 0;
	$(".subtotal").each(function () {
		var stval = parseFloat($(".subtotal",$(this).parent().parent()).text());
		total += isNaN(stval) ? 0 : stval;
	});
	$('#total').text(total);
};

function calculateSubtotal() {
	var subtotal = 0;
	var getPrice = 30.00;
	var amount = $(".amount",$(".edit").parent().parent()).val();
	subtotal = amount * getPrice;
	$(".subtotal",$(".edit").parent().parent()).text(subtotal);
	subtotal = 0;
	calculateTotal();
};

function sendCart(){
	items = sessionStorage.getItem("item");
	var myObject = new Object();
	myObject.items = listitems;
	var myString = JSON.stringify(myObject);
}

function setAmount(){
	var itemList = sessionStorage.getItem("item");
	$(".amount").each(function(){
		var id = $(this).closest('tr').attr('id');
		var regex = new RegExp(id, 'g');
		var count = (itemList.match(regex)).length;
		$(".amount",$(this).parent().parent()).val(count);
	})
}










