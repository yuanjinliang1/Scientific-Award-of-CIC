$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
 
        done: function (e, data) {
            $("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
 
                $("#uploaded-files").append(
                        $('<tr/>')
                        .append($('<td/>').text(file.fileName))
                        .append($('<td/>').text(file.fileSize))
                        .append($('<td/>').text(file.fileType))
                        .append($('<td/>').html("<a class=\"btn btn-default\" href='get/"+index+"'>download</a>"))
                        .append($('<td/>').html("<a class=\"btn btn-default todelete\" href='#' data-url='delete/"+index+"'>delete</a>"))
                        )//end $("#uploaded-files").append()
            }); 
        },
 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        },
 
        dropZone: $('#dropzone')
    })
}
);

$(function(){
    $('.todelete').click(function(){
        $.ajax({
        	url: $(this).attr('data-url'),
        	type: 'GET',
        	success: $(this).remove
        });
    });
})
