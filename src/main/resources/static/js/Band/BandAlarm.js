function inAlarm(){
location.href="BandAlarmRegister";
}

function Alarmgo(){
location.href="Bandalarm";
}

function SaveAlarm(){
const bandname = document.getElementById("bandname").value;
const alarmtype = $('input[name=choice]:checked').val();
const temname = document.getElementById("temname").value;
const alarmtime = document.getElementById("alarmtime").value;
//const email = document.getElementById("email").checked;
//const sms = document.getElementById("sms").checked;
//const band = document.getElementById("band").checked;
//const temcon = document.getElementById("temcon").value;
const alarmstate = $('input[name=choice1]:checked').val();

console.log(bandname);
console.log(temname);
console.log(alarmtime);
console.log(alarmtype);
console.log(alarmstate);
}


function checkReg(event) {
  const regExp = /[^0-9]/g; // 숫자만 허용
//  const regExp = /[^ㄱ-ㅎ|가-힣]/g; // 한글만 허용
  const del = event.target;
  if (regExp.test(del.value)) {
    del.value = del.value.replace(regExp, '');
  }
  if(window.event.keyCode == 13){
    kakaophnum();
  }
};

function kakaophnum(){
var kakaophnum = document.getElementById("kakaophnum").value;
if(kakaophnum != null && kakaophnum != ""){
    const log = document.getElementById("kakaophlist");
    log.isScrollBottom = true;
    log.addEventListener("scroll", (event) => {
      if (event.target.scrollHeight - event.target.scrollTop === event.target.clientHeight) {
        log.isScrollBottom = true;
      } else {
        log.isScrollBottom = false;
      }
    });

    $("#kakaophlist").append(
                        "<div style='margin-bottom:10px;'><span name='kaphnum'>"+kakaophnum+
                        "</span>&nbsp&nbsp<button onclick='delkakao(this)' class='btn btn-primary' style='margin:auto;'><a>삭제</a></button></div>"
                    )
    document.getElementById("kakaophnum").value="";

    if (log.isScrollBottom) {
        log.scrollTop = log.scrollHeight;
      }

}else{
    swal({
            text: "휴대폰 번호를 입력하세요",
            icon: "info",
            button: "확인"
        })
}
}

function delkakao(con){
    var tagName = con.parentNode;
    tagName.remove();
}

function emailtext(){
var emailtext = document.getElementById("emailtext").value;
if(emailtext != null && emailtext != ""){
    const log = document.getElementById("emaillist");
    log.isScrollBottom = true;
    log.addEventListener("scroll", (event) => {
      if (event.target.scrollHeight - event.target.scrollTop === event.target.clientHeight) {
        log.isScrollBottom = true;
      } else {
        log.isScrollBottom = false;
      }
    });

    $("#emaillist").append(
                        "<div style='margin-bottom:10px;'><span name='emailnum'>"+emailtext+
                        "</span>&nbsp&nbsp<button onclick='delemail(this)' class='btn btn-primary' style='margin:auto;'><a>삭제</a></button></div>"
                    )
    document.getElementById("emailtext").value="";

    if (log.isScrollBottom) {
        log.scrollTop = log.scrollHeight;
      }

}else{
    swal({
            text: "이메일 주소를 입력하세요",
            icon: "info",
            button: "확인"
        })
}
}

function delemail(con){
    var tagName = con.parentNode;
    tagName.remove();
}

function onkeyemail(event) {
  if(window.event.keyCode == 13){
    emailtext();
  }
};