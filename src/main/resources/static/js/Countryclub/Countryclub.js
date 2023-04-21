function CC(){
    $('#load').show();
    location.href = "/Countryclub";
}

function inCC(){
    $('#load').show();
    location.href = "/CountryclubRegister";
}

function ModifyCCgo(seq){
    $('#load').show();
let sendData = {
        "seq" : seq
    }

$.ajax({
        url      : "/Countryclubgo",
        data     : sendData,
        type     : "GET",
        success : function(result) {
            $('#load').hide();
            location.href = "/CountryclubModify";
        },
        error:function(request,status,error){
        }
    });
}

function SaveCC(){
    const name = document.getElementById('ccname').value;
    const address = document.getElementById('ccaddress').value;
    const url = document.getElementById('ccurl').value;
    const day = document.getElementById('day').value;
    const opentime = document.getElementById('time').value;
    const cctip = document.getElementById('cctip').value;
    var retype = $("input[name='retype']:checked").val();
    var possible = $("input[name='possible']:checked").val();
if(name === null || name === "") {
        swal({
            title: "골프장 이름을 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(address === null || address === "") {
        swal({
            title: "골프장 주소를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(url === null || url === "") {
        swal({
            title: "골프장 링크를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(day === null || day === "") {
        swal({
            title: "취소 가능일을 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(opentime === null || opentime === "") {
        swal({
            title: "오픈시간을 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(retype === null || retype === "") {
        swal({
            title: "예약정보타입을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(possible === null || possible === "") {
        swal({
            title: "예약가능여부를 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else{
    $('#load').show();
    let sendData = {
        "name" : name,
        "address" : address,
        "url" : url,
        "day" : day,
        "opentime" : opentime,
        "retype" : retype,
        "possible" : possible,
        "cctip" : cctip
    }
    $.ajax({
                url      : "/SaveCC",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    $('#load').hide();
                    swal({
                          title : "등록되었습니다.",
                          closeOnClickOutside : false,
                          icon: "success",
                        }).then(function(){
                            location.href = "/Countryclub";
                        });
                },
                error:function(request,status,error){
                }
            });
}
}


function DelCCgo(seq){
swal({
  title: "CC정보 삭제",
  text: "해당 CC정보를 삭제하시겠습니까?",
  icon: "warning",
  closeOnClickOutside : false,
  buttons : ["취소", "삭제"],
  dangerMode: true
}).then((result) => {
  if (result) {
        $('#load').show();
        let sendData = {
            "seq" : seq
        };
        $.ajax({
            url : "/DelCC",
            data : sendData,
            type : "POST",
            success : function(result){
                $('#load').hide();
                swal({
                      title : "삭제되었습니다.",
                      closeOnClickOutside : false,
                      icon: "success",
                    }).then(function(){
                        location.href = "/Countryclub";
                    });
            },
            error: function (e) {
            }
        });
  }
});
}

function EditCC(seq){
    const name = document.getElementById('ccname').value;
    const address = document.getElementById('ccaddress').value;
    const url = document.getElementById('ccurl').value;
    const day = document.getElementById('day').value;
    const opentime = document.getElementById('time').value;
    var retype = $("input[name='retype']:checked").val();
    var possible = $("input[name='possible']:checked").val();
    const cctip = document.getElementById('cctip').value;
if(name === null || name === "") {
        swal({
            title: "골프장 이름을 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(address === null || address === "") {
        swal({
            title: "골프장 주소를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(url === null || url === "") {
        swal({
            title: "골프장 링크를 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(day === null || day === "") {
        swal({
            title: "취소 가능일을 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(opentime === null || opentime === "") {
        swal({
            title: "오픈시간을 입력하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(retype === null || retype === "") {
        swal({
            title: "예약정보타입을 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else if(possible === null || possible === "") {
        swal({
            title: "예약가능여부를 선택하세요.",
            icon: "info",
            button: "확인"
        });
        return false;
}else{
        $('#load').show();
        let sendData = {
            "seq" : seq,
            "name" : name,
            "address" : address,
            "url" : url,
            "day" : day,
            "opentime" : opentime,
            "retype" : retype,
            "possible" : possible,
            "cctip" : cctip
        }

        $.ajax({
                    url      : "/EditCC",
                    data     : sendData,
                    type     : "POST",
                    success : function(result) {
                            $('#load').hide();
                        swal({
                              title : "수정되었습니다.",
                              closeOnClickOutside : false,
                              icon: "success",
                            }).then(function(){
                                location.href = "/Countryclub";
                            });
                    },
                    error:function(request,status,error){
                    }
                });
}
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
        url: "/Countryclub_search",
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
        url: "/Countryclub_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
        $("#load").hide();

    });

}