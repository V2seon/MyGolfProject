
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
                        document.getElementById('cctip').innerText = result.cctip[0];
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

function Ininfo(){
const ccname = document.getElementById("ccname").value;
const type = $('input[name=choice]:checked').val();
const id = document.getElementById("id").value;
const course = document.getElementById("course").value;
const startdate = document.getElementById("startdate").value;
const enddate = document.getElementById("enddate").value;
const choice = $('input[name=choice1]:checked').val();
//const pw = document.getElementById("pw").value;
const t1 = document.getElementById("t1").value;
const t2 = document.getElementById("t2").value;
//const c = document.getElementById("c").value;
const cc = document.getElementById("cc").value;

if(ccname === null || ccname === "") {
        swal({
            title: "골프장을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(type === null || type === "") {
        swal({
            title: "예약타입을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(id === null || id === ""){
        swal({
            title: "해당 골프장 아이디를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(startdate === null || startdate === ""){
        swal({
            title: "예약시작일를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(enddate === null || enddate === ""){
        swal({
            title: "예약종료일을 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}
else if(choice === null || choice === ""){
        swal({
            title: "예약일을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}
else if(t1 === null || t1 === ""){
        swal({
            title: "희망시간을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}
else if(t2 === null || t2 === ""){
        swal({
            title: "희망시간을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else{
let sendData = {
                "mountin" : ccname,
                "type" : type,
                "id" : id,
                "hope_c" : course,
                "startdate" : startdate,
                "enddate" : enddate,
                "choice" : choice,
                "hope_t1" : t1,
                "hope_t2" : t2,
                "hope_h" : cc
            }

    $.ajax({
        url      : "/SaveWaitRegisterInfo",
        data     : sendData,
        type     : "POST",
        success : function(result) {
            $('#load').hide();
            swal({
                    text: "예약완료.",
                    icon: "success" //"info,success,warning,error" 중 택1
                });
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
console.log(ccname);
console.log(type);
console.log(id);
console.log(course);
console.log(startdate);
console.log(enddate);
console.log(choice);
console.log(t1);
console.log(t2);
console.log(cc);

}
