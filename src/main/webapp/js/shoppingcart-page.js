function deleteRow(id){
	$("#" + id).remove();
	calculateTotal();
	var regex = new RegExp(id, 'g');
	sessionStorage.setItem("item", sessionStorage.getItem("item").replace(regex, ""));
};

function editRow(price, id){
	var subtotal = 0;
	var amount = $("#" + price).val();
	console.log(id);
	calculateSubtotal(price, id);
	var regex = new RegExp(id, 'g');
	sessionStorage.setItem("item", sessionStorage.getItem("item").replace(regex, ""));
	var currentList = sessionStorage.getItem("item");
	for (i = 0; i < amount; i++) { 
		sessionStorage.setItem("item", sessionStorage.getItem("item") + id);
	};
	calculateTotal();
};

function calculateTotal(){
	var total = 0;
	$(".subtotal").each(function () {
		var stval = parseFloat($(".subtotal",$(this).parent().parent()).text());
		total += isNaN(stval) ? 0 : stval;
	});
	$('#total').text(total);
};

function calculateSubtotal(price, id) {
	var subtotal = 0;
	var amount = $("#" + price).val();
	console.log(amount);
	subtotal = amount * parseInt(price);
	$("#"+ id).text(subtotal);
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

function addToCart(){
	$.ajax({
		url: 'restservices/products',
		type: 'GET',
	})
	.done(function(result) {
		var i = 1000;
		var currentList = sessionStorage.getItem("item");
		$.each(result, function(index, key) {
			if (~currentList.indexOf(key.id)){
				var product = "<tr id="+key.id+"><td><p class=\"card-text\">"+key.name+"</p></td><td><input type=\"number\" class=\"form-control col-md-6 amount\" id="+key.price+" required name=\"amount\"></td> <td><p class=\"subtotal\" id="+i+"></p></td><td><button onclick=\"editRow("+"'"+ key.price +"'"+","+"'"+ i +"'"+")\" class=\"btn btn-warning \" >Edit</button>&nbsp;<button onclick=\"deleteRow("+"'"+ key.id +"'"+")\" class=\"btn btn-danger \">Delete</button></td></tr>";
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









