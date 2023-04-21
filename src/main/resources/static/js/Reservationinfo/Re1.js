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
        url: "/search_Reservation1",
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
        url: "/search_Reservation1",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
        $("#load").hide();
    });

}

function Defno(seq){
console.log(seq);
swal({
        title: "예약을 확정하시겠습니까?",
        icon: "success",
        closeOnClickOutside : false,
        buttons : ["취소", "확정"]
    }).then((result) => {
    if(result){
        $('#load').show();
        const sendData = {
                        'seq' : seq
                    };
        $.ajax({
                url      : "/clearrs",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();
                    swal({
                            text: "예약확정완료.",
                            icon: "success",
                            closeOnClickOutside : false,
                            button: "확인"
                        }).then(function(){
                            location.href = "/Reservation1";
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

function band(seq){
swal({
      title : "글을 작성하시겠습니까?.",
      closeOnClickOutside : false,
      icon: "success"
    }).then((result) => {
    if(result){
    $('#load').show();
        const data = {
                    'seq' : seq
                    };
        $.ajax({
                url : "/bandup",
                data : data,
                type : "POST",
                success : function (result){
                    $('#load').hide();
                    location.href="/Reservation1"
                },
                error : function (request,status,error){
                }
            });
    }
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
    $('#load').show();
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
                            location.href = "/Reservation1";
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
        url: "/search_Reservation1",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#set").text(ppp.value);
        $("#intable").replaceWith(fragment);
    });

}

function searchingall(){

    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

    if(titleText == null){
            titleText = "";
    }

    console.log(titleText);
    const params = {
        page: 0,
        selectKey: selectKey,
        titleText: ""
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
        $("#set").text("0");
        $("#intable").replaceWith(fragment);
    });

}

function infoin(){
$('#load').show();
location.href="/RegisterInfo"
}

function Saveinfo(){
    var cname = document.getElementById('ccname').value;
    var id = document.getElementById('id').value;
    var date = document.getElementById('date').value;
    var course = document.getElementById('course').value;
    var cancel = document.getElementById('time').value;
if(cname === null || cname === "") {
        swal({
            title: "골프장을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(id === null || id === "") {
        swal({
            title: "아이디를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(date === null || date === ""){
        swal({
            title: "예약티 시간를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(course === null || course === ""){
        swal({
            title: "코스정보를 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(cancel === null || cancel === ""){
        swal({
            title: "취소가능일을 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else{
swal({
        title: "예약 정보 등록",
          text: "예약 정보 등록하시겠습니까?",
          icon: "info",
          closeOnClickOutside : false,
          buttons : ["취소", "등록"]
    }).then((result) => {
        if(result){
            const sendData = {
                        'cname' : cname,
                        'id' : id,
                        'date' : date,
                        'course' : course,
                        'cancel' : cancel
            }
            $.ajax({
                            url      : "/SaveInfo",
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
                                        location.href = "/Reservation1";
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
}

function chCC(){
$('#load').show();
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
//                        document.getElementById('cctip').innerText = result.cctip[0];
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

function info(){
$('#load').show();
location.href="/Reservation1"
}

function view(seq) {
$('#load').show();
    const sendData = {
                        'seq' : seq
                }
    $.ajax({
                url      : "/golfpt",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();

                    for(var i=0; i<result.count.length; i++){
                        $("#golfptc").append(
                            "<tr class='nickid'><td>"+result.count[i].bgennickname+"</td></tr>"
                        )
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


};
//팝업 Close 기능
function close_pop3(flag) {
     $('#myModal3').hide();
     $('tr.nickid').remove();
};

function DelReservation(seq){
swal({
        title: "예약 연동 삭제",
          text: "해당 예약 연동을 삭제하시겠습니까?",
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
                url      : "/DelReservation",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();
                    swal({
                            text: "연동삭제완료.",
                            icon: "success",
                            closeOnClickOutside : false,
                            button: "확인"
                        }).then(function(){
                            location.href = "/Reservation1";
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

function ckcancel(e){
$('#load').show();
var seq = e.value;
var state = 0;
if(e.checked == false){
state =0;
}else if(e.checked == true){
state =1;
}
const sendData = {
                    'seq' : seq,
                    'state': state,
                    };
        $.ajax({
                url      : "/Updatecancelauto",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();
                },
                error:function(request,status,error){
                    $('#load').hide();

                }
            });
}

function chopt(e){
var seq = e.value;
var text = e.innerText;
var state = 0;
if(text == "대기"){
e.innerText = "밴올";
state = 1;
}else if(text == "밴올"){
e.innerText = "밴성";
state = 2;
}else if(text == "밴성"){
e.innerText = "양올";
state = 3;
}else if(text == "양올"){
e.innerText = "양완";
state = 4;
}else if(text == "양완"){
e.innerText = "취소";
state = 5;
}else if(text == "취소"){
e.innerText = "대기";
state = 0;
}
const sendData = {
                    'seq' : seq,
                    'state': state,
                    };
        $.ajax({
                url      : "/Updateopt2",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();
                },
                error:function(request,status,error){
                    $('#load').hide();

                }
            });
}