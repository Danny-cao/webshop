function addAllCategories(){
	$.ajax({
		url: 'restservices/categories',
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		var i = 0;
		var row = "<div class=\"row\">";
		$.each(result, function(index, key) {
			if (i < 5){;
				var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.category+"</h5><p class=\"card-text\">"+key.description+"</p><hr><a href=\"category.html\" onclick=\"goToCategorytPage("+key.category+")\" class=\"btn btn-primary openCategory\">Open</a></div><div class=\"card-footer\"></div></div></div>";
				$(".row").append(products);
				i++;
			}
			else{
				var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.category+"</h5><p class=\"card-text\">"+key.description+"</p><hr><a href=\"category.html\" onclick=\"goToCategorytPage("+key.category+")\" class=\"btn btn-primary openCategory\">Open</a></div><div class=\"card-footer\"></div></div></div>";
				$(".content").append(row);
				$(".row:last").append(products);
				i = 0;
			}
		});
	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the categories. Try it again in 10 minutes."},{type: "danger"});
	})
	.always(function() {
		console.log("complete");
	});

}

function goToCategorytPage(category){
	sessionStorage.setItem("category",category);
}