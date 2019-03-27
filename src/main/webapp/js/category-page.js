var urlParams = new URLSearchParams(window.location.search);
var urlStringParam = urlParams.toString();

function showCategory(){
	if (urlStringParam != null){
		urlStringParam = urlStringParam.replace("=","");
	}else{
		$.notify({title: "<b>Oops!</b>", message: "There is no parameter given!"},{type: "danger"});
	}
	$.ajax({
		url: 'restservices/categories/' + urlStringParam,
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		if (result == null){
			$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the category. Maybe try a correct parameter"},{type: "danger"});
		}
		var i = 0;
		var row = "<div class=\"row\">";
		var title = urlStringParam;
		$("#title").text(title);
		$.each(result, function(index, key) {
			var products = "<div class=\"col-md-3\"><div class=\"card\"><img class=\"card-img-top\" src=\""+key.image+"\" alt=\"Card image cap\"><div class=\"card-body\"><h5 class=\"card-title\">"+key.name+"</h5><p class=\"card-text\">"+key.description+"</p><hr><p class=\"card-text\">&euro;"+key.price+"</p><a href=\"product.html?"+key.id+"\"  class=\"btn btn-primary \">Open</a></div><div class=\"card-footer\"></div></div></div>";
			$(".row").append(products);
		});
	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "There has been an error connecting to the database. Try it again in 10 minutes."},{type: "danger"});
	})
}