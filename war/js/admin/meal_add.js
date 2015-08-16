/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */

/**
 * Handles addition of meals to datastore.
 * @author Andrew Paul Mago
 * @version 0.02
 * Version History
 * [08/15/2015] 0.01 – Andrew Paul Mago – Initial codes.
 * [08/16/2015] 0.02 - Andrew Paul Mago - AJAX implementation to view meal_detail.html and add meals.
 */

/*
 * Meal details. (for datastore entry)
 * Displays empty meal details.
 */
$(document).ready(function () {
	var container = $('#meal_detail');
	$.ajax({
    	type:'GET',
    	url: '/meals',
    	dataType:'json',
    	success: function(data,status,jqXHR){
    		console.log(data);
    			var header = '<div class="input-group">';
                    header+= '<label>Meal Name</label>';
                    header+= '<input type="text" id="mealname">';
                    header+= '</div>';
                    header+= '<div class="input-group">';
                    header+= '<label>Default Quantity</label>';
                    header+= '<input type="text" id="mealquantity">';
                    header+= '</div>';
                    header+= '<div class="input-group">';
                    header+= '<label>Unit</label>';
                    header+= '<input type="text" id="mealunit">';
                    header+= '</div>';
                    header+= '<div class="input-group">';
                    header+= '<label>Calorie count</label>';
                    header+= '<input type="text" id="mealcalories">';
                    header+= '</div>';
                    	header+= '<div class="error-messages">';
                //	<!-- use jQuery to place error messages in here
                	header+= '</div>';
                	container.append(header);
    	}
    });	
});

/*
 * Add Meal
 * Adds a meal to the datastore.
 */
$(document).on('click','#accept',function() {
	meal = {
			data: JSON.stringify({
				calories: $('#mealcalories').val(),
				defaultQuantity: $('#mealquantity').val(),
				name: $('#mealname').val(),
				unit: $('#mealunit').val(),
			})
	};
	
	$.ajax({
		type:'POST',
		url:'/meals',
		data:meal,
		dataType:'json',
		success: function(data,status,jqXHR) {
			if(data.errorList.length==0) {
				alert('Success!');
			} else {
				var msg = "";
				for (var i = 0; i < data.errorList.length; i++)
					msg += data.errorList[i] + "\n";
				alert(msg);
			}
		},
		error: function(jqXHR,status,error) {
			
		}
	});
});