package com.sgr.owltube_v2.infra.webapi.google.response

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "toplevel", strict = false)
class SuggestKeywordResponse() {

    @ElementList(inline = true)
    var suggests: List<Suggest>? = null

}

//data class SuggestKeywordResponse(val response: Map<Keyword, List<Suggest>>)

data class Keyword(val keyword: String)

@Root(name = "CompleteSuggestion", strict = false)
class Suggest() {

    @set:Element(name = "suggestion")
    @get:Element
    var suggest: String? = null

}