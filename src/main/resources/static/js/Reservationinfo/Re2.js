
// 검색필터
function searching(){

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

    console.log(titleText);

    if(titleText == null){
            titleText = "";
    }

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
        url: "/search_Reservation3",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
    });

}

// 페이징
function paging(pageValue){
    var set = document.getElementById('set').innerText;
    console.log(set);
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


    var querydata = { "page" : pageValue, "selectKey":selectKey, "titleText":titleText, "set":set};


    $.ajax({
        url: "/search_Reservation3",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
        $("#load").hide();
    });

}

function Delinfo(seq){
swal({
        title: "예약 내역 삭제",
          text: "해당 예약 내역을 삭제하시겠습니까?",
          icon: "warning",
          closeOnClickOutside : false,
          buttons : ["취소", "삭제"],
          dangerMode: true
    }).then((result) => {
    if(result){
        const sendData = {
                    'seq' : seq
                    };
        $.ajax({
                url      : "/Delstate",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();
                    swal({
                            text: "삭제완료.",
                            icon: "success",
                            closeOnClickOutside : false,
                            button: "확인"
                        }).then(function(){
                            location.href = "/Reservation3";
                        })
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
    });
}

function searching1(ppp){
    var set = document.getElementById('set').innerText;
    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

    if(titleText == null){
            titleText = "";
    }

    const params = {
        page: 0,
        selectKey: "CC",
        titleText: ppp.value
    }

    const queryString = new URLSearchParams(params).toString();

    const replaceUri = location.pathname + '?' + queryString;

    history.pushState(null, '', replaceUri);

    //값 가져오기 (페이지네이션)
    const myPageQuery = new URLSearchParams(location.search);

    var querydata = { "page" : myPageQuery.get('page'), "selectKey":myPageQuery.get('selectKey'),"titleText":myPageQuery.get('titleText')};

    $.ajax({
        url: "/search_Reservation3",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#set").text(ppp.value);
        $("#intable").replaceWith(fragment);
    });

}