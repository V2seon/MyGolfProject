// 검색필터
function searching(band){
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


    if(band == 'bi'){
        $.ajax({
            url: "/Bandinfo_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bitable").replaceWith(fragment);
        });
    }else if(band == 'bl'){
        $.ajax({
            url: "/bandlog_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#bltable").replaceWith(fragment);
        });
    }else if(band == 'blm'){
        $.ajax({
            url: "/bandlogmember_search",
            data: querydata,
            type:"POST",
        }).done(function (fragment) {
            $("#blmtable").replaceWith(fragment);
        });
    }else if(band == 'bm'){
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
function paging(pageValue){
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

    $.ajax({
        url: "/Countryclub_search",
        data: querydata,
        type:"POST",
    }).done(function (fragment) {
        $("#intable").replaceWith(fragment);
        $("#load").hide();
    });
}