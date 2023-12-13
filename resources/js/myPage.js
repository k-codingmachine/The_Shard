$(function () {
    $('.content_box').not(":first").hide();

    $('.menu a').click(function (e) {
        $('.menu a').css({
            "background": "rgb(243, 243, 243)",
            "color": "rgb(117, 117, 117)",
            "border-right": "1px solid rgb(221, 221, 221)",
            "border-left": "1px solid rgb(221, 221, 221)"
        })

        $(this).css({
            "background": "#0e0e0e",
            "color": "#fff",
            "border-right": "1px solid #0e0e0e",
            "border-left": "1px solid #0e0e0e"
        });



        e.preventDefault();

        var index = $(this).index();

        $('.content_box').stop().hide();
        $('.content_box').eq(index).stop().show();
    })
});
