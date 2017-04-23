<%@ page contentType="text/html;charset=UTF-8" language="java" %>

</div>
</div>
<script>
function checkForm() {
    var value = $('#q').val();
    value = value.trim();
    if(value==''){
        alert('请输入关键字!');
        return false;
    }

    return true;
}
</script>
</body>
</html>
