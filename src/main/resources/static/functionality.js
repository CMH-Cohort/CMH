	$(document).ready(function() {
		$('.status').click(function(event) {
			event.preventDefault();
			event.stopPropagation();
			toggleDone(this);
		});
	});
	
	function toggleDone(termStatusToggle) {
		console.log($(termStatusToggle)[0].id.split('-')[1]);
		var termStatusId = extractId($(termStatusToggle)[0]);
		
		// Using the core $.ajax() method
 		$.ajax({
		 
 			toggle: termStatusToggle,
 			
 			// The URL for the request
		    url: $(termStatusToggle).hasClass('done-true') ? "/markNotDone" : "/markDone",
		 
		    // The data to send (will be converted to a query string)
		    data: {
		        termStatusId: termStatusId
		    },
		    
		 	
		 	
		    // Whether this is a POST or GET request
		    type: "GET",
		 
		    // The type of data we expect back
		    dataType : "text"
		})
		// Code to run if the request succeeds (is done);
		  // The response is passed to the function
		  .done(function() {
		     $(this.toggle).toggleClass('done-true'); // adjust when using a div
		     console.log($(this.toggle).hasClass('done-true'));
		  })
		  // Code to run if the request fails; the raw request and
		  // status codes are passed to the function
		   .fail(function( xhr, status, errorThrown ) {
		    alert( "Sorry, there was a problem!" );
		    console.log( "Error: " + errorThrown );
		    console.log( "Status: " + status );
		    console.dir( xhr );
		  })
		  // Code to run regardless of success or failure;
//		  .always(function( xhr, status ) {
//		    alert( "The request is complete!" );
//		  }); 
	}
	
	function extractId(input) {
		return input.id.split('-')[1];
	}