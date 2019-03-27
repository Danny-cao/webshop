function getAllCategories(){
	$.ajax({
		url: 'restservices/categories',
		type: 'GET'
	})
	.done(function(result) {
		$.each(result, function(index, key) {
			var category = String(key.name);
			$( "#dropdown" ).append( "<a class=\"dropdown-item\" href=\"category.html?"+key.name+"\" >"+key.name+"</a>" );
		})
	})
}
