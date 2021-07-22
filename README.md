# *FAMOUS QUOTES REST API*

### This repository is an example of RESTful API written in Java and Spring Boot and was prepared for the purpose of some recruitment process.
### It enables to find add, update and delete quotes of famos or infamous people.

Endpoints are not secured by Spring Security nor by certificates, datasource used for this demonstration project is embedded H2 database.

### TO RUN APPLICATION:

- Go to project directory and run command: `./gradlew bootrun` 
  
    **OR**
  
- Build project executable jar package using: `./gradlew clean build jar -x test`
- Go to `build/libs` directory and run: `java -jar famous-qoutes-0.0.1-SNAPSHOT.jar`

### REST API DOCS:

###Quote Controller

### findAllUsingGET


```shell
# You can also use wget
curl -X GET /localhost:8080/api/quotes \
  -H 'Accept: */*'

```

```http
GET /localhost:8080/api/quotes HTTP/1.1

Accept: */*

```

`GET /api/quotes`

*findAll*

> 200 Response

<h3 id="findallusingget-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[CollectionModel_EntityModel_QuoteJson_](#schemacollectionmodel_entitymodel_quotejson_)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

### addUsingPOST

<a id="opIdaddUsingPOST"></a>

```shell
# You can also use wget
curl -X POST /localhost:8080/api/quotes \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

```http
POST /localhost:8080/api/quotes HTTP/1.1

Content-Type: application/json
Accept: */*

```



`POST /api/quotes`

*add*

```json
{
  "author": {
    "firstName": "string",
    "id": 0,
    "lastName": "string"
  },
  "content": "string",
  "id": 0
}
```

<h3 id="addusingpost-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[QuoteJson](#schemaquotejson)|true|newQuoteJson|


> 200 Response

<h3 id="addusingpost-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<h3 id="addusingpost-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

### findByIdUsingGET

<a id="opIdfindByIdUsingGET"></a>


```shell
# You can also use wget
curl -X GET /localhost:8080/api/quotes/{id} \
  -H 'Accept: */*'

```

```http
GET /localhost:8080/api/quotes/{id} HTTP/1.1

Accept: */*

```

`GET /api/quotes/{id}`

*findById*

<h3 id="findbyidusingget-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|id|

> 200 Response

<h3 id="findbyidusingget-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[EntityModel_QuoteJson_](#schemaentitymodel_quotejson_)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

### updateUsingPUT

<a id="opIdupdateUsingPUT"></a>

```shell
# You can also use wget
curl -X PUT /localhost:8080/api/quotes/{id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: */*'

```

```http
PUT /localhost:8080/api/quotes/{id} HTTP/1.1

Content-Type: application/json
Accept: */*

```


`PUT /api/quotes/{id}`

*update*

> Body parameter

```json
{
  "author": {
    "firstName": "string",
    "id": 0,
    "lastName": "string"
  },
  "content": "string",
  "id": 0
}
```

<h3 id="updateusingput-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|id|
|body|body|[QuoteJson](#schemaquotejson)|true|newQuoteJson|

> 200 Response

<h3 id="updateusingput-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<h3 id="updateusingput-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

### deleteUsingDELETE

<a id="opIddeleteUsingDELETE"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE /localhost:8080/api/quotes/{id} \
  -H 'Accept: */*'

```

```http
DELETE /localhost:8080/api/quotes/{id} HTTP/1.1

Accept: */*

```

*delete*

<h3 id="deleteusingdelete-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|id|

> 200 Response

<h3 id="deleteusingdelete-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<h3 id="deleteusingdelete-responseschema">Response Schema</h3>

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_AuthorJson">AuthorJson</h2>
<!-- backwards compatibility -->
<a id="schemaauthorjson"></a>
<a id="schema_AuthorJson"></a>
<a id="tocSauthorjson"></a>
<a id="tocsauthorjson"></a>

```json
{
  "firstName": "string",
  "id": 0,
  "lastName": "string"
}

```

AuthorJson

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|firstName|string|false|none|none|
|id|integer(int64)|false|none|none|
|lastName|string|false|none|none|

<h2 id="tocS_Links">Links</h2>
<!-- backwards compatibility -->
<a id="schemalinks"></a>
<a id="schema_Links"></a>
<a id="tocSlinks"></a>
<a id="tocslinks"></a>

```json
{
  "empty": true
}

```

Links

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|empty|boolean|false|none|none|

<h2 id="tocS_ModelAndView">ModelAndView</h2>
<!-- backwards compatibility -->
<a id="schemamodelandview"></a>
<a id="schema_ModelAndView"></a>
<a id="tocSmodelandview"></a>
<a id="tocsmodelandview"></a>

```json
{
  "empty": true,
  "model": {},
  "modelMap": {
    "property1": {},
    "property2": {}
  },
  "reference": true,
  "status": "ACCEPTED",
  "view": {
    "contentType": "string"
  },
  "viewName": "string"
}

```

ModelAndView

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|empty|boolean|false|none|none|
|model|object|false|none|none|
|modelMap|object|false|none|none|
|» **additionalProperties**|object|false|none|none|
|reference|boolean|false|none|none|
|status|string|false|none|none|
|view|[View](#schemaview)|false|none|none|
|viewName|string|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|status|ACCEPTED|
|status|ALREADY_REPORTED|
|status|BAD_GATEWAY|
|status|BAD_REQUEST|
|status|BANDWIDTH_LIMIT_EXCEEDED|
|status|CHECKPOINT|
|status|CONFLICT|
|status|CONTINUE|
|status|CREATED|
|status|DESTINATION_LOCKED|
|status|EXPECTATION_FAILED|
|status|FAILED_DEPENDENCY|
|status|FORBIDDEN|
|status|FOUND|
|status|GATEWAY_TIMEOUT|
|status|GONE|
|status|HTTP_VERSION_NOT_SUPPORTED|
|status|IM_USED|
|status|INSUFFICIENT_SPACE_ON_RESOURCE|
|status|INSUFFICIENT_STORAGE|
|status|INTERNAL_SERVER_ERROR|
|status|I_AM_A_TEAPOT|
|status|LENGTH_REQUIRED|
|status|LOCKED|
|status|LOOP_DETECTED|
|status|METHOD_FAILURE|
|status|METHOD_NOT_ALLOWED|
|status|MOVED_PERMANENTLY|
|status|MOVED_TEMPORARILY|
|status|MULTIPLE_CHOICES|
|status|MULTI_STATUS|
|status|NETWORK_AUTHENTICATION_REQUIRED|
|status|NON_AUTHORITATIVE_INFORMATION|
|status|NOT_ACCEPTABLE|
|status|NOT_EXTENDED|
|status|NOT_FOUND|
|status|NOT_IMPLEMENTED|
|status|NOT_MODIFIED|
|status|NO_CONTENT|
|status|OK|
|status|PARTIAL_CONTENT|
|status|PAYLOAD_TOO_LARGE|
|status|PAYMENT_REQUIRED|
|status|PERMANENT_REDIRECT|
|status|PRECONDITION_FAILED|
|status|PRECONDITION_REQUIRED|
|status|PROCESSING|
|status|PROXY_AUTHENTICATION_REQUIRED|
|status|REQUESTED_RANGE_NOT_SATISFIABLE|
|status|REQUEST_ENTITY_TOO_LARGE|
|status|REQUEST_HEADER_FIELDS_TOO_LARGE|
|status|REQUEST_TIMEOUT|
|status|REQUEST_URI_TOO_LONG|
|status|RESET_CONTENT|
|status|SEE_OTHER|
|status|SERVICE_UNAVAILABLE|
|status|SWITCHING_PROTOCOLS|
|status|TEMPORARY_REDIRECT|
|status|TOO_EARLY|
|status|TOO_MANY_REQUESTS|
|status|UNAUTHORIZED|
|status|UNAVAILABLE_FOR_LEGAL_REASONS|
|status|UNPROCESSABLE_ENTITY|
|status|UNSUPPORTED_MEDIA_TYPE|
|status|UPGRADE_REQUIRED|
|status|URI_TOO_LONG|
|status|USE_PROXY|
|status|VARIANT_ALSO_NEGOTIATES|

<h2 id="tocS_QuoteJson">QuoteJson</h2>
<!-- backwards compatibility -->
<a id="schemaquotejson"></a>
<a id="schema_QuoteJson"></a>
<a id="tocSquotejson"></a>
<a id="tocsquotejson"></a>

```json
{
  "author": {
    "firstName": "string",
    "id": 0,
    "lastName": "string"
  },
  "content": "string",
  "id": 0
}

```

QuoteJson

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|author|[AuthorJson](#schemaauthorjson)|false|none|none|
|content|string|false|none|none|
|id|integer(int64)|false|none|none|

<h2 id="tocS_View">View</h2>
<!-- backwards compatibility -->
<a id="schemaview"></a>
<a id="schema_View"></a>
<a id="tocSview"></a>
<a id="tocsview"></a>

```json
{
  "contentType": "string"
}

```

View

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|contentType|string|false|none|none|

<h2 id="tocS_CollectionModel_EntityModel_QuoteJson_">CollectionModel_EntityModel_QuoteJson_</h2>
<!-- backwards compatibility -->
<a id="schemacollectionmodel_entitymodel_quotejson_"></a>
<a id="schema_CollectionModel_EntityModel_QuoteJson_"></a>
<a id="tocScollectionmodel_entitymodel_quotejson_"></a>
<a id="tocscollectionmodel_entitymodel_quotejson_"></a>

```json
{
  "content": [
    {
      "author": {
        "firstName": "string",
        "id": 0,
        "lastName": "string"
      },
      "content": "string",
      "id": 0,
      "links": {
        "empty": true
      }
    }
  ],
  "links": {
    "empty": true
  }
}

```

CollectionModel«EntityModel«QuoteJson»»

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|content|[[EntityModel_QuoteJson_](#schemaentitymodel_quotejson_)]|false|none|none|
|links|[Links](#schemalinks)|false|none|none|

<h2 id="tocS_EntityModel_QuoteJson_">EntityModel_QuoteJson_</h2>
<!-- backwards compatibility -->
<a id="schemaentitymodel_quotejson_"></a>
<a id="schema_EntityModel_QuoteJson_"></a>
<a id="tocSentitymodel_quotejson_"></a>
<a id="tocsentitymodel_quotejson_"></a>

```json
{
  "author": {
    "firstName": "string",
    "id": 0,
    "lastName": "string"
  },
  "content": "string",
  "id": 0,
  "links": {
    "empty": true
  }
}

```

EntityModel«QuoteJson»

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|author|[AuthorJson](#schemaauthorjson)|false|none|none|
|content|string|false|none|none|
|id|integer(int64)|false|none|none|
|links|[Links](#schemalinks)|false|none|none|

## SWAGGER

****To open swagger ui and inspect whole API graph or interact with enpoints go to `http://localhost/swagger-ui.html` in your browser.****