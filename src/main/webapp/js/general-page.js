function getAllCategories(){
	$.ajax({
		url: 'restservices/categories',
		type: 'GET'
	})
	.done(function(result) {
		$.each(result, function(index, key) {
			var category = String(key.name);
			console.log(category);
			$( "#dropdown" ).append( "<a class=\"dropdown-item\" href=\"category.html\" onclick=\"goToCategoryPage("+"'"+ category +"'"+")\">"+key.name+"</a>" );
		})
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});
}

function goToCategoryPage(category){
	sessionStorage.setItem("category",category);
}

function goToProductPage(id){
	sessionStorage.setItem("id",id);
}
