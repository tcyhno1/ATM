function validate() {
    var loginName = $("#loginName").val();
    var password = $("#password").val();

    if((!loginName)||(!password)){
        alert("内容不能有未填");
        return false;
    }

    if(isNaN(loginName)){
        alert("账号不是有效数字");
        return false;
    }
    if(isNaN(password)){
        alert("密码不是有效数字");
        return false;
    }
    return true;

}