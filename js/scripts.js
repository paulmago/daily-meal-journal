$(document).ready(function(){
    $('.input-group > input[type="text"]').focus(function () {
        $(this).firstChild().css('color', '#3c5eb9');
    });
});