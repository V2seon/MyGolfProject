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
        url: "/userinfoCCList_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#set").text("0");
        $("#intable").replaceWith(fragment);
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
        url: "/userinfoCCList_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#set").text(ppp.value);
        $("#intable").replaceWith(fragment);
    });
}


// CC계정 등록
function addccacount(){ // 계정등록 페이지로 이동
    $("#load").show();
    location.href="/ccacount_add";
}
// CC계정 수정
function uiccedit(no){ // 클릭시 계정정보 수정 가능
}
// CC계정 삭제
function uiccdel(no){ // 클릭시 확인팝업 뜨고 삭제 가능
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
