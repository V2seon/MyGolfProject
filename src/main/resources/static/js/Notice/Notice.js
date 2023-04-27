function Notice(){
$('#load').show();
    location.href="/Notice";
}

function NoticeRe(){
$('#load').show();
    location.href="/NoticeRegister";
}

function inNotice(){
    var title = document.getElementById('title').value;
    var content = document.getElementById('content').value;

    let sendData = {
        "title" : title,
        "content" : content
    }

    $.ajax({
                url      : "/inNotice",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    swal({
                          title : "등록되었습니다.",
                          closeOnClickOutside : false,
                          icon: "success",
                        }).then(function(){
                            location.href = "/Notice";
                        });
                },
                error:function(request,status,error){
                }
            });
}

function ModifyNoticego(seq){
$('#load').show();
location.href="/NoticeModifygo/"+seq+""
}


function EditNotice(seq){
    $('#load').show();
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;

    let sendData = {
        "seq" : seq,
        "title" : title,
        "content" : content
    }

    $.ajax({
                url      : "/EditNotice",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();
                    swal({
                          title : "수정되었습니다.",
                          closeOnClickOutside : false,
                          icon: "success",
                        }).then(function(){
                            location.href = "/Notice";
                        });
                },
                error:function(request,status,error){
                }
            });
}

// 검색필터
function searching(){

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

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
        url: "/notice_search",
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
        url: "/notice_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
        $("#load").hide();

    });

}