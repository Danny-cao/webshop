$(".delete").click(function(){
	$(this).closest("tr").remove();
	calculateTotal();
});

$('.edit').click(function(){
	var subtotal = 0;
	var getPrice = 30.00;
	var amount = $(".amount",$(this).parent().parent()).val();
	subtotal = amount * getPrice;
	$(".subtotal",$(this).parent().parent()).text(subtotal);
	subtotal = 0;
	calculateTotal();
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
