
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
    $('#load').show();
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
        $('#load').hide();
        $("#intable").replaceWith(fragment);
    });

}

function Waitinfoin(){
$('#load').show();
location.href="/WaitRegisterInfo"
}

function move(){
$('#load').show();
location.href="/Reservation"
}

function chCC(){
    $('#load').show();
    $('option.chcourse').remove();
    $('option.chid').remove();
    $('div.idlist').remove();
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
                        for(var i=0; i<result.ccid.length; i++){
                            $("#id").append(
                                "<option class='chid' value='"+result.ccid[i].caid+'/'+result.ccid[i].cano+"'>"+result.ccid[i].caid+"</option>"
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
const idlist = document.getElementsByName("ccid");
const course = document.getElementById("course").value;
const startdate = document.getElementById("startdate").value;
const enddate = document.getElementById("enddate").value;
const choice = $('input[name=choice1]:checked').val();
//const pw = document.getElementById("pw").value;
const t1 = document.getElementById("t1").value;
const t2 = document.getElementById("t2").value;
//const c = document.getElementById("c").value;
const cc = document.getElementById("cc").value;

var id = idlist[0].getAttribute('value');
for(var i=1; i<idlist.length; i++){
    id = id+"/"+idlist[i].getAttribute('value');
}

if(ccname === null || ccname === "") {
        swal({
            title: "골프장을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if($('input[name=choice]').is(":checked") === false) {
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
$('#load').show();
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
                }).then(function(){
                      location.href = "/Reservation";
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
}

function Editinfo(seq){
$('#load').show();
location.href="/ReservationModify/"+seq+""
}

function Delinfo(seq){
swal({
        title: "예약 종료",
          text: "해당 예약을 종료하시겠습니까?",
          icon: "warning",
          closeOnClickOutside : false,
          buttons : ["취소", "삭제"],
          dangerMode: true
    }).then((result) => {
    if(result){
        $('#load').show();
        const sendData = {
                    'seq' : seq
                    };
        $.ajax({
                url      : "/DelWaitReservation",
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
                            location.href = "/Reservation";
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

function Modifyinfo(seq){
const ccname = document.getElementById("ccname").value;
const type = $('input[name=choice]:checked').val();
const idlist = document.getElementsByName("ccid");
const course = document.getElementById("course").value;
const startdate = document.getElementById("startdate").value;
const enddate = document.getElementById("enddate").value;
const choice = $('input[name=choice1]:checked').val();
//const pw = document.getElementById("pw").value;
const t1 = document.getElementById("t1").value;
const t2 = document.getElementById("t2").value;
//const c = document.getElementById("c").value;
const cc = document.getElementById("cc").value;

var id = idlist[0].getAttribute('value');
for(var i=1; i<idlist.length; i++){
    id = id+"/"+idlist[i].getAttribute('value');
}

if(ccname === null || ccname === "") {
        swal({
            title: "골프장을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if($('input[name=choice]').is(":checked") === false) {
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
    $('#load').show();
let sendData = {
                "seq":seq,
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
        url      : "/EditWaitRegisterInfo",
        data     : sendData,
        type     : "POST",
        success : function(result) {
            $('#load').hide();
            swal({
                    text: "수정완료.",
                    icon: "success" //"info,success,warning,error" 중 택1
                }).then(function(){
                      location.href = "/Reservation";
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
}

function idplus(){
$('#load').show();
var id = document.getElementById("id").value;
var idlist = id.split("/");

var ccid = document.getElementsByName("ccid");
var cl = ccid.length;

var id = "";
for(var i=0; i<ccid.length; i++){
    id = id+"/"+ccid[i].innerText;
}

if(id.indexOf(idlist[0]) == -1){
$("#idlist").append(
                    "<div name='idlist' class='idlist' style='margin-bottom:10px;'><span class='ccid' name='ccid' value='"+idlist[1]+"'>"+idlist[0]+
                    "</span>&nbsp&nbsp<button onclick='delid(this)' class='btn btn-primary' style='margin:auto;'><a>삭제</a></button></div>"
                )
                $('#load').hide();
}else{
$('#load').hide();
swal({
        text: "해당 아이디는 이미 추가된 아이디입니다.",
        icon: "info",
        closeOnClickOutside : false
    })
}

const log = document.getElementById("idlist");
log.isScrollBottom = true;
log.addEventListener("scroll", (event) => {
  if (event.target.scrollHeight - event.target.scrollTop === event.target.clientHeight) {
    log.isScrollBottom = true;
  } else {
    log.isScrollBottom = false;
  }
});

if (log.isScrollBottom) {
    log.scrollTop = log.scrollHeight;
  }

}

function delid(con){
$('#load').show();
    var tagName = con.parentNode;
    tagName.remove();
    $('#load').hide();
}

