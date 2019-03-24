function showProduct(){
	var id = sessionStorage.getItem("id") - 1;

	$.ajax({
		url: 'restservices/products',
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		var title = result[id].name;
		var product = "<div class=\"col-md-12\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><p class=\"card-text\">Leuk shirtje i guess</p><hr><p>&euro;"+result[id].price+"</p><a href=\"#\" class=\"btn btn-primary float-right\">Add to shopping cart...</a></div></div></div>";
		$("#title").text(title);
		$(".row").append(product);
		console.log("success");
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});
}