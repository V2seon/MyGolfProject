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
    var querydata = { "page" : myPageQuery.get('page'), "selectKey":myPageQuery.get('selectKey'),"titleText":myPageQuery.get('titleText')};


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

    var querydata = { "page" : pageValue, "selectKey":selectKey, "titleText":titleText};

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
    }
}












function GreetingSet(biseq){
    $('#load').show();
    location.href = "/Bandgreetinglist?no="+biseq+"";
}


function AllMember(blmseq){
 // 클릭시 seq에 맞는 날짜/밴드 멤버리스트 페이지로 이동
}


