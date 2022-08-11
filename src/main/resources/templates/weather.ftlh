<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/weather" method="post">
        <input type="text" name="cityNameReq" placeholder="Enter a city name"/>
        <button type="submit">Find out the weather</button>
    </form>

    <div>
        <#list weatherList as weather>
            City: ${weather.cityName}<br/>
            Temperature: ${weather.temperature}<br/>
            Creation Date: ${weather.creationDate}<br/><br/>
        </#list>
    </div>
</@c.page>
