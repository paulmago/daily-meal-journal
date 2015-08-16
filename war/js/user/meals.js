$(document).ready(function () {
	
	var container = $('#meals');
	
	$.ajax({
    	type:'GET',
    	url: '/meals',
    	dataType: 'json',
    	success: function(data){
    		//console.log(data);
    		$.each(data,function(i,meal){	
    			var header = '<a class="list-item" href="journal_detail.html">';
    			header += '<img src="../svg/hard-boiled-eggs.jpg" class="list-item-avatar">';
    			header += '<div class="list-item-text">';
    			header += '<h3>'+meal.name+'</h3>';
    			header += ' <p>ASaas aksajks lSkajsaljslka</p>';
    			header +='</div>';
    			header +=' </a>';
	            container.append(header);             		
    		});
    	}
    });	
});