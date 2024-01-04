document.addEventListener('DOMContentLoaded', function () {
    var registerForm = document.getElementById('registerForm');

    registerForm.addEventListener('submit', function (event) {
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        var valid = true;
        var errorMessage = '';
        // 简单的用户名和密码验证
        if (username.length < 3 || username.length > 20) {
            errorMessage += '用户名长度应在3到20个字符之间。\n';
            valid = false;
        }

        if (password.length < 6) {
            errorMessage += '密码长度至少为6个字符。\n';
            valid = false;
        }

        // 如果验证失败，阻止表单提交并显示错误消息
        if (!valid) {
            event.preventDefault();
            alert(errorMessage);
        }
    });
});
