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
				var productId = currentProduct.id;
				var product = "<div class=\"col-md-12\"><div class=\"card\"><img class=\"card-img-top\" src=\"pictures/t-shirt01.jpeg\" alt=\"Card image cap\"><div class=\"card-body\"><p class=\"card-text\">"+currentProduct.description+"</p><hr><p>&euro;"+currentProduct.price+"</p><a onclick=\"addToStorage("+productId+")\" class=\"btn btn-primary float-right\">Add to shopping cart...</a></div></div></div>";
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

function bye(){
	console.log("bye");
}

function addToStorage(id){
	var old = sessionStorage.getItem("item");
	console.log(old);
	if(old === null){ 
		old = "";
	};
	sessionStorage.setItem("item", old + id);
};




















