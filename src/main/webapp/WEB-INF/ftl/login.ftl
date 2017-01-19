<html>
<head><title>EPAM university home work</title>
<body>
<div id="header">
    <H2>
        <a href="http://localhost:9093/epam/index.html"><img height="37" width="236" border="0px"
                                                             src="https://s.dou.ua/storage-files/13116257_10209525941449514_4807616636600582763_o.jpg"
                                                             align="left"/></a>
        Epam university
    </H2>
</div>

<div id="content">
    <form name="f" action="login" method="post">
        <fieldset>

            <legend>Please Login</legend>

        <#--<div th:if="${param.logout}" class="alert alert-success">-->
        <#--You have been logged out.-->
        <#--</div>-->
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>

            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>

            <label for="remember-me">Remember Me ?</label>
            <input type="checkbox" id="remember-me" value="true">

            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
        </fieldset>
    </form>

</div>
<#if error??>
<div class="alert alert-error" style="color: crimson; border: brown">
${error}
</div>
</#if>

<#if logout??>
<div class="alert alert-error" style="color: green; border: brown">
${logout}
</div>
</#if>

</body>
</html>