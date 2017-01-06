
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

    <fieldset>
        <legend>Add Event</legend>
        <form name="event" action="events" method="post">
            EventName: <input type="text" name="name"/> <br/>
            EventRating: <select name="eventRating">
            <option value="LOW">Low</option>
            <option value="MEDIUM">Medium</option>
            <option value="HIGH">High</option>
        </select> <br/>
            EventPrice: <input type="number" name="basePrice" min="1"/> <br/>
            EventDate: <input type="date" name="date"/> <br/>
            Auditorium <select name="auditorium">
        <#list model["auditoriumsList"] as als>
            <option>${als.name}</option>
        </#list>
        </select> <br/>
            <input type="submit" value="   Save   "/>
        </form>
    </fieldset>

    <br/>
    <legend>Assign auditorium to event</legend>
    <form name="event" action="assignAuditoriumToEvent" method="post">
        EventName: <select name="event">
    <#list model["eventList"] as els>
        <option>${els.name}</option>
    </#list>
        <br/>
    </select>
        AuditoriumName: <select name="auditorium">
    <#list model["auditoriumsList"] as als>
        <option>${als.name}</option>
    </#list>
        <br/>
    </select>
        Date <input type="date" name="date"><br/>
        <input type="submit" value="Save">
    </form>
    <br/>
    <div>
        <table class="eventData" style="float: left;">
            <caption>Events list</caption>
            <tr>
                <th>Name</th>
                <th>Rating</th>
                <th>Price</th>
                <th>Date</th>
                <th>Auditoriums</th>
            </tr>
        <#list model["eventList"] as event>
            <tr>
                <td>${event.name}</td>
                <td>${event.eventRating!"UNKOWN"}</td>
                <td>${event.basePrice}</td>
                <td>${event.date?if_exists}</td>
                <td>Auditoriums</td>
            </tr>
        </#list>
        </table>

        <table class="auditoriumsList" style="display: inline-block">
            <caption>Auditoriums list</caption>
            <tr>
                <th>Name</th>
                <th>Adress</th>
                <th>Seats</th>
                <th>VipSeats</th>
            </tr>
        <#list model["auditoriumsList"] as audt>
            <tr>
                <td>${audt.name}</td>
                <td>${audt.adress}</td>
                <td>${audt.seats}</td>
                <td>Vip seats</td><#--<td>${audt.vipSeats}</td>-->
            </tr>
        </#list>
        </table>
    </div>
</div>
</body>
</html>