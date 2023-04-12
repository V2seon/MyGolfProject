
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