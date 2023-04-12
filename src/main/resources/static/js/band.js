// 검색필터
function searching(db){
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

    if(db == 'bml'){
        var code = document.getElementById('code').value;
        var date = document.getElementById('date').value;
        var querydata = { "code" : code,  "date" : date,  "page" : myPageQuery.get('page'), "selectKey":myPageQuery.get('selectKey'),"titleText":myPageQuery.get('titleText')};
    }else{
        var querydata = { "page" : myPageQuery.get('page'), "selectKey":myPageQuery.get('selectKey'),"titleText":myPageQuery.get('titleText')};
    }


    if(db == 'bi'){
        $.ajax({
            url: "/Bandinfo_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bitable").replaceWith(fragment);
        });
    }else if(db == 'bl'){
        $.ajax({
            url: "/bandlog_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bltable").replaceWith(fragment);
        });
    }else if(db == 'blm'){
        $.ajax({
            url: "/bandlogmember_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#blmtable").replaceWith(fragment);
        });
    }else if(db == 'bm'){
        $.ajax({
            url: "/bandmember_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bmtable").replaceWith(fragment);
        });
    }else if(db == 'bml'){
        $.ajax({
            url: "/Bandmemberlist_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bmltable").replaceWith(fragment);
        });
    }
}

// 페이징
function paging(pageValue,db){
    const myPageQuery = new URLSearchParams(location.search);
    var titleText = myPageQuery.get('titleText');
    var selectKey = myPageQuery.get('selectKey');

    console.log(pageValue);
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

    if(db == 'bml'){
        var code = document.getElementById('code').value;
        var date = document.getElementById('date').value;
        var querydata = { "code" : code,  "date" : date,  "page" : pageValue, "selectKey":selectKey, "titleText":titleText};
    }else{
        var querydata = { "page" : pageValue, "selectKey":selectKey, "titleText":titleText};
    }

    if(db == 'bi'){
        $.ajax({
            url: "/Bandinfo_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bitable").replaceWith(fragment);
            $("#load").hide();
        });
    }else if(db == 'bl'){
        $.ajax({
            url: "/bandlog_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bltable").replaceWith(fragment);
            $("#load").hide();
        });
    }else if(db == 'blm'){
        $.ajax({
            url: "/bandlogmember_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#blmtable").replaceWith(fragment);
            $("#load").hide();
        });
    }else if(db == 'bm'){
        $.ajax({
            url: "/bandmember_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bmtable").replaceWith(fragment);
            $("#load").hide();
        });
    }else if(db == 'bml'){
        $.ajax({
            url: "/Bandmemberlist_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bmltable").replaceWith(fragment);
            $("#load").hide();
        });
    }
}












function GreetingSet(biseq){
    $('#load').show();
    location.href = "/Bandgreetinglist?no="+biseq+"";
}


function AllMember(blmbiseq,blmtodaydata){
 // 클릭시 seq에 맞는 날짜/밴드 멤버리스트 페이지로 이동
    $('#load').show();
    var regex = /[^0-9]/g;
    var get_date = blmtodaydata.replace(regex, "");
    console.log(blmbiseq+","+get_date)
    location.href = "/Bandmemberlist?no="+blmbiseq+"&date="+get_date+"";
//
//    let sendData = {
//        'biseq' : blmbiseq,
//        'date' : get_date
//    };
//    $.ajax({
//        url : '/Bandmemberlist',
//        data : sendData,
//        type : 'POST',
//        success : function(result){
//            $("#load").hide();
//
//        }
//    })
}



// 마우스 호버 기능
function view(members,check) { // 체크는 out, in, change
    $("#popupLayer").css('overflow','Visible');
    if(check == 'out'){
        for(var i=0; i<members.length; i++){
            $("#Omembers").css('display','block');
            $("#Imembers").css('display','none');
            $("#Cmembers").css('display','none');
            $("#Omembers").append(
                "<tr class='nickid'><td>"+members[i]+"</td></tr>"
            )
        }
    }else if(check == 'in'){
        for(var i=0; i<members.length; i++){
            $("#Omembers").css('display','none');
            $("#Imembers").css('display','block');
            $("#Cmembers").css('display','none');
            $("#Imembers").append(
                "<tr class='nickid'><td>"+members[i]+"</td></tr>"
            )
        }
    }else if(check == 'change'){
        for(var i=0; i<members.length; i++){
            $("#Omembers").css('display','none');
            $("#Imembers").css('display','none');
            $("#Cmembers").css('display','block');
            $("#Cmembers").append(
                "<tr class='nickid'><td>"+members[i]+"</td></tr>"
            )
        }
    }
};

