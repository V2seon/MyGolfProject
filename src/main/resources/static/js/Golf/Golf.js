function sub(){
const ccname = document.getElementById("cc_name").value;
const year = document.getElementById("year").value;
const month = document.getElementById("month").value;
const day = document.getElementById("day").value;
const t1 = document.getElementById("t1").value;
const t2 = document.getElementById("t2").value;
const c = document.getElementById("c").value;
const cc = document.getElementById("cc").value;

console.log(ccname);
console.log(year);
console.log(month);
console.log(day);
console.log(t1);
console.log(t2);
console.log(c);
console.log(cc);

let sendData = {
                "mountin" : ccname,
                "hope_y" : year,
                "hope_m" : month,
                "hope_d" : day,
                "hope_t1" : t1,
                "hope_t2" : t2,
                "hope_c" : c,
                "hope_h" : cc
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