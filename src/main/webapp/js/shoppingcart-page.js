var euro = '\u20AC';

function deleteRow(id){
	$("#" + id).remove();
	calculateTotal();
	var regex = new RegExp(id, 'g');
	sessionStorage.setItem("order", sessionStorage.getItem("order").replace(regex, ""));
};

function editRow(price, id, totalId){
	var subtotal = 0;
	var amount = $("#" + price).val();
	if (amount <= 100){
		console.log(id);
		console.log(totalId);
		calculateSubtotal(price, id);
		var regex = new RegExp(totalId, 'g');
		sessionStorage.setItem("order", sessionStorage.getItem("order").replace(regex, ""));
		var currentList = sessionStorage.getItem("order");
		for (i = 0; i < amount; i++) { 
			console.log(id);
			sessionStorage.setItem("order", sessionStorage.getItem("order") + totalId);
		};
		calculateTotal();
	}else{
		$.notify({title: "<b>Oops!</b>", message: "The amount has to be between 1 and 100."},{type: "warning"});

	}
};

function calculateTotal(){
	var total = 0;
	$(".subtotal").each(function () {
		var string = $(".subtotal",$(this).parent().parent()).text();
		var stripped = string.replace(/\D/g,'');
		var stval = parseFloat(stripped);
		total += isNaN(stval) ? 0 : stval;
	});
	var withEuro = euro.concat(total);
	$('#total').text(withEuro);
};

function calculateSubtotal(price, id) {
	var subtotal = 0;
	var amount = $("#" + price).val();
	console.log(amount);
	subtotal = amount * parseInt(price);
	var withEuro = euro.concat(subtotal);
	$("#"+ id).text(withEuro);
	calculateTotal();
};

function sendCart(){
	items = sessionStorage.getItem("order");
	var myObject = new Object();
	myObject.items = listitems;
	var myString = JSON.stringify(myObject);
}

function setAmount(){
	var itemList = sessionStorage.getItem("order");
	$(".amount").each(function(){
		var id = $(this).closest('tr').attr('id');
		var regex = new RegExp(id, 'g');
		var count = (itemList.match(regex)).length;
		$(".amount",$(this).parent().parent()).val(count);
	})
}

function addToCart(){
	$.ajax({
		url: 'restservices/products',
		type: 'GET',
	})
	.done(function(result) {
		var i = 1000;
		var currentList = sessionStorage.getItem("order");
		$.each(result, function(index, key) {
			if (~currentList.indexOf(key.id)){
				var product = "<tr id="+key.id+"><td><p class=\"card-text\">"+key.name+"</p></td><td><input type=\"number\" class=\"form-control col-md-6 amount\" id="+key.price+" required name=\"amount\"></td> <td><p class=\"subtotal\" id="+i+"></p></td><td><button onclick=\"editRow("+"'"+ key.price +"'"+","+"'"+ i +"'"+","+"'"+ key.id +"'"+")\" class=\"btn btn-warning \" >Edit</button>&nbsp;<button onclick=\"deleteRow("+"'"+ key.id +"'"+")\" class=\"btn btn-danger \">Delete</button></td></tr>";
				$("#shoppingCartTable").append(product);
				setAmount();
				calculateSubtotal(key.price, i);
				i++;
			}

		})
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		calculateTotal();
	});
}

function sendOrder(){
	var completeOrder = sessionStorage.getItem("order");
	$.ajax({
		url: 'restservices/order',
		type: 'POST',
		data: {order: completeOrder}
	})
	.done(function() {
		sessionStorage.setItem("order","");
		$.notify({title: "<b>Awesome!</b>", message: "The order has been placed!"},{type: "success"});
		$("#shoppingCartTable > tr").empty();
		calculateTotal();

	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "Something went wrong. Try it again in a few minutes."},{type: "danger"});

	})
}









