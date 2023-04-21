function inTem(){
$('#load').show();
location.href="/BandTemplateRegister";
}
function Temgo(){
$('#load').show();
location.href="/Bandtemplate";
}

function SaveTem(){
const temcode = document.getElementById("temcode").value;
const temname = document.getElementById("temname").value;
const kakao = document.getElementById("kakao").checked;
const email = document.getElementById("email").checked;
const sms = document.getElementById("sms").checked;
const band = document.getElementById("band").checked;
const temcon = document.getElementById("temcon").value;

const ft = [kakao, email, sms, band];

for(var i=0; i<ft.length; i++){
if(ft[i] == false){ft[i] = 0;}else {ft[i] = 1;}
}
let sendData = {
    "temcode" : temcode,
    "temname" : temname,
    "kakao" : ft[0],
    "email" : ft[1],
    "sms" : ft[2],
    "band" : ft[3],
    "temcon" : temcon
}
$.ajax({
            url      : "/inBandTemplate",
            data     : sendData,
            type     : "POST",
            success : function(result) {
                if(result.Result == "1"){
                     swal({
                           text: "등록완료.",
                           icon: "success" //"info,success,warning,error" 중 택1
                     }).then(function(){
                           location.href = "/Bandtemplate";
                     })
                }else if(result.Result == "0"){
                    swal({
                        text: "해당 템플릿코드는 이미 사용중입니다.",
                        icon: "error"
                    }).then(function(){
                        $('#temcode').focus();
                    })
                }
            },
            error:function(request,status,error){
            }
        });
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
        url: "/search_Bandtemplate",
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
        url: "/search_Bandtemplate",
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

function EditTem(seq){
location.href="/BttemModify/"+seq+""
//console.log(seq);
//    let sendData = {
//            "seq" : seq
//        }
//
//        $.ajax({
//            url      : "/Bttemgo",
//            data     : sendData,
//            type     : "GET",
//            success : function(result) {
//                $('#load').hide();
//                location.href="/BttemModify"
//            },
//            error:function(request,status,error){
//                $('#load').hide();
//            }
//        });
}

function EditTemuse(e){

var usestate = 0;
if(e.checked == false){
usestate = 0;
}else{
usestate = 1;
}
const seq = e.value;

let sendData = {
        "seq" : seq,
        "usestate" : usestate
    }

    $.ajax({
        url      : "/EditBandTemuse",
        data     : sendData,
        type     : "POST",
        success : function(result) {
            $('#load').hide();
            if(e.checked == false){
                document.getElementById("Bts"+seq).innerText="대기";

            }else{
                document.getElementById("Bts"+seq).innerText="사용";
            }
//            location.href="/BttemModify"
        },
        error:function(request,status,error){
            $('#load').hide();
        }
    });
}

function ModifyTem(seq){
const temcode = document.getElementById("temcode").value;
const temname = document.getElementById("temname").value;
const kakao = document.getElementById("kakao").checked;
const email = document.getElementById("email").checked;
const sms = document.getElementById("sms").checked;
const band = document.getElementById("band").checked;
const temcon = document.getElementById("temcon").value;
const ft = [kakao, email, sms, band];

for(var i=0; i<ft.length; i++){
if(ft[i] == false){ft[i] = 0;}else {ft[i] = 1;}
}
let sendData = {
    "seq":seq,
    "temcode" : temcode,
    "temname" : temname,
    "kakao" : ft[0],
    "email" : ft[1],
    "sms" : ft[2],
    "band" : ft[3],
    "temcon" : temcon
}

    $.ajax({
                url      : "/EditBandTemplate",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    if(result.Result == "1"){
                         swal({
                               text: "수정완료.",
                               icon: "success" //"info,success,warning,error" 중 택1
                         }).then(function(){
                               location.href = "/Bandtemplate";
                         })
                    }else if(result.Result == "0"){
                        swal({
                            text: "해당 템플릿코드는 이미 사용중입니다.",
                            icon: "error"
                        }).then(function(){
                            $('#temcode').focus();
                        })
                    }
                },
                error:function(request,status,error){
                }
            });
}

function checkReg(event) {
  const regExp = /[|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g; // 숫자와 영문자만 허용
//  const regExp = /[^ㄱ-ㅎ|가-힣]/g; // 한글만 허용
  const del = event.target;
  if (regExp.test(del.value)) {
    del.value = del.value.replace(regExp, '');
  }
};