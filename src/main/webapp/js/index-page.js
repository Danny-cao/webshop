function addAllSaleProducts(){
	$.ajax({
		url: 'restservices/products',
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		$.each(result, function(index, key) {
			var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.name+"</h5><p class=\"card-text\">"+key.description+"</p><hr><p class=\"card-text\">&euro;"+key.price+"</p><a href=\"product.html\" onclick=\"goToProductPage("+key.id+")\" class=\"btn btn-primary openProduct\">Open</a></div><div class=\"card-footer\"><small class=\"text-muted\">sale started at:+key.saleBegin+<br>sale ends at:+key.saleEnd+</small></div></div></div>";
			$(".row").append(products);
		});
	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the products. Try it again in 10 minutes."},{type: "danger"});
	})
	.always(function() {
		console.log("complete");
	});

}