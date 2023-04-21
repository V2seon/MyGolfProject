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
        url: "/userinfoCCList_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#set").text(ppp.value);
        $("#intable").replaceWith(fragment);
    });
}


// CC계정 등록페이지로 이동
function addccacount(){
    $("#load").show();
    location.href="/ccacount_add";
}
// CC계정 수정페이지로 이동
function uiccedit(no){
    $("#load").show();
    location.href="/ccacount_edit?no="+no;
}
// CC계정 삭제
function uiccdel(no){
    swal({
        title : "계정을 삭제하시겠습니까?",
        icon : "info",
        buttons: {
            confirm: {
                text: "YES",
                value: true
            },
            cancle: {
                text: "NO",
                value: false
            }
        }
    }).then(function(result){
        $('#load').show();
        $.ajax({
            url : "/userinfoCC_delete",
            data : {"no" : no},
            type : "GET",
            success : function(result){
                $("#load").hide();
                swal({
                    text: "삭제되었습니다.",
                    icon: "info"
                }).then(function(){
                    location.href = "/UserInfoCCList";
                });
            }, error:function(request, status, error){
                $("#load").hide();
                swal({
                    text: "통신 오류",
                    icon: "warning"
                }).then(function(){
                    location.href = "/UserInfoCCList";
                });
            }
        });
    });
}

function loginCheckGo(){
var ccno = $("#caccno").val();
var ccid = $("#caid").val();
var ccpw = $("#capassword").val();

if(ccno==null||ccno==""){
    swal({
        text: "골프장을 선택해주세요.",
        icon: "info"
    });
}else if(caid==null||caid==""){
    swal({
        text: "아이디를 입력해주세요.",
        icon: "info"
    });
}else if(ccpw==null||ccpw==""){
    swal({
        text: "아이디를 입력해주세요.",
        icon: "info"
    });
}else {
    if(ccno=="17" || ccno=="40" || ccno=="41"){
            $('#load').show();
            // 로그인 시도 기능 작성
            const sendData = {
                                'ccno' : ccno,
                                'ccid': ccid,
                                'ccpw':ccpw
                                };
                    $.ajax({
                            url      : "/cklogin",
                            data     : sendData,
                            type     : "POST",
                            success : function(result) {
                                $('#load').hide();
                                console.log(result.data);
                                if(result.data == "0"){
                                    swal({
                                            text: "로그인 성공",
                                            icon: "success" //"info,success,warning,error" 중 택1
                                        });
                                    $("#castate").attr("value","1");
                                    document.getElementById('castate').innerText = "성공";
                                }else if(result.data == "1"){
                                    swal({
                                            text: "로그인 실패",
                                            icon: "error" //"info,success,warning,error" 중 택1
                                        });
                                    $("#castate").attr("value","0");
                                    document.getElementById('castate').innerText = "실패";
                                }
                            },
                            error:function(request,status,error){
                                $('#load').hide();

                            }
                        });
        }else{
            swal({
                text: "로그인 확인이 불가능한 골프장입니다.",
                icon: "info"
            }).then(function(){
                location.href = "/UserInfoCCList";
            });
        }
}
}

// CC계정 등록 저장
function addCASave(){
    var caccno = $("#caccno").val();
    var cauino = $("#cauino").val();
    var caid = $("#caid").val();
    var capassword = $("#capassword").val();
    var castate = $("#castate").val();

    if(caccno==null||caccno==""){
        swal({
            text: "골프장을 선택해주세요.",
            icon: "info"
        });
    }else if(cauino==null||cauino==""){
        swal({
            text: "사용자정보를 선택해주세요.",
            icon: "info"
        });
    }else if(caid==null||caid==""){
        swal({
            text: "아이디를 입력해주세요.",
            icon: "info"
        });
    }else if(capassword==null||capassword==""){
        swal({
            text: "비밀번호를 입력해주세요.",
            icon: "info"
        });
    }else{
        $('#load').show();
        $.ajax({
            url : "/uicc_idcheck",
            data : {"ccno" : caccno, "caid" : caid},
            type : "POST",
            success : function(result){
                if(result.save == "1"){ // 1:동일아이디 없음, 0: 동일있음
                        let sendData = {
                            'caccno':caccno,
                            'cauino':cauino,
                            'caid':caid,
                            'capassword':capassword,
                            'castate':castate
                        }
                        $.ajax({
                            url     : "/ccacount_addsave",
                            data    : sendData,
                            type    : "POST",
                            success : function(){
                                $('#load').hide();
                                swal({
                                    text: "등록완료.",
                                    icon: "success",
                                    closeOnClickOutside : false,
                                    button: "확인"
                                }).then(function(){
                                    location.href = "/UserInfoCCList";
                                });
                            }, error: function(request, status, error){
                                $('#load').hide();
                                    swal({
                                    text: "서버에 문제가 발생했습니다.",
                                    icon: "warning" //"info,success,warning,error" 중 택1
                                });
                            }
                        });
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

// CC계정 등록 수정
function editCASave(seq){
    var caccno = $("#caccno").val();
    var cauino = $("#cauino").val();
    var caid = $("#caid").val();
    var capassword = $("#capassword").val();
    var castate = $("#castate").val();

    if(caccno==null||caccno==""){
        swal({
            text: "골프장을 선택해주세요.",
            icon: "info"
        });
    }else if(cauino==null||cauino==""){
        swal({
            text: "사용자정보를 선택해주세요.",
            icon: "info"
        });
    }else if(caid==null||caid==""){
        swal({
            text: "아이디를 입력해주세요.",
            icon: "info"
        });
    }else if(capassword==null||capassword==""){
        swal({
            text: "비밀번호를 입력해주세요.",
            icon: "info"
        });
    }else{
        $('#load').show();
        $.ajax({
            url : "/uicc_idcheck_edit",
            data : {"cano":seq,"ccno" : caccno, "caid" : caid},
            type : "POST",
            success : function(result){
                if(result.save == "1"){ // 1:동일아이디 없음, 0: 동일있음
                        let sendData = {
                            'cano':seq,
                            'caccno':caccno,
                            'cauino':cauino,
                            'caid':caid,
                            'capassword':capassword,
                            'castate':castate
                        }
                        $.ajax({
                            url     : "/ccacount_editsave",
                            data    : sendData,
                            type    : "POST",
                            success : function(){
                                $('#load').hide();
                                swal({
                                    text: "수정완료.",
                                    icon: "success",
                                    closeOnClickOutside : false,
                                    button: "확인"
                                }).then(function(){
                                    location.href = "/UserInfoCCList";
                                });
                            }, error: function(request, status, error){
                                $('#load').hide();
                                    swal({
                                    text: "서버에 문제가 발생했습니다.",
                                    icon: "warning" //"info,success,warning,error" 중 택1
                                });
                            }
                        });
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

//function caUI(){
//    var selectedOption = document.querySelector("#cauino option:checked");
//    $("#caid").val(selectedOption.dataset.uiid);
//    $("#capassword").val(selectedOption.dataset.uipassword);
//}

function userinfoCClist(){ // 목록으로 이동
    $("#load").show();
    location.href="/UserInfoCCList";
}
