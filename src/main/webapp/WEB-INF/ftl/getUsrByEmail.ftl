<h2>Get user by email </h2>


<form name="emailform" id="emailform" action="getUserByEmail" method="post">
    <input type="text" name="email" id="email" value="andyyarish@gmail.com"/>
    <input type="submit" value="search"/>
</form>


    <#--<#if ${user?length} > 0 "">-->
        <#--<p>results of ${email}:</p> <br>  ${user}-->
        <#--<#else>-->
        <#--sorry, there is no such user with email ($email)-->
    <#--</#if>-->

results of email ${email?if_exists}: ${user?if_exists}

