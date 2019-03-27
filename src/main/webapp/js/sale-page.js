function addAllSaleProducts(){
	$.ajax({
		url: 'restservices/sales',
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		$.each(result, function(index, key) {
			var originalPrice = key.product.price;
			var stringOriginalPrice = originalPrice.toString();
			var strikedOriginalPrice = "<strike>"+originalPrice+"</strike>";
			var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\""+key.product.picture+"\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.product.name+"</h5><p class=\"card-text\">"+key.product.description+"</p><hr><p class=\"card-text\"> original price:  &euro;"+strikedOriginalPrice+"<br><b>sale price: &euro;"+key.price+"</b></p><a href=\"product.html?"+key.product.id+"\" class=\"btn btn-primary \">Open</a></div><div class=\"card-footer\"><small class=\"text-muted\">sale started at: "+key.begin+"<br>sale ends at: "+key.end+"</small></div></div></div>";
			$(".row").append(products);
		});
	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the products. Try it again in 10 minutes."},{type: "danger"});
	})

};