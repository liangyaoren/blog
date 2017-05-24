<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    </div>
    <div class="row">
        <div class="col-md-12">
            <p style="text-align: center;color: white;background-color:#303643 !important">© copyright 粤ICP备16108162号-1</p>
        </div>
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
