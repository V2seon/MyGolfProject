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
        const data = {
                    'seq' : seq
                    };
        $.ajax({
                url : "/bandup",
                data : data,
                type : "POST",
                success : function (result){
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
    console.log(ppp.value)
    var titleText = $('#titleText').val();
    var selectKey = $('#selectKey').val();

    if(titleText == null){
            titleText = "";
    }

    console.log(titleText);
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
        $("#intable").replaceWith(fragment);
    });

}

function infoin(){
location.href="/RegisterInfo"
}


function Saveinfo(){
    var cname = document.getElementById('ccname').value;
    var id = document.getElementById('id').value;
    var date = document.getElementById('date').value;
    var course = document.getElementById('course').value;
    var cancel = document.getElementById('time').value;
if(cname.value === null || cname.value === "") {
        swal({
            title: "골프장을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(id.value === null || id.value === "") {
        swal({
            title: "아이디를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(date.value === null || date.value === ""){
        swal({
            title: "예약티 시간를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(course.value === null || course.value === ""){
        swal({
            title: "코스정보를 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(cancel.value === null || cancel.value === ""){
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

function info(){
location.href="/Reservation1"
}