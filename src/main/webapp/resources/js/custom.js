
//fix textarea cannot editing
$('textarea').on('click', ':input', function (event) {
    event.stopPropagation();
});

$(window).on('resize load', function() {
    $('body').css({"padding-top": $(".navbar").height() + "px"});
})