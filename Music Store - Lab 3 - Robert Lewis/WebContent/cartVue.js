(function() {
    	  	
    
    $(function () {
    	var app = new Vue({
    		el: '#app',
    		data: {
    			entries: []
    		},
    		computed: {
    			total: function () {
    				var total = 0;
    				
    					this.entries.forEach(function (item) {
    					total = total + (item.qty * item.product.price)
    				})
    				return total.toFixed(2);
    			}
    		}
    	})
    	$.get("cart", 
        		function(data,status) {
    				app.entries = data;
        		});
    	
    	$(document).on("click", ".update-qty-form", function(event){
    		event.preventDefault();
    		
    		var line = $(event.target).closest("tr");
    		var itemCode = line.attr("itemCode");
    		var qty = line.find(".qty-input").val();
    		
    		$.post("cart", 
    			{
    				productCode: itemCode,
    				qty: qty
    			},
				function(data, status) {
//					alert("update Post: " + status);
					if (qty == 0) {
//						alert("qty: " + qty);
						var newList = app.entries.filter(filterItems);
						function filterItems(item) {
//							alert("yes");
							return item.product.code != itemCode;
						}
//						alert("newList: " + newList);
						app.entries = newList;
					} else {
//						alert("hello");
						newQty = app.entries.find(updateQty);
						function updateQty(item) {
//							alert("yes");
							return item.product.code == itemCode;
						}
//						alert(newList);
						newQty.qty = qty;
//						alert("newQty.qty: " + newQty.qty);
					}
					
				});	
    	});
    				
    });
}());