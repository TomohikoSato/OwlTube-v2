package com.sgr.owltube_v2.infra.webapi.google.response

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "toplevel", strict = false)
class SuggestKeywordResponse(@set:ElementList(inline = true, name = "CompleteSuggestion")
                             @get:ElementList(inline = true, name = "CompleteSuggestion")
                             var suggests: List<CompleteSuggestion>? = null
)

@Root(name = "CompleteSuggestion", strict = false)
class CompleteSuggestion(@set:Element
                         @get:Element
                         var suggestion: Suggestion? = null)

@Root(name = "Suggestion", strict = false)
class Suggestion(@set:Attribute(name = "data")
                 @get:Attribute(name = "data")
                 var data: String? = null)