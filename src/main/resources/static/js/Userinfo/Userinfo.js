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

function userinfo(){ // 목록으로 이동
    $("#load").show();
    location.href="/Userinfo";
}

function AddUserInfo(){ // 등록페이지로 이동
    $("#load").show();
    location.href="/userinfo_add";
}

function uiedit(seq){ // 수정저장
    var uiname = $('#uiname').val();
    var uiphone = $('#uiphone').val();
    var uistate = $('input[name="state"]:checked').val();
    var uiban = $('#uiban').val();
    console.log(uistate)

    if(uiname==null||uiname==""){
        swal({
            text: "이름을 입력해주세요.",
            icon: "info"
        });
    }else if(uiphone==null||uiphone==""){
        swal({
            text: "전화번호를 입력해주세요.",
            icon: "info"
        });
    }else{
        $('#load').show();
        let sendData = {
            "seq" : seq,
            "uiname" : uiname,
            "uiphone" : uiphone,
            "uistate" : uistate,
            "uiban" : uiban
        };
        console.log(sendData);
        $.ajax({
            url : "/userinfo_editsave",
            data : sendData,
            type : "POST",
            success : function(result){
                $("#load").hide();
                if(result.save == "1"){
                    location.href = "/Userinfo";
                }else if(result.save == "0"){
                    swal({
                        text : "저장에 실패하였습니다.",
                        icon : "error"
                    });
                }
            }, error:function(request, status, error){
                $("#load").hide();
                swal({
                    text: "통신 오류",
                    icon: "warning"
                }).then(function(){
                    location.href = "/Userinfo";
                });
            }
        });
    }
}

function uiadd(){ // 등록저장
    var uiid = $('#uiid').val();
    var uipw = $('#uipw').val();
    var uiname = $('#uiname').val();
    var uiphone = $('#uiphone').val();
    var uisms = $('input[name="sms"]:checked').val();
    var uistate = $('input[name="state"]:checked').val();
    var uiban = $('#uiban').val();
    let sendData = {
        "uiid" : uiid
    };
    if(uiid==null||uiid==""){
        swal({
            text: "아이디를 입력해주세요.",
            icon: "info"
        });
    }else{
        $('#load').show();
        $.ajax({
            url : "/userinfo_idcheck",
            data : sendData,
            type : "POST",
            success : function(result){
                if(result.save == "1"){ // 1:동일아이디 없음, 0: 동일있음
                    if(uipw==null||uipw==""){
                        swal({
                            text: "비밀번호를 입력해주세요.",
                            icon: "info"
                        });
                    }else if(uiname==null||uiname==""){
                        swal({
                            text: "이름 입력해주세요.",
                            icon: "info"
                        });
                    }else if(uiphone==null||uiphone==""){
                        swal({
                            text: "전화번호를 입력해주세요.",
                            icon: "info"
                        });
                    }else if(uisms==null||uisms==""){
                        swal({
                            text: "SMS 동의 형태를 선택해주세요.",
                            icon: "info"
                        });
                    }else if(uistate==null||uistate==""){
                        swal({
                            text: "이용상태를 선택해주세요.",
                            icon: "info"
                        });
                    }else{
                        let sendData = {
                            "uiid" : uiid,
                            "uipw" : uipw,
                            "uiname" : uiname,
                            "uiphone" : uiphone,
                            "uisms" : uisms,
                            "uistate" : uistate,
                            "uiban" : uiban
                        };
                        $.ajax({
                            url : "/userinfo_addsave",
                            data : sendData,
                            type : "POST",
                            success : function(result){
                                $("#load").hide();
                                if(result.save == "1"){
                                    location.href = "/Userinfo";
                                }else if(result.save == "0"){
                                    swal({
                                        text : "저장에 실패하였습니다.",
                                        icon : "error"
                                    });
                                }
                            }, error:function(request, status, error){
                                $("#load").hide();
                                swal({
                                    text: "통신 오류",
                                    icon: "warning"
                                }).then(function(){
                                    location.href = "/Userinfo";
                                });
                            }
                        });
                    }
                }else if(result.save == "0"){
                    $("#load").hide();
                    swal({
                        text: "중복된 아이디입니다.",
                        icon: "warning"
                    });
                }
            }, error:function(request, status, error){
                $("#load").hide();
                swal({
                    text: "통신 오류",
                    icon: "warning"
                }).then(function(){
                    location.href = "/Userinfo";
                });
            }
        });
    }
}
