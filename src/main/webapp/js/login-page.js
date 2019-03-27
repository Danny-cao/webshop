function login() {
	$("#login_bttn").click(function(event) {
		event.preventDefault();

		var email = $("#email").val();
		var password = $("#password").val();

		$.ajax({
			url: 'restservices/authentication',
			type: 'POST',
			data: {email: email, password: password},
		})
		.done(function(response) {
			if(response == null){
				$.notify({title: "<b>Unfortunately...</b>", message: "The email or password is incorrect."},{type: "danger"});
			}else{
				$.notify({title: "<b>Success!</b>", message: "Logged in!"},{type: "success"});

				// opslaan in session storage
				sessionStorage.setItem("JWT", response.JWT);				
			}
		})
		.fail(function() {
			$.notify({title: "<b>Unfortunately...</b>", message: "The email or password is incorrect."},{type: "danger"});

		})
		.always(function() {
			$.ajax({
				url: 'restservices/authentication/details',
				type: 'POST',
				data: {jwt: sessionStorage.getItem("JWT")}
			})
			.done(function(result) {
				console.log(result);
				sessionStorage.setItem("customerID", result.id);
				window.location.href = 'sale.html';
			})
		});
		
	});
}