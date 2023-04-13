function inAlarm(){
location.href="BandAlarmRegister";
}

function Alarmgo(){
location.href="/Bandalarm";
}

function SaveAlarm(){
const bandname = document.getElementById("bandname").value;
const alarmtype = $('input[name=choice]:checked').val();
const temname = document.getElementById("temname").value;
const alarmtime = document.getElementById("alarmtime").value;
const kaphnum = document.getElementsByName("kaphnum");
const emailnum = document.getElementsByName("emailnum");
const smsphnum = document.getElementsByName("smsphnum");
const alarmstate = $('input[name=choice1]:checked').val();
const bandch = document.getElementById("bandch").checked;
var band = 0;
if(bandch == false){
band = 0;
}else{
band = 1;
}
var kaphlist = "";
var emphlist = "";
var smphlist = "";
for(var i=0; i<kaphnum.length; i++){
    kaphlist = kaphlist+"/"+kaphnum[i].innerText;
}
for(var i=0; i<emailnum.length; i++){
    emphlist = emphlist+"/"+emailnum[i].innerText;
}
for(var i=0; i<smsphnum.length; i++){
    smphlist = smphlist+"/"+smsphnum[i].innerText;
}

const sendData = {
            'bandname' : bandname,
            'alarmtype' : alarmtype,
            'temname' : temname,
            'alarmtime' : alarmtime,
            'kaphlist' : kaphlist,
            'kaphcount' : kaphnum.length,
            'emphlist' : emphlist,
            'emphcount' : emailnum.length,
            'smphlist' : smphlist,
            'smphcount' : smsphnum.length,
            'bandch' : band,
            'alarmstate' : alarmstate
        }
$.ajax({
            url      : "/inBandalarm",
            data     : sendData,
            type     : "POST",
            success : function(result) {
                 swal({
                       text: "등록완료.",
                       icon: "success" //"info,success,warning,error" 중 택1
                 }).then(function(){
                       location.href = "/Bandalarm";
                 })
            },
            error:function(request,status,error){
            }
        });

}

function checkReg(event) {
  const regExp = /[^0-9]/g; // 숫자만 허용
//  const regExp = /[^ㄱ-ㅎ|가-힣]/g; // 한글만 허용
  const del = event.target;
  if (regExp.test(del.value)) {
    del.value = del.value.replace(regExp, '');
  }
  if(window.event.keyCode == 13){
    kakaophnum();
  }
};

function kakaophnum(){
var kakaophnum = document.getElementById("kakaophnum").value;
if(kakaophnum != null && kakaophnum != ""){
    const log = document.getElementById("kakaophlist");
    log.isScrollBottom = true;
    log.addEventListener("scroll", (event) => {
      if (event.target.scrollHeight - event.target.scrollTop === event.target.clientHeight) {
        log.isScrollBottom = true;
      } else {
        log.isScrollBottom = false;
      }
    });

    $("#kakaophlist").append(
                        "<div name='kadiv' style='margin-bottom:10px;'><span name='kaphnum'>"+kakaophnum+
                        "</span>&nbsp&nbsp<button onclick='delkakao(this)' class='btn btn-primary' style='margin:auto;'><a>삭제</a></button></div>"
                    )
    document.getElementById("kakaophnum").value="";

    if (log.isScrollBottom) {
        log.scrollTop = log.scrollHeight;
      }

}else{
    swal({
            text: "휴대폰 번호를 입력하세요",
            icon: "info",
            button: "확인"
        })
}
}

function delkakao(con){
    var tagName = con.parentNode;
    tagName.remove();
}

function emailtext(){
var emailtext = document.getElementById("emailtext").value;
if(emailtext != null && emailtext != ""){
    const log = document.getElementById("emaillist");
    log.isScrollBottom = true;
    log.addEventListener("scroll", (event) => {
      if (event.target.scrollHeight - event.target.scrollTop === event.target.clientHeight) {
        log.isScrollBottom = true;
      } else {
        log.isScrollBottom = false;
      }
    });

    $("#emaillist").append(
                        "<div name='emdiv' style='margin-bottom:10px;'><span name='emailnum'>"+emailtext+
                        "</span>&nbsp&nbsp<button onclick='delemail(this)' class='btn btn-primary' style='margin:auto;'><a>삭제</a></button></div>"
                    )
    document.getElementById("emailtext").value="";

    if (log.isScrollBottom) {
        log.scrollTop = log.scrollHeight;
      }

}else{
    swal({
            text: "이메일 주소를 입력하세요",
            icon: "info",
            button: "확인"
        })
}
}

function delemail(con){
    var tagName = con.parentNode;
    tagName.remove();
}

function onkeyemail(event) {
  if(window.event.keyCode == 13){
    emailtext();
  }
};

function checkReg1(event) {
  const regExp = /[^0-9]/g; // 숫자만 허용
//  const regExp = /[^ㄱ-ㅎ|가-힣]/g; // 한글만 허용
  const del = event.target;
  if (regExp.test(del.value)) {
    del.value = del.value.replace(regExp, '');
  }
  if(window.event.keyCode == 13){
    smsphnum();
  }
};

function smsphnum(){
var smsphnum = document.getElementById("smsphnum").value;
if(smsphnum != null && smsphnum != ""){
    const log = document.getElementById("smsphlist");
    log.isScrollBottom = true;
    log.addEventListener("scroll", (event) => {
      if (event.target.scrollHeight - event.target.scrollTop === event.target.clientHeight) {
        log.isScrollBottom = true;
      } else {
        log.isScrollBottom = false;
      }
    });

    $("#smsphlist").append(
                        "<div name='smdiv' style='margin-bottom:10px;'><span name='smsphnum'>"+smsphnum+
                        "</span>&nbsp&nbsp<button onclick='delsms(this)' class='btn btn-primary' style='margin:auto;'><a>삭제</a></button></div>"
                    )
    document.getElementById("smsphnum").value="";

    if (log.isScrollBottom) {
        log.scrollTop = log.scrollHeight;
      }

}else{
    swal({
            text: "휴대폰 번호를 입력하세요",
            icon: "info",
            button: "확인"
        })
}
}

function delsms(con){
    var tagName = con.parentNode;
    tagName.remove();
}

function chtem(){
    var temname = document.getElementById('temname').value;
    document.getElementById("bandch").checked=false;
    const sendData = {
                        'seq' : temname
                    }
    $.ajax({
                url      : "/chTem",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();
                    if(result.tem.btkakaostate == 0){
                       document.getElementById("kb").style.display= 'none';
                       var kadiv = document.getElementsByName("kadiv");
                       for(var i=0; i<kadiv.length; i++){
                           console.log("순서: " +i);
                           console.log("길이: " +kadiv.length);
                           kadiv[i].remove();
                       }
                    }else if(result.tem.btkakaostate == 1){
                       document.getElementById("kb").style.display= 'block';
                    }
                    if(result.tem.btemailstate == 0){
                       document.getElementById("eb").style.display= 'none';
                       var emdiv = document.getElementsByName("emdiv");
                       for(var i=0; i<emdiv.length; i++){
                            console.log(emdiv.length);
                           emdiv[i].remove();
                       }
                    }else if(result.tem.btemailstate == 1){
                       document.getElementById("eb").style.display= 'block';
                    }
                    if(result.tem.btsmsstate == 0){
                       document.getElementById("sb").style.display= 'none';
                       var smdiv = document.getElementsByName("smdiv");
                       for(var i=0; i<smdiv.length; i++){
                                                   console.log(smdiv.length);
                           smdiv[i].remove();
                       }
                    }else if(result.tem.btsmsstate == 1){
                       document.getElementById("sb").style.display= 'block';
                    }
                    if(result.tem.btbandstate == 0){
                       document.getElementById("bb").style.display= 'none';
                    }else if(result.tem.btbandstate == 1){
                       document.getElementById("bb").style.display= 'block';
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
        url: "/search_Bandalarm",
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
        url: "/search_Bandalarm",
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

function EditAl(seq){
location.href="/EditAl/"+seq+""
}

function EditAlarm(seq){
const bandname = document.getElementById("bandname").value;
const alarmtype = $('input[name=choice]:checked').val();
const temname = document.getElementById("temname").value;
const alarmtime = document.getElementById("alarmtime").value;
const kaphnum = document.getElementsByName("kaphnum");
const emailnum = document.getElementsByName("emailnum");
const smsphnum = document.getElementsByName("smsphnum");
const alarmstate = $('input[name=choice1]:checked').val();
const bandch = document.getElementById("bandch").checked;
var band = 0;
if(bandch == false){
band = 0;
}else{
band = 1;
}
var kaphlist = "";
var emphlist = "";
var smphlist = "";
for(var i=0; i<kaphnum.length; i++){
    kaphlist = kaphlist+"/"+kaphnum[i].innerText;
}
for(var i=0; i<emailnum.length; i++){
    emphlist = emphlist+"/"+emailnum[i].innerText;
}
for(var i=0; i<smsphnum.length; i++){
    smphlist = smphlist+"/"+smsphnum[i].innerText;
}

const sendData = {
            'seq':seq,
            'bandname' : bandname,
            'alarmtype' : alarmtype,
            'temname' : temname,
            'alarmtime' : alarmtime,
            'kaphlist' : kaphlist,
            'kaphcount' : kaphnum.length,
            'emphlist' : emphlist,
            'emphcount' : emailnum.length,
            'smphlist' : smphlist,
            'smphcount' : smsphnum.length,
            'bandch' : band,
            'alarmstate' : alarmstate
        }
$.ajax({
            url      : "/EditBandalarm",
            data     : sendData,
            type     : "POST",
            success : function(result) {
                 swal({
                       text: "수정완료.",
                       icon: "success" //"info,success,warning,error" 중 택1
                 }).then(function(){
                       location.href = "/Bandalarm";
                 })
            },
            error:function(request,status,error){
            }
        });

}

function DelAl(seq){
swal({
        title: "연동 설정 삭제",
          text: "해당 연동 설정을 삭제하시겠습니까?",
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
                url      : "/DelAlarm",
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
                            location.href = "/Bandalarm";
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