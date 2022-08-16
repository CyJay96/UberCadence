<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>

<@c.page>
    <h5>Find out the weather</h5>

    <div class="mt-3">
        <form action="/weather" method="post" enctype="multipart/form-data">
            <div class="mt-3">
                <input class="form-control" type="text" name="cityNameReq" placeholder="Enter a city name"/>
            </div>

            <div class="mt-3">
                <button type="submit" class="btn btn-outline-primary">Find</button>
            </div>
        </form>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list weatherPage.content as weather>
            <div class="col">
                <div class="card m-3">
                    <div class="m-2">
                        City: ${weather.cityName}<br/>
                        Temperature: ${weather.temperature} °C
                    </div>

                    <div class="card-footer text-muted container">
                        <div class="row">
                            <i>Creation Date: ${weather.creationDate}</i>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <div class="mt-10">No records</div>
        </#list>
    </div>

    <@p.pager url weatherPage />
</@c.page>
