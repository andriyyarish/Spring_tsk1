<h1>Error page</h1>
    ${ex.message}
<br>
<li><a href="/epam")">go to homepage</a></li>
<hr>
<br>

<pre>
    ${ex.StackTrace?if_exists}
</pre>