
// 검색필터
function searching(){

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

    if(titleText == null){
            titleText = "";
    }

    console.log(titleText);
    const params = {
        page: 0,
        selectKey: selectKey,
        titleText: titleText
    }

    const queryString = new URLSearchParams(params).toString();

    const replaceUri = location.pathname + '?' + queryString;

    history.pushState(null, '', replaceUri);

    //값 가져오기 (페이지네이션)
    const myPageQuery = new URLSearchParams(location.search);

    var querydata = { "page" : myPageQuery.get('page'), "selectKey":myPageQuery.get('selectKey'),"titleText":myPageQuery.get('titleText')};

    $.ajax({
        url: "/search_Reservation",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
    });

}

// 페이징
function paging(pageValue){
    const myPageQuery = new URLSearchParams(location.search);

    console.log(pageValue);

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

    $("#load").show();

    //문자열 "null" 이 들어가는것 방지하기 위해 값이 null 이라면 공백 문자열 대입
    if(titleText == null){
        titleText = "";
    }

    // 대입 끝

    //url 주소 바꾸기
    const params = {
        page: pageValue,
        selectKey: selectKey,
        titleText: titleText
    }
    const queryString = new URLSearchParams(params).toString();
    const replaceUri = location.pathname + '?' + queryString;
    history.pushState(null, '', replaceUri);
    //url 주소 바꾸기 끝


    var querydata = { "page" : pageValue, "selectKey":selectKey, "titleText":titleText};


    $.ajax({
        url: "/search_Reservation",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
        $("#load").hide();

    });

}

function searching1(Ccname){

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

    if(titleText == null){
            titleText = "";
    }

    console.log(titleText);
    const params = {
        page: 0,
        selectKey: "CC",
        titleText: Ccname
    }

    const queryString = new URLSearchParams(params).toString();

    const replaceUri = location.pathname + '?' + queryString;

    history.pushState(null, '', replaceUri);

    //값 가져오기 (페이지네이션)
    const myPageQuery = new URLSearchParams(location.search);

    var querydata = { "page" : myPageQuery.get('page'), "selectKey":myPageQuery.get('selectKey'),"titleText":myPageQuery.get('titleText')};

    $.ajax({
        url: "/search_Reservation1",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
    });

}

function Waitinfoin(){
location.href="/WaitRegisterInfo"
}

function chCC(){
    $('option.chcourse').remove();
    var cname = document.getElementById('ccname').value;
    const sendData = {
                        'seq' : cname
                    }
        $.ajax({
                    url      : "/chCC",
                    data     : sendData,
                    type     : "POST",
                    success : function(result) {
                        $('#load').hide();
//                        $('#myModal3').show();
                        for(var i=0; i<result.course.length; i++){
                            $("#course").append(
                                "<option class='chcourse' value='"+result.course[i].cno+"'>"+result.course[i].cname+"</option>"
                            )
                        }
                    },
                    error:function(request,status,error){
                        $('#load').hide();
                        swal({
                            text: "서버에 문제가 발생했습니다.",
                            icon: "warning" //"info,success,warning,error" 중 택1
                        });
                    }
                });
}