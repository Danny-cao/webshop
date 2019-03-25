function showCategory(){
	var category = sessionStorage.getItem("category");

	$.ajax({
		url: 'restservices/categories/' + category,
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		var i = 0;
		var row = "<div class=\"row\">";
		var title = category;
		$("#title").text(title);
		$.each(result, function(index, key) {
			if (i < 5){;
				var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.name+"</h5><p class=\"card-text\">"+key.description+"</p><hr><p class=\"card-text\">&euro;"+key.price+"</p><a href=\"product.html\" onclick=\"goToProductPage("+key.id+")\" class=\"btn btn-primary openProduct\">Open</a></div><div class=\"card-footer\"></div></div></div>";
				$(".row").append(products);
				i++;
			}
			else{
				var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.name+"</h5><p class=\"card-text\">"+key.description+"</p><hr><p class=\"card-text\">&euro;"+key.price+"</p><a href=\"product.html\" onclick=\"goToProductPage("+key.id+")\" class=\"btn btn-primary openProduct\">Open</a></div><div class=\"card-footer\"></div></div></div>";
				$(".content").append(row);
				$(".row:last").append(products);
				i = 0;
			}
		});
	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the category. Try it again in 10 minutes."},{type: "danger"});
	})
	.always(function() {
		console.log("complete");
	});

}