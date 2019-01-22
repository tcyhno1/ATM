
function validate() {
    var money = $("#money").val();
    // if(money==null || money==''  || money==undefined)
    if(!money){
        alert("金额为空");
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
    return true;
}


function submitForm() {
    var v = validate();
    if (v){
        //$("#form").submit();
        form.submit();
    }
}