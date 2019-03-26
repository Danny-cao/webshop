var id = sessionStorage.getItem("id");

function hello(){
	console.log("hello");
}

function showProduct(){
	$.ajax({
		url: 'restservices/products',
		type: 'GET',
		dataType: 'json'
	})
	.done(function(result) {
		for (let index in result){
			let currentProduct = result[index];
			if(currentProduct['id'] == id){
				var title = currentProduct.name;
				var product = "<div class=\"col-md-12\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><p class=\"card-text\">"+currentProduct.description+"</p><hr><p>&euro;"+currentProduct.price+"</p><a onclick=\"addToStorage("+currentProduct.id+")\" class=\"btn btn-primary float-right\">Add to shopping cart...</a></div></div></div>";
				$("#title").text(title);
				$(".row").append(product);
			}
		}
	})
	.fail(function() {
		$.notify({title: "<b>Oops!</b>", message: "There has been an error loading the product. Try it again in 10 minutes."},{type: "danger"});
	})
	.always(function() {
		console.log("complete");
	})
};


function addToStorage(id){
	var old = localStorage.getItem("item");
	if(old === null){ 
		old = "";
	};
	localStorage.setItem("item", old + "," + id);
}

















