function Notice(){
    location.href="/Notice";
}

function inNotice(){
    const title = document.getElementById('title');
    const content = document.getElementById('content');

    let sendData = {
        "title" : title,
        "content" : content
    }

    $.ajax({
                url      : "/inNotice",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    swal({
                          title : "등록되었습니다.",
                          closeOnClickOutside : false,
                          icon: "success",
                        }).then(function(){
                            location.href = "/Notice";
                        });
                },
                error:function(request,status,error){
                }
            });
}

function ModifyNoticego(){
    const seq = document.getElementById('seq');

    let sendData = {
        "seq" : seq
    }

    $.ajax({
                url      : "/Noticego",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    location.href = "/NoticeModifygo";
                },
                error:function(request,status,error){
                }
            });
}

function EditNotice(){
    const seq = document.getElementById('seq');
    const title = document.getElementById('title');
    const content = document.getElementById('content');

    let sendData = {
        "seq" : seq,
        "title" : title,
        "content" : content
    }

    $.ajax({
                url      : "/EditNotice",
                data     : sendData,
                type     : "POST",
                success : function(result) {
                    swal({
                          title : "수정되었습니다.",
                          closeOnClickOutside : false,
                          icon: "success",
                        }).then(function(){
                            location.href = "/Notice";
                        });
                },
                error:function(request,status,error){
                }
            });
}