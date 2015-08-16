/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */

/**
 * Handles RUD methods of meals to datastore.
 * @author Andrew Paul Mago
 * @version 0.02
 * Version History
 * [08/15/2015] 0.01 – Andrew Paul Mago – Initial codes. AJAX implementation of displaying meal details.
 * [08/16/2015] 0.02 - Andrew Paul Mago - AJAX implementation to delete and edit meals.
 */

/*
 * Meal details
 * Displays meal details.
 */
$(document).ready(function () {
	var container = $('#meal_detail');
	$.ajax({
    	type:'GET',
    	url: '/meals?id='+getURLParameter('id'),
    	dataType:'json',
    	success: function(data,status,jqXHR){
    		console.log(data);
    			var header = '<div class="input-group">';
                    header+= '<label>Meal Name</label>';
                    header+= '<input type="text" id="mealname" value="'+data.name+'">';
                    header+= '</div>';
                    header+= '<div class="input-group">';
                    header+= '<label>Default Quantity</label>';
                    header+= '<input type="text" id="mealquantity" value="'+data.defaultQuantity+'">';
                    header+= '</div>';
                    header+= '<div class="input-group">';
                    header+= '<label>Unit</label>';
                    header+= '<input type="text" id="mealunit" value="'+data.unit+'">';
                    header+= '</div>';
                    header+= '<div class="input-group">';
                    header+= '<label>Calorie count</label>';
                    header+= '<input type="text" id="mealcalories" value="'+data.calories+'">';
                    header+= '</div>';
                    header+= '<div class="error-messages">';
                //	<!-- use jQuery to place error messages in here
                	header+= '</div>';
                	container.append(header);
    	}
    });	
});

/*
 * Delete Meal
 * Deletes meal from the datastore.
 */
$(document).on('click','#delete',function() {
	$.ajax({
		type:'DELETE',
		url: '/meals?id='+getURLParameter('id'),
		success: function(data,status,jqXHR) {
			if(data.errorList.length==0) {
				//window.location.href = '/admin/meals';
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


/*
 * Edit Meal
 * Modifies meal on datastore.
 */
$(document).on('click','#accept',function() {
	meal = {
			data: JSON.stringify({
				mealId: getURLParameter('id'),
				calories: $('#mealcalories').val(),
				defaultQuantity: $('#mealquantity').val(),
				name: $('#mealname').val(),
				unit: $('#mealunit').val(),
			})
	};
	$.ajax({
		type:'PUT',
		url: '/meals',
		data:meal,
		dataType:'json',
		success: function(data,status,jqXHR) {
			if(data.errorList.length==0) {
				//window.location.href = '/admin/meals';
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

/*
 * Method to retrieve meal id.
 */
function getURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}