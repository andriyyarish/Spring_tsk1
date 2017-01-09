<h1>Error page</h1>
    ${ex.message?if_exists}
<br>
<li><a href="/epam")">go to homepage</a></li>
<hr>
<br>

<pre>
    ${ex.StackTrace?if_exists}
</pre>

<pre>
    ${message?if_exists}
</pre>