$(document).ready(function () {
	var container = $('#journals');
	
	$.ajax({
    	type:'GET',
    	url: '/journals',
    	success: function(data){
    		console.log(data);
    		$.each(data,function(i,journal){
    			var total=0;
    			var obj = journal.journals;
    			var header = '<div class="list-header">';
    			header += '<span class="primary-text">' + journal.dateCreated + '</span>';
	            $.each(obj, function(j, data) {	
	            	total+=parseInt(data.calories);

        		});	
	            header += '<span class="secondary-text">' + total + ' calorie(s)</span>';     
	            header += '</div>';
	            container.append(header); 


    			$.each(obj, function(j, data) {
        			// container.append('<div class="primary-text"> Journal' +j+';'  +data.name+' Meal Jounal ID:'+data.mealJournalId+'</div>');
    				var item = '<a class="list-item" href="journal_detail.html">';//
                    item += '<img src="../svg/hard-boiled-eggs.jpg" class="list-item-avatar">';
                    item += '<div class="list-item-text">';
                    item += '<h3>' + data.name + '</h3>';
                    item += '<p>Calories: ' + data.calories + '</p>';
                    item += '</div></a>';
                    container.append(item);
        		});		

    		});
    	}
    });	
	 
});
$("#atest").click(function () {
    alert("test");
});
