function validate() {
    var toNum = $("#toNum").val();
    var money = $("#money").val();
    var studentName = $("#studentName").val();

    if ((!toNum)||(!money)||(!studentName)){
        alert("内容不能有未填");
        return false;
    }



    if(isNaN(money)){
        alert("金额不是有效数字");
        return false;
    }

    if (money<=0) {
        alert("金额必须为大于零的数");
        return false;
    }

    if(isNaN(toNum)){
        alert("卡号格式错误");
        return false;
    }
    return true;
}