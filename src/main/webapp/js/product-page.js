var urlParams = new URLSearchParams(window.location.search);
var urlStringParam = urlParams.toString();


function showProduct(){
	if (urlStringParam != null){
		urlStringParam = urlStringParam.replace("=","");
	}
	$.ajax({
		url: 'restservices/products/' + urlStringParam,
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		if (result == null){
			$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the product. Maybe try a correct parameter"},{type: "danger"});
		}
		var product = "<div class=\"col-md-12\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><p class=\"card-text\">"+result.description+"</p><hr><p>&euro;"+result.price+"</p><a onclick=\"addToStorage()\" class=\"btn btn-primary float-right\">Add to shopping cart...</a></div></div></div>";
		$("#title").text(result.name);
		$(".row").append(product);
	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the product. Try it again in 10 minutes."},{type: "danger"});
	})
};

function bye(){
	console.log("bye");
}

function addToStorage(){
	var old = sessionStorage.getItem("order");
	console.log(old);
	if(old === null){ 
		old = "";
	};
	sessionStorage.setItem("order", old + id);
};




















