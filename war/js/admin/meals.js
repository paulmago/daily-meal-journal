/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */

/**
 * Handles the population of meals data from datastore.
 * @author Andrew Paul Mago
 * @version 0.01
 * Version History
 * [08/15/2015] 0.01 – Andrew Paul Mago – AJAX implementation to populate meals at meals.html.
 */

/*
 * Display meals
 * Populate meals from datastore on page.
 */
$(document).ready(function () {
	var container = $('#meals');
	$.ajax({
    	type:'GET',
    	url: '/meals',
    	data:'null',
    	dataType:'json',
    	success: function(data){
    		console.log(data);
    		$.each(data,function(i,meal){		
    			var header = '<a class="list-item" href="meals/edit?id='+meal.mealId+'">'; //admin/meals?id='+meal.mealId+'
    			//header += '<input type="hidden" value="'+meal.mealId+'" name="'+meal.mealId+'">';
    			header += '<img src="../svg/hard-boiled-eggs.jpg" class="list-item-avatar">';
    			header += '<div class="list-item-text">';
    			header += '<h3>'+meal.name+'</h3>';
    			header += ' <p> Calories: '+meal.calories+'</p>';
    			header +='</div>';
    			header +=' </a>';
	            container.append(header);             		
    		});
    	}
    });
});
