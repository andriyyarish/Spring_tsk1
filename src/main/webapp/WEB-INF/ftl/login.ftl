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
        <#if error?has_content>
            <div class="alert alert-error" style="color: crimson; border: brown">
                Invalid username or password.
            </div>
        </#if>
            <legend>Please Login</legend>

        <#if logout?has_content>
            <div class="alert alert-error" style="color: green; border: brown">
                You have been logged out.
            </div>
        </#if>

            <legend>Please Login</legend>

        <#--<div th:if="${param.logout}" class="alert alert-success">-->
        <#--You have been logged out.-->
        <#--</div>-->
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
        </fieldset>
    </form>

</div>
</body>
</html>