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

function checkLogin(page) {
	var jwt = sessionStorage.getItem("jwt");

	if (name == undefined) {
		if (page != 'index') {
			window.location.href = 'index.html';
			sessionStorage.clear();
		}
	}else{
		if (page == 'index') {
			window.location.href = 'sale.html';
		}
	}
}

$('.logout').click(function() {
	swal({
		text: "Are you sure you want to log out?",
		icon: "warning",
		buttons: true,
		dangerMode: true,
		buttons: ["Cancel", "Log out"]
	}) .then((logout) => {
		if (logout) {
			swal("You have been logged out!", {
				icon: "success",
			});
			sessionStorage.clear();
			window.location.href = 'sale.html';
		}
	});
});
