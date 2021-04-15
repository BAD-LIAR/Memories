$(document).ready(function() {

    //Удаляем запись при клике по крестику
    $("body").on("click", ".ban", function(e) {
        e.preventDefault();
        write("123")
        var clickedID = this.id.split("-"); //Разбиваем строку (Split работает аналогично PHP explode)
        var DbNumberID = clickedID[1]; //и получаем номер из массива
        var myData = 'recordToDelete='+ DbNumberID; //выстраиваем  данные для POST

        jQuery.ajax({
            type: "POST", // HTTP метод  POST или GET
            url: "ban", //url-адрес, по которому будет отправлен запрос
            dataType:"text", // Тип данных
            data:myData, //post переменные
            success:function(response){
                // в случае успеха, скрываем, выбранный пользователем для удаления, элемент
                $('#ban'+DbNumberID).disabled();
                $('#unban'+DbNumberID).active();
            },
            error:function (xhr, ajaxOptions, thrownError){
                //выводим ошибку
                alert(thrownError);
            }
        });
    });

    //Удаляем запись при клике по крестику
    $("body").on("click", ".unban", function(e) {
        e.preventDefault();
        var clickedID = this.id.split("-"); //Разбиваем строку (Split работает аналогично PHP explode)
        var DbNumberID = clickedID[1]; //и получаем номер из массива
        var myData = 'recordToDelete='+ DbNumberID; //выстраиваем  данные для POST

        jQuery.ajax({
            type: "POST", // HTTP метод  POST или GET
            url: "unban", //url-адрес, по которому будет отправлен запрос
            dataType:"text", // Тип данных
            data:myData, //post переменные
            success:function(response){
                // в случае успеха, скрываем, выбранный пользователем для удаления, элемент
                $('#unban'+DbNumberID).disabled();
                $('#ban'+DbNumberID).active();
            },
            error:function (xhr, ajaxOptions, thrownError){
                //выводим ошибку
                alert(thrownError);
            }
        });
    });
});
