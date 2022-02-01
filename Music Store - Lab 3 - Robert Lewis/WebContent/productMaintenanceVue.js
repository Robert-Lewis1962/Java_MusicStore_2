(function() {
    	  	
    
    $(function () {
    	var app = new Vue({
    		el: '#app',
    		data: {
    			items: []
    		}
    	})
    	$.ajax({
            url: 'productMaint',
            method: 'POST',
            success: function (data) {
//                alert(data);
                data.forEach(function (item){
//                	alert(item.code);
                	item.editProduct = "editProduct?productCode=" + item.code;
//                	alert("Before: " + item.price);
                	item.price = item.price.toFixed(2);
//                	alert("After: " + item.price);
                })
                app.items = data;
                
            },
            error: function (error) {
                alert("ERROR: " + error);
            }
        });
    	

    	$(document).on("click", ".deleteProduct", function(event){
    		event.preventDefault();
//    	    alert("Check1");
    		var line = $(event.target).closest("tr");
//    		alert("Check2");
    		var itemCode = line.attr("itemCode");
//    		alert("Check3");
    		var check = confirm("Are you sure you want to remove product:" + itemCode + " form the list?");
    		
//    		alert("itemCode: " + itemCode);
    		
    		
    		if (!check) {
    			return;
    		}
    		
    		$.post("deleteProduct", 
    		{
    			itemCode: itemCode
    		},
    		function(data,status) {
    			
//    			alert("data: " + data);
    			
    			var newList = app.items.filter(filterItems);
    			function filterItems(item) {
//    				alert("yes");
    			    return item.code != itemCode;
    			}
    			
    			app.items = newList;
    		});

	    });
    });
}());