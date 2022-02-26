package com.speedit.server.dto.book.aladin

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.apache.commons.text.StringEscapeUtils.unescapeJava

@Suppress("unused")
class AladinBookListDto {
    lateinit var version: String
    lateinit var logo: String
    lateinit var title: String
    lateinit var link: String
    lateinit var pubDate: String
    var totalResults: Int = 0
    var startIndex: Int = 1
    var itemsPerPage: Int = 100
    lateinit var query: String
    var searchCategoryId: Long = 0
    lateinit var searchCategoryName: String
    lateinit var item: List<AladinBookDto>

    override fun toString(): String {
        return unescapeJava(ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE))
    }
}