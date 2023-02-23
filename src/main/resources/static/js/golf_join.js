function golfjoin(){
    const uid = $("#uid").val();
    const upw = $("#upw").val();
    const uname = $("#uname").val();
    const uphone = $("#uphone").val();
//    const sms = $("input[name='sms']:checked").val();
    const sms = $("#sms").val();
    console.log(sms);

    const data = {
        'uid' : uid,
        'upw' : upw,
        'uname' : uname,
        'uphone' : uphone,
        'sms' : sms
    };
    $.ajax({
        url : "/golfjoin",
        data : data,
        type : "POST",
        success : function (result){
            swal({
                    title: "회원가입이 완료되었습니다.",
                    icon: "success",
                    closeOnClickOutside : false,
                    button: "확인"
                }).then(function(){
                    location.href = "/";
                });
        },
        error : function (request,status,error){
            swal({
                    title: "서버에러",
                    icon: "warning",
                    button: "확인"
                });
        }
    });
}