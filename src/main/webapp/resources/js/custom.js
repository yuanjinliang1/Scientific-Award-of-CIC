
//fix textarea cannot editing
$('textarea').on('click', ':input', function (event) {
    event.stopPropagation();
});
