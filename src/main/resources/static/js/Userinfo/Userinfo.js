// 검색필터
function searching(){

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

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
        url: "/userinfo_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#infotable").replaceWith(fragment);
    });

}

// 페이징
function paging(pageValue){
    const myPageQuery = new URLSearchParams(location.search);

    console.log(pageValue);

    var titleText = myPageQuery.get('titleText');
    var selectKey = myPageQuery.get('selectKey');

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
        url: "/userinfo_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#infotable").replaceWith(fragment);
        $("#load").hide();

    });

}

function enterkey(){
    if(window.event.keyCode == 13){
        searching();
    }
}

function detailUser(seq){
    console.log(seq);
    let sendData = {
                "seq" : seq
            }

    $.ajax({
        url      : "/userinfo_detailgo",
        data     : sendData,
        type     : "GET",
        success : function(result) {
            $('#load').hide();
            location.href="/userinfo_detail";
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

function userinfo(){
    location.href="/Userinfo";
}
