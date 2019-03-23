function getAllCategories(){
	$.ajax({
		url: '/path/to/file',
		type: 'default GET'
	})
	.done(function() {
		console.log("success");
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});
}

function addToDropdown(){
	$( "#dropdown" ).append( "<a class=\"dropdown-item\" href=\"realcategories.html\">T-shirts</a>" );
	$( "#dropdown" ).append( "<a class=\"dropdown-item\" href=\"realcategories.html\">Hoodies</a>" );
	$( "#dropdown" ).append( "<a class=\"dropdown-item\" href=\"realcategories.html\">Sweaters</a>" );
}