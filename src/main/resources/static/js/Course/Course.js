function DelCgo(seq){
swal({
        title: "코스 삭제",
          text: "해당 코스를 삭제하시겠습니까?",
          icon: "warning",
          closeOnClickOutside : false,
          buttons : ["취소", "삭제"],
          dangerMode: true
    }).then((result) => {
    if(result){
        $('#load').show();
        const sendData = {
                    'ccseq' : seq
                    };
        $.ajax({
                url      : "/DelCourse",
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
                            location.href = "/Course";
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
};

function EditCourse(seq){
var ccnum = document.getElementById("ccseq");
var ccname = document.getElementById("ccname");
if(ccnum === null || ccnum === "") {
        swal({
            title: "골프장을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(ccname === null || ccname === "") {
        swal({
            title: "코스를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else{
    swal({
            title: "코스 수정",
              text: "해당 코스를 수정하시겠습니까?",
              icon: "info",
              closeOnClickOutside : false,
              buttons : ["취소", "수정"]
        }).then((result) => {
        if(result){
            $('#load').show();
            let sendData = {
                "seq" : seq,
                "ccseq" : ccnum.value,
                "ccname" : ccname.value,
            }

            $.ajax({
                url      : "/ModifyCourse",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();
                    swal({
                            text: "수정완료.",
                            icon: "success",
                            closeOnClickOutside : false,
                            button: "확인"
                        }).then(function(){
                            location.href = "/Course";
                        })
                },
                error:function(request,status,error){
                    $('#load').hide();
                }
            });
        }
        });
    }
}

function ModifyCgo(seq){
$('#load').show();
location.href="/CourseModifygo/"+seq+""
}

function inCourse(){
    $('#load').show();
    location.href = "/CourseRegister";
}

function Course(){
    $('#load').show();
    location.href = "/Course";
}

function SaveCourse(){
    var ccnum = document.getElementById("ccseq");
    var ccname = document.getElementById("ccname");
    if(ccnum === null || ccnum === "") {
            swal({
                title: "골프장을 선택하세요.",
                icon: "info",
                button: "확인"
            });
            return false;
    }else if(ccname === null || ccname === "") {
            swal({
                title: "코스를 입력하세요.",
                icon: "info",
                button: "확인"
            });
            return false;
    }else{
    $('#load').show();

        let sendData = {
            "ccseq" : ccnum.value,
            "ccname" : ccname.value,
        }

        $.ajax({
            url      : "/SaveCourse",
            data     : sendData,
            type     : "POST",
            success : function(result) {
                $('#load').hide();
                swal({
                        text: "등록완료.",
                        icon: "success",
                        closeOnClickOutside : false,
                        button: "확인"
                    }).then(function(){
                        location.href = "/Course";
                    })
            },
            error:function(request,status,error){
                $('#load').hide();
            }
        });
    }
}

// 검색필터
function searching(){

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

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
        url: "/Course_search",
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
        url: "/Course_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
        $("#load").hide();

    });

}

function enterkey(){
    if(window.event.keyCode == 13){
        searching();
    }
}