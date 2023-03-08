function sub(){
const ccname = document.getElementById("cc_name").value;
const type = $('input[name=choice]:checked').val();
const startdate = document.getElementById("startdate").value;
const enddate = document.getElementById("enddate").value;
const id = document.getElementById("id").value;
const pw = document.getElementById("pw").value;
const t1 = document.getElementById("t1").value;
const t2 = document.getElementById("t2").value;
const c = document.getElementById("c").value;
const cc = document.getElementById("cc").value;


console.log(ccname);
console.log(type);
console.log(startdate);
console.log(enddate);
console.log(id);
console.log(pw);
console.log(t1);
console.log(t2);
console.log(c);
console.log(cc);

let sendData = {
                "mountin" : ccname,
                "startdate" : startdate,
                "enddate" : enddate,
                "id" : id,
                "pw" : pw,
                "hope_t1" : t1,
                "hope_t2" : t2,
                "hope_c" : c,
                "hope_h" : cc,
                "type" : type
            }

    $.ajax({
        url      : "/golftest3",
        data     : sendData,
        type     : "POST",
        success : function(result) {
            $('#load').hide();
            swal({
                    text: "예약완료.",
                    icon: "success" //"info,success,warning,error" 중 택1
                });
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

function move(seq){
console.log(seq);

if(seq == 17){
    location.href="/formRI17";
}else if(seq == 40){
    location.href="/formRI40";
}
}

function test(){
const year = document.getElementById("year").value;
console.log(year);
}