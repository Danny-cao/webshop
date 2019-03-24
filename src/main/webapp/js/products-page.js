function addAllProducts(){
	$.ajax({
		url: 'restservices/products',
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		var i = 0;
		var row = "<div class=\"row\">";
		$.each(result, function(index, key) {
			if (i < 5){
				var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.name+"</h5><p class=\"card-text\">Leuk shirtje i guess</p><hr><p class=\"card-text\">&euro;"+key.price+"</p><a href=\"product.html\" onclick=\"goToProductPage("+key.id+")\" class=\"btn btn-primary openProduct\">Open</a></div><div class=\"card-footer\"></div></div></div>";
				$(".row").append(products);
				i++;
				console.log("success");
				console.log(i);
			}
			else{
				var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.name+"</h5><p class=\"card-text\">Leuk shirtje i guess</p><hr><p class=\"card-text\">&euro;"+key.price+"</p><a href=\"product.html\" onclick=\"goToProductPage("+key.id+")\" class=\"btn btn-primary openProduct\">Open</a></div><div class=\"card-footer\"></div></div></div>";
				$(".content").append(row);
				$(".row:last").append(products);
				i = 0;
			}
		});
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});

}

function goToProductPage(id){
	sessionStorage.setItem("id",id);
}